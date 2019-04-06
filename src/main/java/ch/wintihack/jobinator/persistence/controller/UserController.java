package ch.wintihack.jobinator.persistence.controller;

import ch.wintihack.jobinator.model.*;
import ch.wintihack.jobinator.persistence.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private UserService userService;

    @Autowired
    private AnswerService answerService;

    @Autowired
    private UserAnswerService userAnswerService;
    @Autowired
    private JobService jobService;

    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET, produces = "application/json", path = "/{userId}/questions")
    public Question getNextQuestion(@PathVariable(value = "userId") Integer userId) throws Exception {
        User user = userService.getUserById(userId);
        Question question = questionService.getNextQuestion(userId, user.getCurrentQuestionId());
        user.setCurrentQuestionId(question.getQuestionId());
        userService.save(user);
        return question;
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public User createNewUser() {
        return userService.createNewUser();
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.POST, produces = "application/json", path = "/{idUser}/questions/{idQuestion}/answer/{answerId}")
    public Progress enterUserAnswer(@PathVariable(value = "idUser") Integer idUser, @PathVariable(value = "idQuestion") Integer idQuestion, @PathVariable(value = "answerId") Integer answerId) throws Exception {
        Answer answer = answerService.getAnswerById(answerId);
        Question question = questionService.getQuestionById(idQuestion);
        User user = userService.getUserById(idUser);
        UserAnswer userAnswer = new UserAnswer(user, answer, question);
        userAnswerService.createNewUserAnswer(userAnswer);
        user.getProgress().setCurrentAmountJobs(jobService.getJobList(user, 9999).size());
        return user.getProgress();
    }
}
