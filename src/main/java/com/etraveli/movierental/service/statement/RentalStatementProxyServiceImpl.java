package com.etraveli.movierental.service.statement;

import com.etraveli.movierental.model.Customer;
import com.etraveli.movierental.model.MovieRental;
import com.etraveli.movierental.validator.Validator;

import java.util.List;

/**
 * This class is proxy for actual business logic class
 * Here we are doing data validation before calling actual method
 *
 * @author vivek
 */
public class RentalStatementProxyServiceImpl implements RentalStatementService {
    private final RentalStatementService rentalStatementService;
    private final Validator<List<MovieRental>> movieValidator;
    private final Validator<Customer> customerValidator;

    public RentalStatementProxyServiceImpl(RentalStatementService rentalStatementService, Validator<List<MovieRental>> movieValidator, Validator<Customer> customerValidator) {
        this.rentalStatementService = rentalStatementService;
        this.movieValidator = movieValidator;
        this.customerValidator = customerValidator;
    }

    @Override
    public String statement(Customer customer) {
        validateInput(customer);
        return rentalStatementService.statement(customer);
    }

    private void validateInput(Customer customer) {
        customerValidator.validate(customer);
        movieValidator.validate(customer.rentals());
    }
}
