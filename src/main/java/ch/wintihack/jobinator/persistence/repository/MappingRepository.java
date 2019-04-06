package ch.wintihack.jobinator.persistence.repository;

import ch.wintihack.jobinator.model.Mapping;
import org.springframework.data.repository.CrudRepository;

public interface MappingRepository extends CrudRepository<Mapping, Integer> {
}
