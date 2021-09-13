package dev.scarnati.exceptions;

public class UsernameDoesNotExistException extends Exception {
    public UsernameDoesNotExistException(String errorMessage) {
        super(errorMessage);
    }
}
