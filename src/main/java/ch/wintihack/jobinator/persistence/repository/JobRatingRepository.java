package ch.wintihack.jobinator.persistence.repository;

import ch.wintihack.jobinator.model.JobPreview;
import ch.wintihack.jobinator.model.JobRating;
import org.springframework.data.repository.CrudRepository;

public interface JobRatingRepository extends CrudRepository<JobRating, Integer> {
}
