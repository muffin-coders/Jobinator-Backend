package ch.wintihack.jobinator.persistence.repository;

import ch.wintihack.jobinator.model.Favorite;
import org.springframework.data.repository.CrudRepository;

public interface FavoriteRepository extends CrudRepository<Favorite, Integer> {
}
