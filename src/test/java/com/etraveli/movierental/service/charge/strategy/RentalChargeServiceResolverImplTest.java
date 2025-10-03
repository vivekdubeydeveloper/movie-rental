package com.etraveli.movierental.service.charge.strategy;

import com.etraveli.movierental.model.MovieType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.EnumMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class RentalChargeServiceResolverImplTest {
private RentalChargeServiceResolver rentalChargeServiceResolver;
    @BeforeEach
    void setUp() {
        Map<MovieType, RentalChargeService> rentalChargeServiceCache = new EnumMap<>(MovieType.class);
        rentalChargeServiceCache.put(MovieType.NEW,new NewMovieChargeService());
        rentalChargeServiceCache.put(MovieType.REGULAR,new RegularMovieChargeService());
        rentalChargeServiceCache.put(MovieType.CHILDREN,new ChildrenMovieChargeService());
        rentalChargeServiceResolver=new RentalChargeServiceResolverImpl(rentalChargeServiceCache);
    }

    @Test
    void resolveWhenGivingCorrectMovieTypeInputThenGetExpectedOutput() {
        assertInstanceOf(NewMovieChargeService.class,rentalChargeServiceResolver.resolve(MovieType.NEW));
        assertInstanceOf(RegularMovieChargeService.class,rentalChargeServiceResolver.resolve(MovieType.REGULAR));
        assertInstanceOf(ChildrenMovieChargeService.class,rentalChargeServiceResolver.resolve(MovieType.CHILDREN));
    }
}