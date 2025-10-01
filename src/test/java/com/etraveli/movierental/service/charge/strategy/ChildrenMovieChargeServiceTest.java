package com.etraveli.movierental.service.charge.strategy;

import com.etraveli.movierental.model.MovieType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ChildrenMovieChargeServiceTest {
    private RentalChargeService ChildrenMovieChargeService;

    @BeforeEach
    void setUp() {
        ChildrenMovieChargeService = RentalChargeServiceFactory.getRentalChargeService(MovieType.CHILDRENS);
    }

    @Test
    void calculateChargeWhenDaysRentedLessThanOrEqualToThreeThenGetExpectedOutput() {
        assertEquals(1.5, ChildrenMovieChargeService.calculateCharge(2));
        assertEquals(1.5, ChildrenMovieChargeService.calculateCharge(3));
    }

    @Test
    void calculateChargeWhenDaysRentedGreaterThanThreeThenGetExpectedOutput() {
        assertEquals(4.5, ChildrenMovieChargeService.calculateCharge(5));
    }

    @Test
    void calculateFrequentEnterPointsWhenDaysRentedIsPositiveThenGetExpectedOutputOne() {
        assertEquals(1, ChildrenMovieChargeService.calculateFrequentEnterPoints(2));
    }
}