package com.etraveli.movierental;

import com.etraveli.movierental.dao.MovieDAO;
import com.etraveli.movierental.model.Customer;
import com.etraveli.movierental.model.InputType;
import com.etraveli.movierental.model.MovieType;
import com.etraveli.movierental.service.charge.strategy.*;
import com.etraveli.movierental.service.format.StatementTextFormatterServiceImpl;
import com.etraveli.movierental.service.statement.RentalStatementProxyServiceImpl;
import com.etraveli.movierental.service.statement.RentalStatementServiceImpl;
import com.etraveli.movierental.service.user.input.*;
import com.etraveli.movierental.validator.CustomerValidator;
import com.etraveli.movierental.validator.MovieValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.EnumMap;
import java.util.Map;

/**
 * This is Main class which is entry point for the application.
 * This class do initial setup required for running the application.
 * This class create all the required dependencies and inject in other classes
 * We can run this class to test the application
 * This class does not contain any business logic so this is not in the scope of testing
 * @author vivek
 */
public class Main {
    private static final Logger log = LogManager.getLogger(Main.class);

    /**
     * This is the method which will be called by JVM when it starts.
     * This method is an entry point for our core business logic
     * In this method we are creating all the dependencies with the help of helper method,injecting in the appropriate object
     * In this method we are creating input for application
     * @param args command line arguments for program
     */
    public static void main(String[] args) {
        try {
            //Customer customer = takeInput(InputType.FIXED);
            Customer customer = getCustomerFromInput(InputType.USER_INPUT);

            //This line is calling business method with customer
            String result = new RentalStatementProxyServiceImpl(new RentalStatementServiceImpl(new MovieDAO(), new StatementTextFormatterServiceImpl(), new RentalChargeServiceResolverImpl(createRentalChargeServiceCache())), new MovieValidator(), new CustomerValidator()).statement(customer);
            log.debug(result);
        } catch (Exception e) {
            log.error("Error in statement generation", e);
        }
    }

    // Create customer object from input
    private static Customer getCustomerFromInput(InputType inputType) {
        InputServiceResolver inputServiceResolver = createInputServiceResolver();
        InputService userInputService = inputServiceResolver.resolve(inputType);

        return userInputService.takeInput();
    }

    // Create map of Input factory and inject in InputServiceResolver.
    //  Based on the input type InputServiceResolver will return appropriate InputService class
    private static InputServiceResolver createInputServiceResolver() {
        Map<InputType, InputService> inputServiceCache = new EnumMap<>(InputType.class);
        inputServiceCache.put(InputType.FIXED, new FixedInputServiceImpl());
        inputServiceCache.put(InputType.USER_INPUT, new UserInputServiceImpl());
        return new InputServiceResolverImpl(inputServiceCache);
    }

    //Create factory map  of RentalChargeServices
    private static Map<MovieType, RentalChargeService> createRentalChargeServiceCache() {
        Map<MovieType, RentalChargeService> rentalChargeServiceCache = new EnumMap<>(MovieType.class);
        rentalChargeServiceCache.put(MovieType.NEW, new NewMovieChargeService());
        rentalChargeServiceCache.put(MovieType.REGULAR, new RegularMovieChargeService());
        rentalChargeServiceCache.put(MovieType.CHILDREN, new ChildrenMovieChargeService());
        return rentalChargeServiceCache;
    }
}