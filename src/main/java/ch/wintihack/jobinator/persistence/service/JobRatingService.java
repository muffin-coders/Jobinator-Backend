package ch.wintihack.jobinator.persistence.service;

import ch.wintihack.jobinator.model.JobPreview;
import ch.wintihack.jobinator.model.JobRating;
import ch.wintihack.jobinator.model.User;
import ch.wintihack.jobinator.persistence.repository.JobRatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobRatingService {

    @Autowired
    private JobRatingRepository jobRatingRepository;

    public void addClick(JobPreview jobPreview, User user) {
        JobRating jobRating = getJobRating(jobPreview, user);
        jobRating.setClicked(true);
        jobRatingRepository.save(jobRating);
    }

    private JobRating getJobRating(JobPreview jobPreview, User user) {
        JobRating jobRating = jobPreview.getJobRatings().stream().filter(v -> v.getUser().equals(user)).findFirst().orElseGet(JobRating::new);
        jobRating.setJobPreview(jobPreview);
        jobRating.setUser(user);
        return jobRating;
    }

    public void addFavorite(JobPreview jobPreview, User user) {
        JobRating jobRating = getJobRating(jobPreview, user);
        jobRating.setIsFavorite(true);
        jobRatingRepository.save(jobRating);
    }

    public JobRating addLike(JobPreview jobPreview, User user) {
        JobRating jobRating = getJobRating(jobPreview, user);
        jobRating.setLiked(true);
        return jobRatingRepository.save(jobRating);
    }

    public void addDislikeLike(JobPreview jobPreview, User user) {
        JobRating jobRating = getJobRating(jobPreview, user);
        jobRating.setLiked(false);
        jobRatingRepository.save(jobRating);
    }

}
