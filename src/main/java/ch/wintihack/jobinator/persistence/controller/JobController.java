package ch.wintihack.jobinator.persistence.controller;

import ch.wintihack.jobinator.model.*;
import ch.wintihack.jobinator.persistence.service.*;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("job")
public class JobController {

    @Autowired
    private JobService jobService;


    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET, produces = "application/json", path = "/{jobId}")
    public Job getNextQuestion(@PathVariable(value = "userId") Integer jobId) throws Exception {
        return jobService.getJobById(jobId);
    }
}
