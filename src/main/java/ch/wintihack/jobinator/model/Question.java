package ch.wintihack.jobinator.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.w3c.dom.Text;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer questionId;

    private String question;

    @EqualsAndHashCode.Exclude
    @JsonIgnoreProperties("question")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "question")
    private Set<UserAnswer> userAnswers;
}
