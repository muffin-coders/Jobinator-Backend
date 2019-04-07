package ch.wintihack.jobinator.persistence.service;

import ch.wintihack.jobinator.model.JobPreview;
import ch.wintihack.jobinator.model.Setting;
import ch.wintihack.jobinator.model.User;
import ch.wintihack.jobinator.recommendation.service.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class AiService {
    @Autowired
    private JobService jobService;

    @Autowired
    private SettingService settingService;

    RecommendationService recommendationService;

    public Optional<Integer> getScore(User user, JobPreview jobPreview) {
        Setting setting = settingService.getSetting();
        if (recommendationService == null) {
            recommendationService = new RecommendationService(() -> jobService.getInputRatings(), 10000, 10000);
            return Optional.empty();
        }
        if (!recommendationService.isModelReady())
            return Optional.empty();
        Double points;
        try {
            points = recommendationService.getPrediction(user.getUserId(), jobPreview.getJobPreviewId());
        } catch (Exception e) {
            return Optional.empty();
        }
        return Optional.of(((Double) (setting.getPointsFavorite() / points * 100)).intValue());
    }

}
