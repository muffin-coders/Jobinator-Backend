package ch.wintihack.jobinator.model;

import lombok.Data;
import org.w3c.dom.Text;

import javax.persistence.*;

@Data
@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer questionId;

    private String question;
}
