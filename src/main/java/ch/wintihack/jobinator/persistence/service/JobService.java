package ch.wintihack.jobinator.persistence.service;

import ch.wintihack.jobinator.model.*;
import ch.wintihack.jobinator.persistence.repository.JobDetailRepository;
import ch.wintihack.jobinator.persistence.repository.JobPreviewRepository;
import ch.wintihack.jobinator.persistence.repository.JobRatingRepository;
import ch.wintihack.jobinator.recommendation.data.InputRating;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class JobService {

    @Autowired
    private JobPreviewRepository jobPreviewRepository;
    @Autowired
    private JobDetailRepository jobDetailRepository;
    @Autowired
    private JobRatingRepository jobRatingRepository;
    @Autowired
    private ScoreService scoreService;
    @Autowired
    private SettingService settingService;
    @Autowired
    private CategoryQuestionService categoryQuestionService;

    public JobPreview getJobPreviewById(Integer id) throws Exception {
        return jobPreviewRepository.findById(id).orElseThrow(Exception::new);
    }

    public JobDetail getJobDetailById(Integer id) throws Exception {
        return jobDetailRepository.findById(id).orElseThrow(Exception::new);
    }

    public JobDetail save(JobDetail job) {
        return jobDetailRepository.save(job);
    }

    public List<InputRating> getInputRatings() {
        return getAllJobRatings().stream()
                .map(jR -> new InputRating(jR.getUser().getUserId(), jR.getJobPreview().getJobPreviewId(), jR.getRating()))
                .collect(Collectors.toList());
    }

    public List<JobRating> getAllJobRatings() {
        Setting setting = settingService.getSetting();
        return Lists.newArrayList(jobRatingRepository.findAll())
                .stream().peek(jR -> jR.setRating(setting))
                .collect(Collectors.toList());
    }

    public List<JobPreview> getJobList(User user, Integer limit) {
        List<JobPreview> jobRatings = Lists.newArrayList(jobPreviewRepository.findAll());
        List<JobPreview> alreadySeen = user.getJobRatings().stream().map(JobRating::getJobPreview).collect(Collectors.toList());
        List<JobPreview> filteredByCat = categoryQuestionService.filterByCat(user,alreadySeen);
        jobRatings = filterByAlreadySeen(jobRatings.stream(), filteredByCat).collect(Collectors.toList());
        return jobRatings.stream()
                .peek(jobPreview -> jobPreview.setScore(scoreService.getScore(user, jobPreview)))
                .sorted(Comparator.comparing(JobPreview::getScore))
                .limit(limit)
                .collect(Collectors.toList());
    }

    private Stream<JobPreview> filterByAlreadySeen(Stream<JobPreview> jobPreviewStream, List<JobPreview> jobPreviews) {
        return jobPreviewStream.filter(jP -> !jobPreviews.contains(jP));
    }
}
