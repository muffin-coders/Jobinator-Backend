package ch.wintihack.jobinator.persistence.controller;

import ch.wintihack.jobinator.model.Answer;
import ch.wintihack.jobinator.model.Question;
import ch.wintihack.jobinator.model.User;
import ch.wintihack.jobinator.model.UserAnswer;
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
    private UserAnswerService userAnswerService;

    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET, produces = "application/json", path = "/{id}/questions")
    public Question getNextQuestion(@PathVariable(value = "id") Integer id) {
        List<Question> myList = Lists.newArrayList(questionService.getAllObjects());
        Collections.shuffle(myList);
        return myList.get(0);
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public User createNewUser() {
        return userService.createNewUser();
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.POST, produces = "application/json", path = "/{idUser}/questions/{idQuestion}/answers")
    public UserAnswer enterUserAnswer(@RequestParam Answer answer) {
        UserAnswer userAnswer = new UserAnswer();
        return userAnswerService.createNewUserAnswer(userAnswer);
    }
}
