package com.etraveli.movierental.service.statement;

import com.etraveli.movierental.exception.InvalidInputException;
import com.etraveli.movierental.exception.MovieNotFoundException;
import com.etraveli.movierental.model.Customer;
import com.etraveli.movierental.model.MovieRental;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RentalStatementProxyServiceImplTest {
    private RentalStatementService rentalStatementService;
    @BeforeEach
    void setUp() {
        rentalStatementService = new RentalStatementProxyServiceImpl();
    }

    @Test
    void statementWhenCustomerIsNullThenThrowInvalidInputException() {
        InvalidInputException invalidInputException = assertThrows(InvalidInputException.class, () -> rentalStatementService.statement(null));
        assertEquals("Customer is null",invalidInputException.getMessage() );
    }

    @Test
    void statementWhenCustomerNameIsNullThenThrowInvalidInputException() {
        InvalidInputException invalidInputException = assertThrows(InvalidInputException.class, () -> rentalStatementService.statement(new Customer(null, null)));
        assertEquals("Customer name is null or blank",invalidInputException.getMessage() );
    }

    @Test
    void statementWhenCustomerNameIsBlankOrEmptyThenThrowInvalidInputException() {
        InvalidInputException invalidInputException = assertThrows(InvalidInputException.class, () -> rentalStatementService.statement(new Customer("", null)));
        assertEquals("Customer name is null or blank",invalidInputException.getMessage());
        invalidInputException = assertThrows(InvalidInputException.class, () -> rentalStatementService.statement(new Customer("   ", null)));
        assertEquals("Customer name is null or blank", invalidInputException.getMessage());
    }

    @Test
    void statementWhenMovieRentalIsNullOrEmptyThenThrowInvalidInputException() {
        InvalidInputException invalidInputException = assertThrows(InvalidInputException.class, () -> rentalStatementService.statement(new Customer("vivek", null)));
        assertEquals("Movie Rentals can't be null or empty",invalidInputException.getMessage());
        invalidInputException = assertThrows(InvalidInputException.class, () -> rentalStatementService.statement(new Customer("vivek", Collections.EMPTY_LIST)));
        assertEquals("Movie Rentals can't be null or empty",invalidInputException.getMessage());
    }

    @Test
    void statementWhenMovieIdIsNullOrEmptyThenThrowInvalidInputException() {
        InvalidInputException invalidInputException = assertThrows(InvalidInputException.class, () -> rentalStatementService.statement(new Customer("vivek", List.of(new MovieRental(null,2)))));
        assertEquals("Movie Id can't be null or empty",invalidInputException.getMessage());
        invalidInputException = assertThrows(InvalidInputException.class, () -> rentalStatementService.statement(new Customer("vivek", List.of(new MovieRental("",2)))));
        assertEquals("Movie Id can't be null or empty",invalidInputException.getMessage());
        invalidInputException = assertThrows(InvalidInputException.class, () -> rentalStatementService.statement(new Customer("vivek", List.of(new MovieRental("  ",2)))));
        assertEquals("Movie Id can't be null or empty",invalidInputException.getMessage());
    }

    @Test
    void statementWhenRentalDaysAreInvalidThenThrowInvalidInputException() {
        InvalidInputException invalidInputException = assertThrows(InvalidInputException.class, () -> rentalStatementService.statement(new Customer("vivek", List.of(new MovieRental("F001",0)))));
        assertEquals("Rental days should be positive",invalidInputException.getMessage());
        invalidInputException = assertThrows(InvalidInputException.class, () -> rentalStatementService.statement(new Customer("vivek", List.of(new MovieRental("F001",-2)))));
        assertEquals("Rental days should be positive",invalidInputException.getMessage());
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