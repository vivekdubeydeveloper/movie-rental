package com.etraveli.movierental.service.statement;

import com.etraveli.movierental.model.Customer;
import com.etraveli.movierental.model.MovieRental;
import com.etraveli.movierental.validator.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * This class is proxy class for RentalStatementService class
 * Here we are doing preprocessing i.e. data validation
 * if preprocessing fails it throws exceptions
 * else call to RentalStatementService to generate the statement
 * @author vivek
 */
public class RentalStatementProxyServiceImpl implements RentalStatementService {
    private static final Logger log = LogManager.getLogger(RentalStatementProxyServiceImpl.class);
    private final RentalStatementService rentalStatementService;
    private final Validator<List<MovieRental>> movieValidator;
    private final Validator<Customer> customerValidator;

    public RentalStatementProxyServiceImpl(RentalStatementService rentalStatementService, Validator<List<MovieRental>> movieValidator, Validator<Customer> customerValidator) {
        this.rentalStatementService = rentalStatementService;
        this.movieValidator = movieValidator;
        this.customerValidator = customerValidator;
    }

    /**
     * This function validates the customer object if object is invalid it throws exception
     * if Object is valid it calls RentalStatementService for rental statement generation
     * @param customer customer name with rental details
     * @return statement string
     */
    @Override
    public String statement(Customer customer) {
        validateInput(customer);
        log.info("Customer :{} input is valid",customer.name());
        return rentalStatementService.statement(customer);
    }

    //validate customer object and movie rentals list
    private void validateInput(Customer customer) {
        customerValidator.validate(customer);
        movieValidator.validate(customer.rentals());
    }
}
