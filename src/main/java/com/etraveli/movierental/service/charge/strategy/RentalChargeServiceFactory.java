package com.etraveli.movierental.service.charge.strategy;

import com.etraveli.movierental.model.MovieType;

import java.util.EnumMap;
import java.util.Map;

public class RentalChargeServiceFactory {
    private final static Map<MovieType, RentalChargeService> rentalChargeServiceCache = new EnumMap<>(MovieType.class);

    public static RentalChargeService getRentalChargeService(MovieType movieType) {
        //TODO: validate Movie type null in validation layer
        return rentalChargeServiceCache.computeIfAbsent(movieType, movieTypeKey -> switch (movieTypeKey) {
            case NEW -> new NewMovieChargeService();
            case REGULAR -> new RegularMovieChargeService();
            case CHILDRENS -> new ChildrenMovieChargeService();
        });


    }


}
