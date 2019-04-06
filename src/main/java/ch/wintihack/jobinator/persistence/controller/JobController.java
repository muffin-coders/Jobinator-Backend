package ch.wintihack.jobinator.persistence.controller;

import ch.wintihack.jobinator.model.*;
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

    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET, produces = "application/json", path = "/{jobPreviewId}")
    public JobPreview getJobPreviewById(@PathVariable(value = "jobPreviewId") Integer jobPreviewId) throws Exception {
        return jobService.getJobPreviewById(jobPreviewId);
    }
    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET, produces = "application/json", path = "/{jobDetailId}")
    public JobDetail getJobDetailById(@PathVariable(value = "jobDetailId") Integer jobDetailId) throws Exception {
        return jobService.getJobDetailById(jobDetailId);
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET, produces = "application/json", path = "/{userId}/users/previews")
    public List<JobPreview> getJobPreviews(@PathVariable(value = "userId") Integer userId) throws Exception {
        User user = userService.getUserById(userId);
        return jobService.getOverLowerBound(user).stream().collect(Collectors.toList());
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.POST, produces = "application/json", path = "/{userId}/users/previews/{previewId}/like")
    public void likeJob(@PathVariable(value = "userId") Integer userId,@PathVariable(value = "previewId") Integer previewId) throws Exception {
        User user = userService.getUserById(userId);
        JobPreview jobPreview = jobService.getJobPreviewById(previewId);
        jobRatingService.addLike(jobPreview,user);
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.POST, produces = "application/json", path = "/{userId}/users/previews/{previewId}/dislike")
    public void dislikeJob(@PathVariable(value = "userId") Integer userId,@PathVariable(value = "previewId") Integer previewId) throws Exception {
        User user = userService.getUserById(userId);
        JobPreview jobPreview = jobService.getJobPreviewById(previewId);
        jobRatingService.addDislikeLike(jobPreview,user);
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.POST, produces = "application/json", path = "/{userId}/users/previews/{previewId}/favorite")
    public void favoriteJob(@PathVariable(value = "userId") Integer userId,@PathVariable(value = "previewId") Integer previewId) throws Exception {
        User user = userService.getUserById(userId);
        JobPreview jobPreview = jobService.getJobPreviewById(previewId);
        jobRatingService.addFavorite(jobPreview,user);
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.POST, produces = "application/json", path = "/{userId}/users/previews/{previewId}/click")
    public void clickJob(@PathVariable(value = "userId") Integer userId,@PathVariable(value = "previewId") Integer previewId) throws Exception {
        User user = userService.getUserById(userId);
        JobPreview jobPreview = jobService.getJobPreviewById(previewId);
        jobRatingService.addClick(jobPreview,user);
    }
}
