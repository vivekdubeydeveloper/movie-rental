package com.etraveli.movierental.validator;

import com.etraveli.movierental.exception.InvalidInputException;
import com.etraveli.movierental.model.Customer;

import java.util.Objects;

/**
 * This class will validate customer data
 *
 * @author vivek
 */
public class CustomerValidator implements Validator<Customer> {
    /**
     * This method checks if customer is null or customer name is blank then throws InvalidInputException
     *
     * @param customer represent customer object
     */
    @Override
    public void validate(Customer customer) {
        if (Objects.isNull(customer)) throw new InvalidInputException(ValidationErrors.CUSTOMER_NULL.getMessage());
        if (Objects.isNull(customer.name()) || customer.name().isBlank())
            throw new InvalidInputException(ValidationErrors.CUSTOMER_NAME_NULL_OR_BLANK.getMessage());
    }
}
