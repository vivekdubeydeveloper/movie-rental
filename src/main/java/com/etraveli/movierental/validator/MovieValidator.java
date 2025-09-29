package com.etraveli.movierental.validator;

import com.etraveli.movierental.dao.DAO;
import com.etraveli.movierental.exception.InvalidInputException;
import com.etraveli.movierental.model.MovieRental;

import java.util.List;
import java.util.Objects;

public class MovieValidator implements Validator<List<MovieRental>> {
    @Override
    public void validate(List<MovieRental> movieRentals) {
        movieRentalIsNullOrEmpty(movieRentals);
        movieIdIsNullOrEmpty(movieRentals);
        validRentalDays(movieRentals);
    }
    private void movieRentalIsNullOrEmpty(List<MovieRental> movieRentals) {
        if(Objects.isNull(movieRentals)|| movieRentals.isEmpty()) {
            throw new InvalidInputException("Movie Rentals can't be null or empty");
        }
    }

    private void movieIdIsNullOrEmpty(List<MovieRental> movieRentals) {
        if(movieRentals.stream().anyMatch(movieRental -> Objects.isNull(movieRental.movieId()) || movieRental.movieId().isBlank())){
            throw new InvalidInputException("Movie Id can't be null or empty");
        }
    }
    private void validRentalDays(List<MovieRental> movieRentals) {
        if(movieRentals.stream().anyMatch(movieRental -> movieRental.days()<=0)){
            throw new InvalidInputException("Rental days should be positive");
        }
    }
}
