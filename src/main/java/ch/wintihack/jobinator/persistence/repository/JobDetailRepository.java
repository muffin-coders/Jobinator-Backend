package ch.wintihack.jobinator.persistence.repository;

import ch.wintihack.jobinator.model.JobDetail;
import ch.wintihack.jobinator.model.JobPreview;
import org.springframework.data.repository.CrudRepository;

public interface JobDetailRepository extends CrudRepository<JobDetail, Integer> {
}
