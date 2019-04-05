package ch.wintihack.jobinator.persistence.service;

import ch.wintihack.jobinator.model.Answer;
import ch.wintihack.jobinator.model.UserAnswer;
import ch.wintihack.jobinator.persistence.repository.AnswerRepository;
import ch.wintihack.jobinator.persistence.repository.UserAnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnswerService {

    @Autowired
    private AnswerRepository answerRepository;

    public Answer getAnswerById(Integer id) throws Exception {
        return answerRepository.findById(id).orElseThrow(Exception::new);
    }
}
