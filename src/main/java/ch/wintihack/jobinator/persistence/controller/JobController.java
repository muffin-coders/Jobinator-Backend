package ch.wintihack.jobinator.persistence.controller;

import ch.wintihack.jobinator.model.Favorite;
import ch.wintihack.jobinator.model.JobDetail;
import ch.wintihack.jobinator.model.JobPreview;
import ch.wintihack.jobinator.model.User;
import ch.wintihack.jobinator.persistence.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("job")
public class JobController {

    @Autowired
    private JobService jobService;

    @Autowired
    private JobRatingService jobRatingService;

    @Autowired
    private UserService userService;

    @Autowired
    private FavoriteService favoriteService;

    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET, produces = "application/json", path = "/previews/{jobPreviewId}")
    public JobPreview getJobPreviewById(@PathVariable(value = "jobPreviewId") Integer jobPreviewId) throws Exception {
        return jobService.getJobPreviewById(jobPreviewId);
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET, produces = "application/json", path = "/details/{jobDetailId}")
    public JobDetail getJobDetailById(@PathVariable(value = "jobDetailId") Integer jobDetailId) throws Exception {
        return jobService.getJobDetailById(jobDetailId);
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.POST, produces = "application/json", path = "/{userId}/users/previews/{previewId}/like")
    public void likeJob(@PathVariable(value = "userId") Integer userId, @PathVariable(value = "previewId") Integer previewId) throws Exception {
        User user = userService.getUserById(userId);
        JobPreview jobPreview = jobService.getJobPreviewById(previewId);
        jobRatingService.addLike(jobPreview, user);
        updateUserProgress(user);
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.POST, produces = "application/json", path = "/{userId}/users/previews/{previewId}/dislike")
    public void dislikeJob(@PathVariable(value = "userId") Integer userId, @PathVariable(value = "previewId") Integer previewId) throws Exception {
        User user = userService.getUserById(userId);
        JobPreview jobPreview = jobService.getJobPreviewById(previewId);
        jobRatingService.addDislikeLike(jobPreview, user);
        updateUserProgress(user);
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.POST, produces = "application/json", path = "/{userId}/users/previews/{previewId}/favorite")
    public void favoriteJob(@PathVariable(value = "userId") Integer userId, @PathVariable(value = "previewId") Integer previewId) throws Exception {
        User user = userService.getUserById(userId);
        JobPreview jobPreview = jobService.getJobPreviewById(previewId);
        favoriteService.saveFavorite(new Favorite(user, jobPreview));
        jobRatingService.addFavorite(jobPreview, user);
        updateUserProgress(user);
    }


    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET, produces = "application/json", path = "/{userId}/users/favorites")
    public List<JobPreview> favoriteJob(@PathVariable(value = "userId") Integer userId) throws Exception {
        User user = userService.getUserById(userId);
        updateUserProgress(user);
        return user.getFavorites().stream().map(Favorite::getJobPreview).collect(Collectors.toList());
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.POST, produces = "application/json", path = "/{userId}/users/previews/{previewId}/detail")
    public JobDetail clickJob(@PathVariable(value = "userId") Integer userId, @PathVariable(value = "previewId") Integer previewId) throws Exception {
        User user = userService.getUserById(userId);
        JobPreview jobPreview = jobService.getJobPreviewById(previewId);
        jobRatingService.addClick(jobPreview, user);
        updateUserProgress(user);
        return jobPreview.getJobDetail();
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET, produces = "application/json", path = "/{userId}/users/previews")
    public List<JobPreview> getJobPreviews(@PathVariable(value = "userId") Integer userId) throws Exception {
        User user = userService.getUserById(userId);
        return jobService.getJobList(user, user.getProgress().getCurrentLimit());
    }


    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET, produces = "application/json", path = "/users/{userId}/previews/next")
    public List<JobPreview> getNextPreview(@PathVariable(value = "userId") Integer userId) throws Exception {
        User user = userService.getUserById(userId);
        List<JobPreview> jobPreviews = jobService.getJobList(user, user.getProgress().getCurrentLimit());
        return jobService.getJobList(user, user.getProgress().getCurrentLimit()).stream().peek(v -> v.setIsLast(jobPreviews.size() == 1)).limit(1).collect(Collectors.toList());
    }

    private void updateUserProgress(User user) {
        user.getProgress().decreaseLimit();
        user.getProgress().setCurrentAmountJobs(jobService.getJobList(user, user.getProgress().getCurrentLimit()).size());
        userService.save(user);
    }

}
