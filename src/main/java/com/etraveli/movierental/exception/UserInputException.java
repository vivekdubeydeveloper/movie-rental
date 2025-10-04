package com.etraveli.movierental.exception;

/**
 * Custom Exception if user is providing wrong input
 * @author vivek
 */
public class UserInputException extends RuntimeException{
    public UserInputException(String message) {
        super(message);
    }
}
