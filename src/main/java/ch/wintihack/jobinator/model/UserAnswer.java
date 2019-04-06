package ch.wintihack.jobinator.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class UserAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer userAnswerId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "userId")
    @JsonIdentityReference(alwaysAsId = true)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "answerId")
    @JsonIdentityReference(alwaysAsId = true)
    @JoinColumn(name = "answer_id", nullable = false)
    private Answer answer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIdentityReference(alwaysAsId = true)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "questionId")
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;

    public UserAnswer(User user, Answer answer, Question question) {
        this.user = user;
        this.answer = answer;
        this.question = question;
    }

    public UserAnswer() {
        // used for hibernate
    }
}
