package llc.synvata.interview.core.exception;

import static java.lang.String.format;

public class ScenarioNotFoundException extends RuntimeException {

    private static final String ERROR_MESSAGE = "Scenario Not Found, ID [ %s ] ";

    public ScenarioNotFoundException(String idScenario){
        super(format(ERROR_MESSAGE, idScenario));
    }

}
