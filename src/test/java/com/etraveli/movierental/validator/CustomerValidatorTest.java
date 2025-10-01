package com.etraveli.movierental.validator;

import com.etraveli.movierental.exception.InvalidInputException;
import com.etraveli.movierental.model.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CustomerValidatorTest {
    private Validator<Customer> customerValidator;

    @BeforeEach
    void setUp() {
        customerValidator = new CustomerValidator();
    }

    @Test
    void validateWhenCustomerIsNullThenThrowInvalidInputException() {
        InvalidInputException invalidInputException = assertThrows(InvalidInputException.class, () -> customerValidator.validate(null));
        assertEquals("Customer is null", invalidInputException.getMessage());
    }

    @Test
    void validateWhenCustomerNameIsNullThenThrowInvalidInputException() {
        InvalidInputException invalidInputException = assertThrows(InvalidInputException.class, () -> customerValidator.validate(new Customer(null, null)));
        assertEquals("Customer name is null or blank", invalidInputException.getMessage());
    }

    @Test
    void validateWhenCustomerNameIsBlankOrEmptyThenThrowInvalidInputException() {
        InvalidInputException invalidInputException = assertThrows(InvalidInputException.class, () -> customerValidator.validate(new Customer("", null)));
        assertEquals("Customer name is null or blank", invalidInputException.getMessage());
        invalidInputException = assertThrows(InvalidInputException.class, () -> customerValidator.validate(new Customer("   ", null)));
        assertEquals("Customer name is null or blank", invalidInputException.getMessage());
    }
}