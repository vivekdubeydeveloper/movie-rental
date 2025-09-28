package com.etraveli.movierental.validator;

public interface Validator<T> {
    public void validate(T t);
}
