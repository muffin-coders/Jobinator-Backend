package ch.wintihack.jobinator.persistence.repository;

import ch.wintihack.jobinator.model.Setting;
import org.springframework.data.repository.CrudRepository;

public interface SettingRepository extends CrudRepository<Setting, Integer> {
}
