package ch.wintihack.jobinator.persistence.service;

import ch.wintihack.jobinator.model.JobDetail;
import ch.wintihack.jobinator.model.JobPreview;
import ch.wintihack.jobinator.persistence.repository.JobDetailRepository;
import ch.wintihack.jobinator.persistence.repository.JobPreviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobService {

    @Autowired
    private JobPreviewRepository jobPreviewRepository;
    @Autowired
    private JobDetailRepository jobDetailRepository;

    public JobPreview getJobPreviewById(Integer id) throws Exception {
        return jobPreviewRepository.findById(id).orElseThrow(Exception::new);
    }

    public JobDetail getJobDetailById(Integer id) throws Exception {
        return jobDetailRepository.findById(id).orElseThrow(Exception::new);
    }
}
