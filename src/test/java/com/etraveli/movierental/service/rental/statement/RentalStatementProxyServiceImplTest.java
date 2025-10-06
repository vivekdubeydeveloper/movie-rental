package com.etraveli.movierental.service.rental.statement;

import com.etraveli.movierental.dao.MovieDAO;
import com.etraveli.movierental.exception.InvalidInputException;
import com.etraveli.movierental.exception.MovieNotFoundException;
import com.etraveli.movierental.model.Customer;
import com.etraveli.movierental.model.MovieRental;
import com.etraveli.movierental.model.MovieType;
import com.etraveli.movierental.service.rental.statement.formatter.StatementTextFormatterServiceImpl;
import com.etraveli.movierental.service.rental.charge.*;
import com.etraveli.movierental.validator.CustomerValidator;
import com.etraveli.movierental.validator.MovieValidator;
import com.etraveli.movierental.validator.ValidationErrors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RentalStatementProxyServiceImplTest {
    private RentalStatementService rentalStatementService;
    @BeforeEach
    void setUp() {
        Map<MovieType, RentalChargeService> rentalChargeServiceCache = new EnumMap<>(MovieType.class);
        rentalChargeServiceCache.put(MovieType.NEW,new NewMovieChargeService());
        rentalChargeServiceCache.put(MovieType.REGULAR,new RegularMovieChargeService());
        rentalChargeServiceCache.put(MovieType.CHILDREN,new ChildrenMovieChargeService());
        rentalStatementService = new RentalStatementProxyServiceImpl(new RentalStatementServiceImpl(new MovieDAO(),new StatementTextFormatterServiceImpl(),new RentalChargeServiceResolverImpl(rentalChargeServiceCache)),new MovieValidator(),new CustomerValidator());
    }

    @Test
    void statementWhenCustomerIsNullThenThrowInvalidInputException() {
        InvalidInputException invalidInputException = assertThrows(InvalidInputException.class, () -> rentalStatementService.statement(null));
        assertEquals(ValidationErrors.CUSTOMER_NULL.getMessage(),invalidInputException.getMessage() );
    }

    @Test
    void statementWhenCustomerNameIsNullThenThrowInvalidInputException() {
        InvalidInputException invalidInputException = assertThrows(InvalidInputException.class, () -> rentalStatementService.statement(new Customer(null, null)));
        assertEquals(ValidationErrors.CUSTOMER_NAME_NULL_OR_BLANK.getMessage(),invalidInputException.getMessage() );
    }

    @Test
    void statementWhenCustomerNameIsBlankOrEmptyThenThrowInvalidInputException() {
        InvalidInputException invalidInputException = assertThrows(InvalidInputException.class, () -> rentalStatementService.statement(new Customer("", null)));
        assertEquals(ValidationErrors.CUSTOMER_NAME_NULL_OR_BLANK.getMessage(),invalidInputException.getMessage());
        invalidInputException = assertThrows(InvalidInputException.class, () -> rentalStatementService.statement(new Customer("   ", null)));
        assertEquals(ValidationErrors.CUSTOMER_NAME_NULL_OR_BLANK.getMessage(), invalidInputException.getMessage());
    }

    @Test
    void statementWhenMovieRentalIsNullOrEmptyThenThrowInvalidInputException() {
        InvalidInputException invalidInputException = assertThrows(InvalidInputException.class, () -> rentalStatementService.statement(new Customer("vivek", null)));
        assertEquals(ValidationErrors.MOVIE_RENTAL_NULL_OR_EMPTY.getMessage(),invalidInputException.getMessage());
        invalidInputException = assertThrows(InvalidInputException.class, () -> rentalStatementService.statement(new Customer("vivek", Collections.EMPTY_LIST)));
        assertEquals(ValidationErrors.MOVIE_RENTAL_NULL_OR_EMPTY.getMessage(),invalidInputException.getMessage());
    }

    @Test
    void statementWhenMovieIdIsNullOrEmptyThenThrowInvalidInputException() {
        InvalidInputException invalidInputException = assertThrows(InvalidInputException.class, () -> rentalStatementService.statement(new Customer("vivek", List.of(new MovieRental(null,2)))));
        assertEquals(ValidationErrors.MOVIE_ID_NULL_OR_EMPTY.getMessage(),invalidInputException.getMessage());
        invalidInputException = assertThrows(InvalidInputException.class, () -> rentalStatementService.statement(new Customer("vivek", List.of(new MovieRental("",2)))));
        assertEquals(ValidationErrors.MOVIE_ID_NULL_OR_EMPTY.getMessage(),invalidInputException.getMessage());
        invalidInputException = assertThrows(InvalidInputException.class, () -> rentalStatementService.statement(new Customer("vivek", List.of(new MovieRental("  ",2)))));
        assertEquals(ValidationErrors.MOVIE_ID_NULL_OR_EMPTY.getMessage(),invalidInputException.getMessage());
    }

    @Test
    void statementWhenRentalDaysAreInvalidThenThrowInvalidInputException() {
        //When rental days are 0 or less than 0
        InvalidInputException invalidInputException = assertThrows(InvalidInputException.class, () -> rentalStatementService.statement(new Customer("vivek", List.of(new MovieRental("F001",0)))));
        assertEquals(ValidationErrors.RENTAL_DAYS_SHOULD_BE_POSITIVE.getMessage(),invalidInputException.getMessage());
        invalidInputException = assertThrows(InvalidInputException.class, () -> rentalStatementService.statement(new Customer("vivek", List.of(new MovieRental("F001",-2)))));
        assertEquals(ValidationErrors.RENTAL_DAYS_SHOULD_BE_POSITIVE.getMessage(),invalidInputException.getMessage());
    }

    @Test
    void statementWhenMovieNotFoundInDBThenThrowMovieNotFoundException() {
        MovieNotFoundException invalidInputException = assertThrows(MovieNotFoundException.class, () -> rentalStatementService.statement(new Customer("vivek", List.of(new MovieRental("F0001",2)))));
        assertEquals("Movie F0001 not found",invalidInputException.getMessage());
    }

    @Test
    void statementWhenInputIsCorrectThenGetExpectedResponse() {
        String expected = "Rental Record for C. U. Stomer\n\tYou've Got Mail\t3.50\n\tMatrix\t2.00\nAmount owed is 5.50\nYou earned 2 frequent points\n";
        assertEquals(expected,rentalStatementService.statement(new Customer("C. U. Stomer", Arrays.asList(new MovieRental("F001", 3), new MovieRental("F002", 1)))));
    }
}