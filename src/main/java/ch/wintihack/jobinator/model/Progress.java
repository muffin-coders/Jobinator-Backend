package ch.wintihack.jobinator.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Progress {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer progressId;

    @OneToOne
    private User user;

    private Integer currentAmountJobs;

    private Integer maxAmountJobs;
}
