package com.etraveli.movierental.validator;

/**
 * This is generic interface which will be used for validating the data
 *
 * @param <T> any type of entity
 * @author vivek
 */
public interface Validator<T> {
    void validate(T t);
}
