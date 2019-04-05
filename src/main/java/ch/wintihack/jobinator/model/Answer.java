package ch.wintihack.jobinator.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@Entity
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer answerId;

    private String answerText;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties("answers")
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;

    @EqualsAndHashCode.Exclude
    @JsonIgnoreProperties("answer")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "answer")
    private Set<UserAnswer> userAnswers;
}
