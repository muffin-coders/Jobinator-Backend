package ch.wintihack.jobinator.model;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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
    @JsonIdentityReference(alwaysAsId=true)
    @JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="jobDetailId")
    private JobDetail jobDetail;

    @JsonIgnore
    @EqualsAndHashCode.Exclude
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "jobPreview")
    private Set<JobRating> jobRatings;

    @JsonIgnore
    @EqualsAndHashCode.Exclude
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "jobPreview")
    private Set<Favorite> favorites;
}
