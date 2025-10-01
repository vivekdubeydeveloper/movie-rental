package com.etraveli.movierental.service.charge.strategy;

import com.etraveli.movierental.model.MovieType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegularMovieChargeServiceTest {
    private RentalChargeService regularMovieChargeService;
    @BeforeEach
    void setUp() {
        regularMovieChargeService= RentalChargeServiceFactory.getRentalChargeService(MovieType.REGULAR);
    }

    @Test
    void calculateChargeWhenDaysRentedIsLessThanEqualToTwoThenGetExpectedOutput() {
        assertEquals(2.0,regularMovieChargeService.calculateCharge(1));
        assertEquals(2.0,regularMovieChargeService.calculateCharge(2));
    }

    @Test
    void calculateChargeWhenDaysRentedIsGreaterThanTwoThenGetExpectedOutput() {
        assertEquals(6.5,regularMovieChargeService.calculateCharge(5));
    }

    @Test
    void calculateFrequentEnterPointsWhenDaysRentedIsPositiveThenGetExpectedOutput() {
        assertEquals(1,regularMovieChargeService.calculateFrequentEnterPoints(5));
    }
}