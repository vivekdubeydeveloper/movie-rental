package com.etraveli.movierental.service.charge.strategy;

import com.etraveli.movierental.model.MovieType;

import java.util.Map;

public record RentalChargeServiceResolverImpl(
        Map<MovieType, RentalChargeService> rentalChargeServiceCache) implements RentalChargeServiceResolver {

    @Override
    public RentalChargeService resolve(MovieType movieType) {
        return rentalChargeServiceCache.get(movieType);
    }
}
