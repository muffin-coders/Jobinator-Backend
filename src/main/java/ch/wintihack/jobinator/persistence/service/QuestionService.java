package ch.wintihack.jobinator.persistence.service;

import ch.wintihack.jobinator.model.*;
import ch.wintihack.jobinator.persistence.repository.MappingRepository;
import ch.wintihack.jobinator.persistence.repository.QuestionRepository;
import ch.wintihack.jobinator.persistence.repository.UserAnswerRepository;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private MappingRepository mappingRepository;

    @Autowired
    private UserAnswerRepository userAnswerRepository;

    private Map<Integer, Integer> currentQuestions = new HashMap<Integer, Integer>();

    public Iterable<Question> getAllObjects() {
        return questionRepository.findAll();
    }

    public Question getNextQuestion(int user_id) throws Exception {
        Integer currentQuestionId = currentQuestions.get(user_id);
        if (currentQuestionId == null) {
            Question question = getQuestionById(1);
            currentQuestions.put(user_id, question.getQuestionId());
            return question;
        }

        List<Mapping> mappings = Lists.newArrayList(mappingRepository.findAll());
        List<UserAnswer> userAnswers = Lists.newArrayList(userAnswerRepository.findAll());
        for (Mapping mapping : mappings) {
            if (mapping.getBaseQuestion().getQuestionId().equals(currentQuestionId)) {
                Set<MappingCondition> mappingConditions = mapping.getMappingConditions();
                if (mappingConditions.isEmpty()) {
                    currentQuestions.put(user_id, mapping.getResultQuestion().getQuestionId());
                    return mapping.getResultQuestion();
                }
                for (MappingCondition condition : mappingConditions) {
                    for (UserAnswer userAnswer : userAnswers) {
                        if (userAnswer.getUser().getUserId() == user_id
                                && userAnswer.getQuestion().equals(condition.getQuestion())
                                && userAnswer.getAnswer().equals(condition.getAnswer())) {
                            currentQuestions.put(user_id, mapping.getResultQuestion().getQuestionId());
                            return mapping.getResultQuestion();
                        }
                    }
                }
            }
        }
        return null;
    }

    public Question getQuestionById(Integer id) throws Exception {
        return questionRepository.findById(id).orElseThrow(Exception::new);
    }
}
