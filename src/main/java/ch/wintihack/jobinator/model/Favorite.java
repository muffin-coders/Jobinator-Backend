package ch.wintihack.jobinator.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@Entity
public class Favorite {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer favoriteId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @EqualsAndHashCode.Exclude
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "userId")
    @JsonIdentityReference(alwaysAsId = true)
    private User user;


    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    @EqualsAndHashCode.Exclude
    @JoinColumn(name = "job_preview_id", nullable = false)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "jobPreviewId")
    @JsonIdentityReference(alwaysAsId = true)
    private JobPreview jobPreview;

    public Favorite(User user, JobPreview jobPreview) {
        this.user = user;
        this.jobPreview = jobPreview;
    }

    public Favorite() {
    }
}
