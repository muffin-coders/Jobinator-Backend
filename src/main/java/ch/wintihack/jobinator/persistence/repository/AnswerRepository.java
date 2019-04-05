package ch.wintihack.jobinator.persistence.repository;

import ch.wintihack.jobinator.model.Answer;
import ch.wintihack.jobinator.model.User;
import org.springframework.data.repository.CrudRepository;

public interface AnswerRepository extends CrudRepository<Answer, Integer> {
}
