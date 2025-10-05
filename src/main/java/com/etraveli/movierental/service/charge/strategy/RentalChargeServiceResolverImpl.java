package com.etraveli.movierental.service.charge.strategy;

import com.etraveli.movierental.model.MovieType;

import java.util.Map;

/**
 * This record keeps the map with movieType as key and different RentalChargeService implementations as value.
 * It will work as a cached factory when we need implementation of RentalChargeService based on the movie type.
 *
 * @param rentalChargeServiceCache cache for different RentalChargeService implementations based on movie type
 * @author vivek
 */
public record RentalChargeServiceResolverImpl(
        Map<MovieType, RentalChargeService> rentalChargeServiceCache) implements RentalChargeServiceResolver {

    /**
     * This method will return RentalChargeService implementation based on the movieType
     *
     * @param movieType type of movie
     * @return RentalChargeService implementation
     */
    @Override
    public RentalChargeService resolve(MovieType movieType) {
        return rentalChargeServiceCache.get(movieType);
    }
}
