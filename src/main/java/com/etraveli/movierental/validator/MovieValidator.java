package com.etraveli.movierental.validator;

import com.etraveli.movierental.dao.MovieDAO;
import com.etraveli.movierental.exception.InvalidInputException;
import com.etraveli.movierental.model.MovieRental;

import java.util.List;

public class MovieValidator implements Validator<List<MovieRental>> {
    MovieDAO movieDAO;
    public MovieValidator(MovieDAO movieDAO) {
        this.movieDAO = movieDAO;
    }
    @Override
    public void validate(List<MovieRental> movieRentals) {
        movieFoundInData(movieRentals);
    }
    private void movieFoundInData(List<MovieRental> movieRentals) throws InvalidInputException {
        //ToDo: replace data layer logic with optional instead of null
        List<String> missingId = movieDAO.findAllMissingId(movieRentals.stream().map(MovieRental::getMovieId).toList());
        if (!missingId.isEmpty()) {
            throw new InvalidInputException(missingId.toString());
        }
    }
}
