package com.etraveli.movierental;

import com.etraveli.movierental.dao.MovieDAO;
import com.etraveli.movierental.model.Customer;
import com.etraveli.movierental.model.MovieRental;
import com.etraveli.movierental.model.MovieType;
import com.etraveli.movierental.service.charge.strategy.*;
import com.etraveli.movierental.service.format.StatementTextFormatterServiceImpl;
import com.etraveli.movierental.service.statement.RentalStatementProxyServiceImpl;
import com.etraveli.movierental.service.statement.RentalStatementServiceImpl;
import com.etraveli.movierental.validator.CustomerValidator;
import com.etraveli.movierental.validator.MovieValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;

/**
 * This is Main class which is entry point for the code.
 * We can run this class to test the application
 * @author vivek
 */
public class Main {
    private static final Logger log = LogManager.getLogger(Main.class);
    /**
     * This is the method which will be called by JVM when it starts.
     * From this method is entry point for our core business logic,we are validating response here
     * @param args arguments for program
     */
    public static void main(String[] args) {
        Map<MovieType, RentalChargeService> rentalChargeServiceCache = new EnumMap<>(MovieType.class);
        rentalChargeServiceCache.put(MovieType.NEW,new NewMovieChargeService());
        rentalChargeServiceCache.put(MovieType.REGULAR,new RegularMovieChargeService());
        rentalChargeServiceCache.put(MovieType.CHILDREN,new ChildrenMovieChargeService());
        try {
           String result = new RentalStatementProxyServiceImpl(new RentalStatementServiceImpl(new MovieDAO(),new StatementTextFormatterServiceImpl(),new RentalChargeServiceResolverImpl(rentalChargeServiceCache)),new MovieValidator(),new CustomerValidator()).statement(new Customer("C. U. Stomer", Arrays.asList(new MovieRental("F001", 3), new MovieRental("F002", 1))));
            log.debug(result);
        } catch (Exception e) {
            log.error("Error in statement generation", e);
        }
    }
}