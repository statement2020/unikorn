package uk.co.devinity.unikorn.exceptions;

public class CouldNotExecuteQueryException extends TestFailureException {

    public CouldNotExecuteQueryException(final String message,
                                         final Throwable cause) {
        super(message, cause);
    }
}
