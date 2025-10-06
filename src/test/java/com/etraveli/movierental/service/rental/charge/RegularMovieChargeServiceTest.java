package com.etraveli.movierental.service.rental.charge;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RegularMovieChargeServiceTest {
    private RentalChargeService regularMovieChargeService;
    @BeforeEach
    void setUp() {
        regularMovieChargeService= new RegularMovieChargeService();
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