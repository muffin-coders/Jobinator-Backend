package ch.wintihack.jobinator.persistence.repository;

import ch.wintihack.jobinator.model.Progress;
import org.springframework.data.repository.CrudRepository;

public interface ProgressRepository extends CrudRepository<Progress, Integer> {
}
