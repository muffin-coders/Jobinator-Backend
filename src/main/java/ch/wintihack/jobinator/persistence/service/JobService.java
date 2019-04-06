package ch.wintihack.jobinator.persistence.service;

import ch.wintihack.jobinator.model.*;
import ch.wintihack.jobinator.persistence.repository.JobDetailRepository;
import ch.wintihack.jobinator.persistence.repository.JobPreviewRepository;
import ch.wintihack.jobinator.persistence.repository.JobRatingRepository;
import ch.wintihack.jobinator.recommendation.data.InputRating;
import ch.wintihack.jobinator.recommendation.service.RecommendationService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobService {

    @Autowired
    private JobPreviewRepository jobPreviewRepository;
    @Autowired
    private JobDetailRepository jobDetailRepository;
    @Autowired
    private JobRatingRepository jobRatingRepository;
    @Autowired
    private SettingService settingService;

    RecommendationService recommendationService = new RecommendationService(() -> getInputRatings(), 10, 0);

    public JobPreview getJobPreviewById(Integer id) throws Exception {
        return jobPreviewRepository.findById(id).orElseThrow(Exception::new);
    }

    public JobDetail getJobDetailById(Integer id) throws Exception {
        return jobDetailRepository.findById(id).orElseThrow(Exception::new);
    }

    private List<InputRating> getInputRatings() {
        return getAllJobRatings().stream().map(jR -> new InputRating(jR.getUser().getUserId(), jR.getJobPreview().getJobPreviewId(), jR.getRating())).collect(Collectors.toList());
    }

    public List<JobRating> getAllJobRatings() {
        Setting setting = settingService.getSetting();
        return Lists.newArrayList(jobRatingRepository.findAll()).stream().peek(jR -> jR.setRating(setting)).collect(Collectors.toList());
    }

    public List<JobPreview> getOverLowerBound(User user) {
        Double lowerBound = settingService.getSetting().getLowerBoundAIPoints();
        List<JobPreview> jobRatings = Lists.newArrayList(jobPreviewRepository.findAll());
        if (recommendationService.isModelReady())
            return Lists.newArrayList(jobPreviewRepository.findAll())
                    .stream()
                    .filter(jP -> {
                        Double p = recommendationService.getPrediction(user.getUserId(), jP.getJobPreviewId());
                        return p >= lowerBound || p == 0;
                    })
                    .collect(Collectors.toList());
        else
            return jobRatings;
    }
}
