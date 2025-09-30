package com.etraveli.movierental.validator;

import com.etraveli.movierental.exception.InvalidInputException;
import com.etraveli.movierental.model.MovieRental;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MovieValidatorTest {
    Validator<List<MovieRental>>  movieValidator;
    @BeforeEach
    void setUp() {
        movieValidator=new MovieValidator();
    }

    @Test
    void validateWhenMovieRentalIsNullOrEmptyThenThrowInvalidInputException() {
        InvalidInputException invalidInputException = assertThrows(InvalidInputException.class, () -> movieValidator.validate( null));
        assertEquals("Movie Rentals can't be null or empty",invalidInputException.getMessage());
        invalidInputException = assertThrows(InvalidInputException.class, () -> movieValidator.validate( Collections.EMPTY_LIST));
        assertEquals("Movie Rentals can't be null or empty",invalidInputException.getMessage());
    }

    @Test
    void validateWhenMovieIdIsNullOrEmptyThenThrowInvalidInputException() {
        InvalidInputException invalidInputException = assertThrows(InvalidInputException.class, () -> movieValidator.validate(List.of(new MovieRental(null,2))));
        assertEquals("Movie Id can't be null or empty",invalidInputException.getMessage());
        invalidInputException = assertThrows(InvalidInputException.class, () -> movieValidator.validate(List.of(new MovieRental("",2))));
        assertEquals("Movie Id can't be null or empty",invalidInputException.getMessage());
        invalidInputException = assertThrows(InvalidInputException.class, () -> movieValidator.validate(List.of(new MovieRental("  ",2))));
        assertEquals("Movie Id can't be null or empty",invalidInputException.getMessage());
    }

    @Test
    void validateWhenRentalDaysAreInvalidThenThrowInvalidInputException() {
        InvalidInputException invalidInputException = assertThrows(InvalidInputException.class, () -> movieValidator.validate( List.of(new MovieRental("F001",0))));
        assertEquals("Rental days should be positive",invalidInputException.getMessage());
        invalidInputException = assertThrows(InvalidInputException.class, () -> movieValidator.validate(List.of(new MovieRental("F001",-2))));
        assertEquals("Rental days should be positive",invalidInputException.getMessage());
    }
}