package com.etraveli.movierental.validator;

public interface Validator<T> {
    void validate(T t);
}
