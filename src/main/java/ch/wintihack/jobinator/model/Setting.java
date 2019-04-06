package ch.wintihack.jobinator.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Setting {
    public static final double LOWER_BOUND_AI_POINTS = 3;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer settingsID;

    private Integer pointsFavorite = 5;

    private Integer pointsDetailsClick = 2;

    private Integer pointsInterest = 3;

    private Integer pointsNoInterest = 1;

    private Double lowerBoundAIPoints = LOWER_BOUND_AI_POINTS;
}
