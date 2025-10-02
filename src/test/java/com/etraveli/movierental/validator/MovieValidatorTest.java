package com.etraveli.movierental.validator;

import com.etraveli.movierental.exception.InvalidInputException;
import com.etraveli.movierental.model.MovieRental;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MovieValidatorTest {
    private Validator<List<MovieRental>>  movieValidator;
    @BeforeEach
    void setUp() {
        movieValidator=new MovieValidator();
    }

    @Test
    void validateWhenMovieRentalIsNullOrEmptyThenThrowInvalidInputException() {
        InvalidInputException invalidInputException = assertThrows(InvalidInputException.class, () -> movieValidator.validate( null));
        assertEquals(ValidationErrors.MOVIE_RENTAL_NULL_OR_EMPTY.getMessage(),invalidInputException.getMessage());
        invalidInputException = assertThrows(InvalidInputException.class, () -> movieValidator.validate( Collections.EMPTY_LIST));
        assertEquals(ValidationErrors.MOVIE_RENTAL_NULL_OR_EMPTY.getMessage(),invalidInputException.getMessage());
    }

    @Test
    void validateWhenMovieIdIsNullOrEmptyThenThrowInvalidInputException() {
        InvalidInputException invalidInputException = assertThrows(InvalidInputException.class, () -> movieValidator.validate(List.of(new MovieRental(null,2))));
        assertEquals(ValidationErrors.MOVIE_ID_NULL_OR_EMPTY.getMessage(),invalidInputException.getMessage());
        invalidInputException = assertThrows(InvalidInputException.class, () -> movieValidator.validate(List.of(new MovieRental("",2))));
        assertEquals(ValidationErrors.MOVIE_ID_NULL_OR_EMPTY.getMessage(),invalidInputException.getMessage());
        invalidInputException = assertThrows(InvalidInputException.class, () -> movieValidator.validate(List.of(new MovieRental("  ",2))));
        assertEquals(ValidationErrors.MOVIE_ID_NULL_OR_EMPTY.getMessage(),invalidInputException.getMessage());
    }

    @Test
    void validateWhenRentalDaysAreInvalidThenThrowInvalidInputException() {
        InvalidInputException invalidInputException = assertThrows(InvalidInputException.class, () -> movieValidator.validate( List.of(new MovieRental("F001",0))));
        assertEquals(ValidationErrors.RENTAL_DAYS_SHOULD_BE_POSITIVE.getMessage(),invalidInputException.getMessage());
        invalidInputException = assertThrows(InvalidInputException.class, () -> movieValidator.validate(List.of(new MovieRental("F001",-2))));
        assertEquals(ValidationErrors.RENTAL_DAYS_SHOULD_BE_POSITIVE.getMessage(),invalidInputException.getMessage());
    }
}