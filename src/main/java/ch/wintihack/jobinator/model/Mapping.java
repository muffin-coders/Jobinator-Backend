package ch.wintihack.jobinator.model;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
public class Mapping {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer mappingId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIdentityReference(alwaysAsId = true)
    @JoinColumn(name = "base_question_id", nullable = false)
    private Question baseQuestion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIdentityReference(alwaysAsId = true)
    @JoinColumn(name = "result_question_id", nullable = false)
    private Question resultQuestion;

    @JsonIgnore
    @EqualsAndHashCode.Exclude
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "mapping")
    private Set<MappingCondition> mappingConditions;
}
