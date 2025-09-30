package com.etraveli.movierental.service.statement;

import com.etraveli.movierental.exception.MovieNotFoundException;
import com.etraveli.movierental.model.Customer;
import com.etraveli.movierental.model.MovieRental;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RentalStatementServiceImplTest {
RentalStatementService rentalStatementService;
    @BeforeEach
    void setUp() {
        rentalStatementService=new RentalStatementServiceImpl();
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