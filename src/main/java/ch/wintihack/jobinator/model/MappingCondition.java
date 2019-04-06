package ch.wintihack.jobinator.model;

import lombok.Data;

import javax.persistence.*;

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
}
