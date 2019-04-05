package ch.wintihack.jobinator.persistence.repository;

import ch.wintihack.jobinator.model.JobPreview;
import org.springframework.data.repository.CrudRepository;

public interface JobPreviewRepository extends CrudRepository<JobPreview, Integer> {
}
