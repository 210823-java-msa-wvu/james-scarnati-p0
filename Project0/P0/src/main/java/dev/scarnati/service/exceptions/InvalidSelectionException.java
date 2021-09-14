package dev.scarnati.service.exceptions;

public class InvalidSelectionException extends RuntimeException {
    public InvalidSelectionException(String errorMessage) {
        super(errorMessage);
    }
}
