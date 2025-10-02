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
        assertEquals(ValidationErrors.CUSTOMER_NULL.getMessage(), invalidInputException.getMessage());
    }

    @Test
    void validateWhenCustomerNameIsNullThenThrowInvalidInputException() {
        InvalidInputException invalidInputException = assertThrows(InvalidInputException.class, () -> customerValidator.validate(new Customer(null, null)));
        assertEquals(ValidationErrors.CUSTOMER_NAME_NULL_OR_BLANK.getMessage(), invalidInputException.getMessage());
    }

    @Test
    void validateWhenCustomerNameIsBlankOrEmptyThenThrowInvalidInputException() {
        InvalidInputException invalidInputException = assertThrows(InvalidInputException.class, () -> customerValidator.validate(new Customer("", null)));
        assertEquals(ValidationErrors.CUSTOMER_NAME_NULL_OR_BLANK.getMessage(), invalidInputException.getMessage());
        invalidInputException = assertThrows(InvalidInputException.class, () -> customerValidator.validate(new Customer("   ", null)));
        assertEquals(ValidationErrors.CUSTOMER_NAME_NULL_OR_BLANK.getMessage(), invalidInputException.getMessage());
    }
}