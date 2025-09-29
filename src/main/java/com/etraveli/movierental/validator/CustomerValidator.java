package com.etraveli.movierental.validator;

import com.etraveli.movierental.exception.InvalidInputException;
import com.etraveli.movierental.model.Customer;

import java.util.Objects;

public class CustomerValidator implements Validator<Customer> {
    @Override
    public void validate(Customer customer) {
        if(Objects.isNull(customer)) throw new InvalidInputException("Customer is null");
        if(Objects.isNull(customer.name()) || customer.name().isBlank() ) throw new InvalidInputException("Customer name is null or blank");

    }
}
