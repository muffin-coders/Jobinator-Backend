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
    @RequestMapping(method = RequestMethod.GET, produces = "application/json", path = "/{userId}/user/previews")
    public List<JobPreview> getJobPreviews(@PathVariable(value = "userId") Integer userId) throws Exception {
        User user = userService.getUserById(userId);
        return jobService.getOverLowerBound(user).stream().collect(Collectors.toList());
    }
}
