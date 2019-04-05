package ch.wintihack.jobinator.persistence.controller;

import ch.wintihack.jobinator.model.Question;
import ch.wintihack.jobinator.persistence.service.QuestionService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("user")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET, produces = "application/json", path = "/{id}/question")
    public Question getObjectById(@PathVariable(value = "id") Integer id) throws Exception {
        List<Question> myList = Lists.newArrayList(questionService.getAllObjects());
        Collections.shuffle(myList);
        return myList.get(0);
    }
}
