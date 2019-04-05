package ch.wintihack.jobinator.persistence.service;

import ch.wintihack.jobinator.model.Job;
import ch.wintihack.jobinator.model.Question;
import ch.wintihack.jobinator.persistence.repository.JobRepository;
import ch.wintihack.jobinator.persistence.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobService {

    @Autowired
    private JobRepository jobRepository;

    public Job getJobById(Integer id) throws Exception {
        return jobRepository.findById(id).orElseThrow(Exception::new);
    }
}
