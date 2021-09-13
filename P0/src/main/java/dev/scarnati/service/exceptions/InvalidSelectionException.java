package dev.scarnati.service.exceptions;

public class InvalidSelectionException extends Exception {
    public InvalidSelectionException(String errorMessage) {
        super(errorMessage);
    }
}
