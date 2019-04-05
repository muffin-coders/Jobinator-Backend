package ch.wintihack.jobinator.persistence.repository;

import ch.wintihack.jobinator.model.Question;
import org.springframework.data.repository.CrudRepository;

public interface QuestionRepository extends CrudRepository<Question, Integer> {
}
