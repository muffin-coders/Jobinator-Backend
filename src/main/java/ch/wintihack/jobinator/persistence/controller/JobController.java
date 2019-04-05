package ch.wintihack.jobinator.persistence.controller;

import ch.wintihack.jobinator.model.*;
import ch.wintihack.jobinator.persistence.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("job")
public class JobController {

    @Autowired
    private JobService jobService;

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
}
