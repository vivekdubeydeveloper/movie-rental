package com.etraveli.movierental.service.charge.strategy;

import com.etraveli.movierental.model.MovieType;

import java.util.Map;

public class RentalChargeServiceResolverImpl implements RentalChargeServiceResolver {
    private final Map<MovieType, RentalChargeService> rentalChargeServiceCache;

    public RentalChargeServiceResolverImpl(Map<MovieType, RentalChargeService> rentalChargeServiceCache) {
        this.rentalChargeServiceCache = rentalChargeServiceCache;
    }

    @Override
    public RentalChargeService resolve(MovieType movieType) {
        return rentalChargeServiceCache.get(movieType);
    }
}
