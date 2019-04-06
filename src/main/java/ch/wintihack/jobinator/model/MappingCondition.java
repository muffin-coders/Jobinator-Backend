package ch.wintihack.jobinator.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class MappingCondition {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer conditionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "answer_id", nullable = false)
    private Answer answer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mapping_id", nullable = false)
    private Mapping mapping;

    public boolean fulfillsCondition(List<UserAnswer> userAnswers) {
        return userAnswers.stream()
                .anyMatch(userAnswer -> userAnswer.getQuestion().equals(question)
                                && userAnswer.getAnswer().equals(answer));
    }
}
