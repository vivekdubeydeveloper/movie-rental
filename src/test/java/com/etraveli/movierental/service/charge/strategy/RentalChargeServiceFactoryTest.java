package com.etraveli.movierental.service.charge.strategy;

import com.etraveli.movierental.model.MovieType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RentalChargeServiceFactoryTest {

    @Test
    void getRentalChargeServiceWhenGivingCorrectMovieTypeThenGetExpectedRentalChargeService() {
        assertInstanceOf(ChildrenMovieChargeService.class, RentalChargeServiceFactory.getRentalChargeService(MovieType.CHILDRENS));
        assertInstanceOf(RegularMovieChargeService.class, RentalChargeServiceFactory.getRentalChargeService(MovieType.REGULAR));
        assertInstanceOf(NewMovieChargeService.class, RentalChargeServiceFactory.getRentalChargeService(MovieType.NEW));
    }
}