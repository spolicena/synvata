package llc.synvata.interview.service;

import llc.synvata.interview.domain.Scenario;

import java.util.List;

public interface ScenarioService {

    Scenario findByScenario(String idScenario);

    List<Scenario> findScenarios();

    Scenario saveScenario(String idScenario, Scenario scenario);
}
