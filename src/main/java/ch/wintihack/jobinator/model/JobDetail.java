package ch.wintihack.jobinator.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@Entity
public class JobDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer jobDetailId;

    private String jobTitle;

    private String jobText;

    private String url;

    private String location;

    private String tele;

    @OneToOne(cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    private JobPreview jobPreview;
}
