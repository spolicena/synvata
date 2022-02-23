package llc.synvata.interview.core.exception;

import static java.lang.String.format;

public class NoSceneryException extends RuntimeException {

    private static final String ERROR_MESSAGE = "No Scenery Found";

    public NoSceneryException(){
        super(format(ERROR_MESSAGE));
    }

}
