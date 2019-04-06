package ch.wintihack.jobinator.persistence.repository;

import ch.wintihack.jobinator.model.UserAnswer;
import org.springframework.data.repository.CrudRepository;

public interface UserAnswerRepository extends CrudRepository<UserAnswer, Integer> {
}
