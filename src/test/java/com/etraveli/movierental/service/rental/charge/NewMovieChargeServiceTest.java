package com.etraveli.movierental.service.rental.charge;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NewMovieChargeServiceTest {
    private RentalChargeService newMovieChargeService;
    @BeforeEach
    void setUp() {
        newMovieChargeService= new NewMovieChargeService();
    }

    @Test
    void calculateChargeWhenDaysRentedIsPositiveNumberThenGetExpectedOutput() {
        assertEquals(15,newMovieChargeService.calculateCharge(5));
    }

    @Test
    void calculateFrequentEnterPointsWhenDaysRentedIsPositiveNumberLessThanOrEqualToTwoThenGetExpectedOutput() {
        assertEquals(1,newMovieChargeService.calculateFrequentEnterPoints(1));
        assertEquals(1,newMovieChargeService.calculateFrequentEnterPoints(2));
    }

    @Test
    void calculateFrequentEnterPointsWhenDaysRentedIsPositiveNumberGreaterThanTwoThenGetExpectedOutput() {
        assertEquals(2,newMovieChargeService.calculateFrequentEnterPoints(5));
    }
}