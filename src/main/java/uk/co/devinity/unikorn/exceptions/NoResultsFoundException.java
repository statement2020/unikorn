package uk.co.devinity.unikorn.exceptions;

public class NoResultsFoundException extends TestFailureException {
    public NoResultsFoundException(final String msg,
                                   final Throwable ex) {
        super(msg, ex);
    }
}
