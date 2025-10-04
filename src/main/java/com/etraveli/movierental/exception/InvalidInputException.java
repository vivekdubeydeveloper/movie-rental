package com.etraveli.movierental.exception;

/**
 * Custom Exception for invalid Input
 * @author vivek
 */
public class InvalidInputException extends RuntimeException{
    public InvalidInputException(String message) {
        super(message);
    }
}
