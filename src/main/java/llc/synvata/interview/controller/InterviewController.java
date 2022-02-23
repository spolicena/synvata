package llc.synvata.interview.controller;

import llc.synvata.interview.domain.Scenario;
import llc.synvata.interview.service.ScenarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@RestController
@RequestMapping("/interview")
public class InterviewController {

    private final Logger logger = Logger.getLogger(InterviewController.class.getName());

    @Autowired
    private ScenarioService scenarioService;

    @GetMapping("/{id_scenario}")
    public ResponseEntity<Object> getScenario(@PathVariable("id_scenario")
                                                      String idScenario){
        logger.info("Start endpoint get scenario: " + idScenario);
        Object response = null;
        try{
            response = scenarioService.findByScenario(idScenario);
            return ResponseEntity.ok().body(response);
        } catch (Exception ex){
            logger.severe(ex.getMessage());
            return ResponseEntity.notFound().build();
        }
        finally {
            logger.info("End endpoint get scenario: " + idScenario + ", result: " + response);
        }
    }

    @GetMapping("/allScenarios")
    public ResponseEntity<Object> getScenario(){
        logger.info("Start endpoint get all scenarios");
        Object response = null;
        try{
            response = scenarioService.findScenarios();
            return ResponseEntity.ok().body(response);
        } catch (Exception ex){
            logger.severe(ex.getMessage());
            return ResponseEntity.notFound().build();
        }
        finally {
            logger.info("End endpoint get all scenarios , result: " + response);
        }
    }

    @PostMapping("/{id_scenario}")
    public ResponseEntity<Object> saveScenario(@PathVariable(name = "id_scenario") String idScenario,
                                               @RequestBody Scenario scenario){
        logger.info("Start endpoint post save scenario");
        Object response = null;
        try{
            response = scenarioService.saveScenario(idScenario, scenario);
            return ResponseEntity.ok().body(response);
        } catch (Exception ex){
            logger.severe(ex.getMessage());
            return ResponseEntity.notFound().build();
        }
        finally {
            logger.info("End endpoint post save scenario: " + response);
        }
    }

}
