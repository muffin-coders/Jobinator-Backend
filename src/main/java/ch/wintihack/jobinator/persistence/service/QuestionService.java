package ch.wintihack.jobinator.persistence.service;

import ch.wintihack.jobinator.model.*;
import ch.wintihack.jobinator.persistence.repository.MappingRepository;
import ch.wintihack.jobinator.persistence.repository.QuestionRepository;
import ch.wintihack.jobinator.persistence.repository.UserAnswerRepository;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private MappingRepository mappingRepository;

    @Autowired
    private UserAnswerRepository userAnswerRepository;

    public Iterable<Question> getAllObjects() {
        return questionRepository.findAll();
    }

    public Question getNextQuestion(int userId, Integer currentQuestionId) throws Exception {
        if (currentQuestionId == null || currentQuestionId == 0)
            return getQuestionById(1);

        Integer questionInt = currentQuestionId;
        Question question = getQuestionById(questionInt);
        Optional<Question> nextQuestion = question.getBaseMappings()
                .stream()
                .filter(mapping -> mapping.getMappingConditions().size() == 0)
                .map(Mapping::getResultQuestion)
                .findAny();
        System.out.println(currentQuestionId);
        System.out.println("1");
        if(nextQuestion.isPresent())
            return nextQuestion.get();
        System.out.println("2");

        List<UserAnswer> userAnswers = Lists.newArrayList(userAnswerRepository.findAll());
        nextQuestion = question.getBaseMappings()
                .stream()
                .peek(v -> System.out.println("3"))
                .filter(mapping -> mapping.checkAllCondition(userAnswers))
                .peek(v -> System.out.println("4"))
                .map(Mapping::getResultQuestion)
                .peek(v -> System.out.println("5"))
                .findAny();
                //.orElseThrow(Exception::new);
        if (nextQuestion.isPresent())
            return nextQuestion.get();

        throw new Exception();
    }

    public Question getQuestionById(Integer id) throws Exception {
        return questionRepository.findById(id).orElseThrow(Exception::new);
    }
}
