package edu.ubb.cartering.backend.services;

public class ServiceException extends RuntimeException {

	public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
