package llc.synvata.interview.service;

import llc.synvata.interview.core.exception.NoSceneryException;
import llc.synvata.interview.core.exception.ScenarioNotFoundException;
import llc.synvata.interview.domain.Scenario;
import llc.synvata.interview.domain.entity.ScenarioEntity;
import llc.synvata.interview.repository.ScenarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import static llc.synvata.interview.domain.Scenario.buildFromScenarioEntity;

@Service
public class ScenarioServiceImpl implements ScenarioService {

    private final Logger logger = Logger.getLogger(ScenarioServiceImpl.class.getName());

    @Autowired
    private ScenarioRepository scenarioRepository;

    @Override
    public Scenario findByScenario(String idScenario) {
        logger.info("Start service find scenario: " + idScenario);
        Optional<ScenarioEntity> scenarioEntity = scenarioRepository.findById(idScenario);
        if(scenarioEntity.isEmpty()) throw new ScenarioNotFoundException(idScenario);
        Scenario scenario = buildFromScenarioEntity(scenarioEntity.get());
        logger.info("End service find scenario: " + idScenario + ", object data: " + scenario);
        return scenario;
    }

    @Override
    public List<Scenario> findScenarios() {
        logger.info("Start service find scenario: ");
        List<ScenarioEntity> scenariosEntity = scenarioRepository.findAll();
        if(scenariosEntity == null || scenariosEntity.isEmpty()) throw new NoSceneryException();
        List<Scenario> scenarios = new ArrayList<>(scenariosEntity.size());
        for(ScenarioEntity scenario : scenariosEntity){
            scenarios.add(buildFromScenarioEntity(scenario));
        }
        logger.info("End service find scenario, object data: " + scenarios);
        return scenarios;
    }

    @Override
    public Scenario saveScenario(String idScenario, Scenario scenario) {
        logger.info("Start service save scenario: " + idScenario + ", object data: " + scenario);
        Optional<ScenarioEntity> optionalScenario = scenarioRepository.findById(idScenario);
        ScenarioEntity scenarioEntity = null;
        if(optionalScenario.isEmpty()) {
            scenarioEntity = new ScenarioEntity();
            scenarioEntity.setId(idScenario);
            scenarioEntity.setPeriods(scenario.getPeriods());
            scenarioEntity.setIndexA(scenario.getIndexA());
            scenarioEntity.setIndexB(scenario.getIndexB());
            scenarioEntity.setIndexC(scenario.getIndexC());
            scenarioEntity.setIndexD(scenario.getIndexD());
            scenarioEntity.setAmount(scenario.getAmount());
            scenarioEntity.setAccumulated(scenario.getAccumulated());
            scenarioEntity.setObtained(scenario.getObtained());
            scenarioEntity.setCalculatedTotal(scenario.getCalculatedTotal());
        } else {
            scenarioEntity = optionalScenario.get();
            scenarioEntity.setPeriods(scenario.getPeriods());
            scenarioEntity.setIndexA(scenario.getIndexA());
            scenarioEntity.setIndexC(scenario.getIndexC());
            scenarioEntity.setAmount(scenario.getAmount());
        }
        scenarioEntity = scenarioRepository.saveAndFlush(scenarioEntity);
        logger.info("End service save scenario, object data: " + scenarioEntity);
        return buildFromScenarioEntity(scenarioEntity);
    }
}
