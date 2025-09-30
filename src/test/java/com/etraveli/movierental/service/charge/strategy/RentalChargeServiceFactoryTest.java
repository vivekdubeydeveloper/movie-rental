package com.etraveli.movierental.service.charge.strategy;

import com.etraveli.movierental.model.MovieType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RentalChargeServiceFactoryTest {

    @Test
    void getRentalChargeService() {
        assertTrue(RentalChargeServiceFactory.getRentalChargeService(MovieType.CHILDRENS) instanceof ChildrenMovieChargeService);
        assertTrue(RentalChargeServiceFactory.getRentalChargeService(MovieType.REGULAR) instanceof RegularMovieChargeService);
        assertTrue(RentalChargeServiceFactory.getRentalChargeService(MovieType.NEW) instanceof NewMovieChargeService);
    }
}