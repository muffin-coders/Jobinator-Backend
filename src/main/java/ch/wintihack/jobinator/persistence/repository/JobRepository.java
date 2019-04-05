package ch.wintihack.jobinator.persistence.repository;

import ch.wintihack.jobinator.model.Job;
import ch.wintihack.jobinator.model.User;
import org.springframework.data.repository.CrudRepository;

public interface JobRepository extends CrudRepository<Job, Integer> {
}
