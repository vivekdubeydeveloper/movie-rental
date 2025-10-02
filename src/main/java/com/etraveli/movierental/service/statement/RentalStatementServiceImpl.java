package com.etraveli.movierental.service.statement;

import com.etraveli.movierental.dao.DAO;
import com.etraveli.movierental.exception.MovieNotFoundException;
import com.etraveli.movierental.model.Customer;
import com.etraveli.movierental.model.Movie;
import com.etraveli.movierental.model.MovieRental;
import com.etraveli.movierental.model.RentalStatement;
import com.etraveli.movierental.service.charge.strategy.RentalChargeService;
import com.etraveli.movierental.service.charge.strategy.RentalChargeServiceResolver;
import com.etraveli.movierental.service.format.StatementFormatterService;
import com.etraveli.movierental.validator.ValidationErrors;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RentalStatementServiceImpl implements RentalStatementService {

    private final DAO movieDAO;
    private final StatementFormatterService statementFormatterService;
    private final RentalChargeServiceResolver  rentalChargeServiceResolver;

    public RentalStatementServiceImpl(DAO movieDAO, StatementFormatterService statementFormatterService,RentalChargeServiceResolver  rentalChargeServiceResolver) {
        this.movieDAO=movieDAO;
        this.statementFormatterService = statementFormatterService;
        this.rentalChargeServiceResolver = rentalChargeServiceResolver;
    }

    public String statement(Customer customer) {
    RentalChargeService rentalChargeService;
    List<RentalStatement> rentalStatements = new ArrayList<>();

    for (MovieRental movieRental : customer.rentals()) {
        Movie movie = movieDAO.findById(movieRental.movieId());
        if(Objects.isNull(movie)){
            throw new MovieNotFoundException(String.format(ValidationErrors.MOVIE_NOT_FOUND.getMessage(),movieRental.movieId()));
        }
        rentalChargeService= rentalChargeServiceResolver.resolve(movie.movieType());
        rentalStatements.add(new RentalStatement(movie.title(),rentalChargeService.calculateCharge(movieRental.days()),rentalChargeService.calculateFrequentEnterPoints(movieRental.days())));
    }
        return statementFormatterService.formatStatement(customer.name(), rentalStatements);
  }
}
