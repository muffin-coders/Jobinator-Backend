package ch.wintihack.jobinator.model;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class JobRating {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer jobRatingId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_preview_id", nullable = false)
    @JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="jobPreviewId")
    @JsonIdentityReference(alwaysAsId=true)
    private JobPreview jobPreview;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="userId")
    @JsonIdentityReference(alwaysAsId=true)
    private User user;

    private Boolean isFavorite;

    private Boolean liked;

    private Boolean clicked;
}
