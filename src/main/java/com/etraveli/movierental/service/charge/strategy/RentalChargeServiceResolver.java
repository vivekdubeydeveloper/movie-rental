package com.etraveli.movierental.service.charge.strategy;

import com.etraveli.movierental.model.MovieType;

/**
 * This is contract to find the Service which is responsible for calculating rental charges and
 * frequent enter point based on the movieType
 *
 * @author vivek
 */
public interface RentalChargeServiceResolver {
    RentalChargeService resolve(MovieType movieType);
}
