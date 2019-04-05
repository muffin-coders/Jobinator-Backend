package ch.wintihack.jobinator.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class UserAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer userAnswerId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties("userAnswer")
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties("userAnswer")
    @JoinColumn(name = "answer_id", nullable = false)
    private Answer answer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties("userAnswer")
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;
}
