package com.etraveli.movierental.validator;

/**
 * This is generic interface which will be used for validating the data
 * @author vivek
 * @param <T> any type of entity
 */
public interface Validator<T> {
    void validate(T t);
}
