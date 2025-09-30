package com.etraveli.movierental.service.charge.strategy;

import com.etraveli.movierental.model.MovieType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegularMovieChargeServiceTest {
    RentalChargeService regularMovieChargeService;
    @BeforeEach
    void setUp() {
        regularMovieChargeService= RentalChargeServiceFactory.getRentalChargeService(MovieType.REGULAR);
    }

    @Test
    void calculateCharge() {
        assertEquals(2.0,regularMovieChargeService.calculateCharge(2));
        assertEquals(6.5,regularMovieChargeService.calculateCharge(5));
    }

    @Test
    void calculateFrequentEnterPoints() {
        assertEquals(1,regularMovieChargeService.calculateFrequentEnterPoints(5));
    }
}