package com.etraveli.movierental.validator;

import com.etraveli.movierental.exception.InvalidInputException;
import com.etraveli.movierental.model.Customer;

public class CustomerValidator implements Validator<Customer> {
    @Override
    public void validate(Customer customer) {
        if(customer == null) throw new InvalidInputException("Customer is null");
        if(customer.getName() == null || customer.getName().isBlank() ) throw new InvalidInputException("Customer name is null or blank");

    }
}
