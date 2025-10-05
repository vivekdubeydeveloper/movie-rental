package com.etraveli.movierental.exception;

/**
 * Custom Exception if movie not found in DB
 *
 * @author vivek
 */
public class MovieNotFoundException extends RuntimeException {
    public MovieNotFoundException(String message) {
        super(message);
    }
}
