package ch.wintihack.jobinator.persistence.service;

import ch.wintihack.jobinator.model.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryQuestionService {

    public List<JobPreview> filterByCat(User user, List<JobPreview> jobPreviews) {
        List<String> categories = new ArrayList<>();
        for (UserAnswer userAnswer : user.getUserAnswers()) {
            if (QuestionType.CategoryQuestion.equals(userAnswer.getQuestion().getQuestionType())) {
                Answer answer = userAnswer.getAnswer();
                String answerText = answer.getAnswerText();
                categories.add(answerText);
            }
        }
        return jobPreviews.stream().filter(jobPreview -> jobPreview.getCategories().containsAll(jobPreviews)).collect(Collectors.toList());
    }

}
