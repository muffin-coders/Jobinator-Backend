package ch.wintihack.jobinator.persistence.service;

import ch.wintihack.jobinator.model.UserAnswer;
import ch.wintihack.jobinator.persistence.repository.UserAnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAnswerService {

    @Autowired
    private UserAnswerRepository userAnswerService;

    public UserAnswer createNewUserAnswer(UserAnswer userAnswer) {
        return userAnswerService.save(userAnswer);
    }
}
