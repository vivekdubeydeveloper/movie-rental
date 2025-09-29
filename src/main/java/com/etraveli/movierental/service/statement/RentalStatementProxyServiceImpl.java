package com.etraveli.movierental.service.statement;

import com.etraveli.movierental.dao.MovieDAO;
import com.etraveli.movierental.model.Customer;
import com.etraveli.movierental.model.MovieRental;
import com.etraveli.movierental.validator.CustomerValidator;
import com.etraveli.movierental.validator.MovieValidator;
import com.etraveli.movierental.validator.Validator;

import java.util.List;

public class RentalStatementProxyServiceImpl implements RentalStatementService {
    private final RentalStatementService rentalStatementService;
    private final Validator<List<MovieRental>> movieValidator;
    private final Validator<Customer> customerValidator;

    public RentalStatementProxyServiceImpl() {
        this.customerValidator = new CustomerValidator();
        this.movieValidator = new MovieValidator(new MovieDAO());
        this.rentalStatementService = new RentalStatementServiceImpl();
    }

    @Override
    public String statement(Customer customer) {
        validateInput(customer);
        return rentalStatementService.statement(customer);
    }

    private void validateInput(Customer customer) {
        //TODO:need to handle this properly
        customerValidator.validate(customer);
        //TODO:Need to think if validate a single record or multiple records
        //Here multiple record validation seems efficient
        movieValidator.validate(customer.rentals());
    }
}
