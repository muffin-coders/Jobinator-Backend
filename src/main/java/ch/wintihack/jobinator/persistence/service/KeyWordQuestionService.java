package ch.wintihack.jobinator.persistence.service;

import ch.wintihack.jobinator.model.*;
import ch.wintihack.jobinator.recommendation.service.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class KeyWordQuestionService {

    public Optional<Integer> getScore(User user, JobPreview jobPreview) {
        List<String> keywords = new ArrayList<>();
        for (UserAnswer userAnswer : user.getUserAnswers()) {
            if (QuestionType.KeyWordQuestion.equals(userAnswer.getQuestion().getQuestionTye())) {
                Answer answer = userAnswer.getAnswer();
                String answerText = answer.getAnswerText();
                keywords.add(answerText);
            }
        }
        long sumMatches = keywords.stream().filter(s -> jobPreview.getJobText().contains(s)).count();
        return Optional.of((Integer.valueOf(String.valueOf(sumMatches / (keywords.size()+1) * 100))));
    }

}
