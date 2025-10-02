package com.etraveli.movierental.service.charge.strategy;

import com.etraveli.movierental.model.MovieType;

public interface RentalChargeServiceResolver {
    RentalChargeService resolve(MovieType movieType);
}
