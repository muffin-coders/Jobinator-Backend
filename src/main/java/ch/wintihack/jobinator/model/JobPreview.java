package ch.wintihack.jobinator.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
public class JobPreview {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer jobPreviewId;

    private String jobTitle;

    private String jobText;

    @OneToOne
    private JobDetail jobDetail;

    @JsonIgnore
    @EqualsAndHashCode.Exclude
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "jobPreview")
    private Set<JobRating> jobRatings;
}
