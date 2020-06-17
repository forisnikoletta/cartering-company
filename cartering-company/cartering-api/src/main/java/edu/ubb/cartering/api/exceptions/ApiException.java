package edu.ubb.cartering.api.exceptions;

public class ApiException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ApiException(final String string) {
        super(string);
    }

    public ApiException(final String string, final Throwable throwable) {
        super(string, throwable);
    }

}