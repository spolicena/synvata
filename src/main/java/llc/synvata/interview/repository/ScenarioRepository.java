package llc.synvata.interview.repository;

import llc.synvata.interview.domain.entity.ScenarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ScenarioRepository extends JpaRepository<ScenarioEntity, String> {

    @Query("select scenarioEntity FROM ScenarioEntity scenarioEntity " +
            "WHERE scenarioEntity.id = ?1 ")
    Optional<ScenarioEntity> findById(String idScenario);

}
