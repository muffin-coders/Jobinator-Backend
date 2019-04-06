package ch.wintihack.jobinator.persistence.controller;

import ch.wintihack.jobinator.model.Answer;
import ch.wintihack.jobinator.model.Question;
import ch.wintihack.jobinator.model.User;
import ch.wintihack.jobinator.model.UserAnswer;
import ch.wintihack.jobinator.persistence.service.AnswerService;
import ch.wintihack.jobinator.persistence.service.QuestionService;
import ch.wintihack.jobinator.persistence.service.UserAnswerService;
import ch.wintihack.jobinator.persistence.service.UserService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

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

    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET, produces = "application/json", path = "/{userId}/questions")
    public Question getNextQuestion(@PathVariable(value = "userId") Integer userId) throws Exception {
        return questionService.getNextQuestion(userId);
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public User createNewUser() {
        return userService.createNewUser();
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.POST, produces = "application/json", path = "/{idUser}/questions/{idQuestion}/answer/{answerId}")
    public UserAnswer enterUserAnswer(@PathVariable(value = "idUser") Integer idUser, @PathVariable(value = "idQuestion") Integer idQuestion, @PathVariable(value = "answerId") Integer answerId) throws Exception {
        Answer answer = answerService.getAnswerById(answerId);
        Question question = questionService.getQuestionById(idQuestion);
        User user = userService.getUserById(idUser);
        UserAnswer userAnswer = new UserAnswer(user, answer, question);
        return userAnswerService.createNewUserAnswer(userAnswer);
    }
}
