package uk.co.devinity.unikorn.exceptions;

public abstract class TestFailureException extends RuntimeException {
    public TestFailureException(final String message,
                                final Throwable cause) {
        super(message, cause);
    }
}
