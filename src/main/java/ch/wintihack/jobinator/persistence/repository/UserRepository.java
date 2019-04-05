package ch.wintihack.jobinator.persistence.repository;

import ch.wintihack.jobinator.model.Question;
import ch.wintihack.jobinator.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
}
