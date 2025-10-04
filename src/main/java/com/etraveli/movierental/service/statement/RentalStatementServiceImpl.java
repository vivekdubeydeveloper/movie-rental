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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * This is actual business service which will calculate rental charge and frequent enter points for each movie.
 * It is collecting the data in  List<RentalStatement> and sends to StatementFormatter Service to Format the statement
 * @author vivek
 */

public class RentalStatementServiceImpl implements RentalStatementService {
    private static final Logger log = LogManager.getLogger(RentalStatementServiceImpl.class);
    private final DAO movieDAO;
    private final StatementFormatterService statementFormatterService;
    private final RentalChargeServiceResolver  rentalChargeServiceResolver;

    public RentalStatementServiceImpl(DAO movieDAO, StatementFormatterService statementFormatterService,RentalChargeServiceResolver  rentalChargeServiceResolver) {
        this.movieDAO=movieDAO;
        this.statementFormatterService = statementFormatterService;
        this.rentalChargeServiceResolver = rentalChargeServiceResolver;
    }

    /**
     * This method get rental statement information and forward to statement Formatter for creating the formatted rental statement.
     * This method fetch movie from movieDAO if movie is not found in DB then it throws exception.
     * If movie is found in DB it finds appropriate RentalChargeService implementation on the base of movie type
     * with the help of RentalChargeServiceResolver.
     * RentalChargeServiceResolver return the appropriate object form its cache based on the movie type i.e.
     * if movieType is CHILDREN it will return ChildrenMovieChargeService object and so on
     * This method use the returned object from above step to calculate charges and frequent enter points by calling methods
     * This method store calculate charges,frequent enter points data in List<RentalStatement>
     * After collecting the data this method call Formatter Service with the data to generate formatted rental statement
     * @param customer object of customer
     * @return rental statement as formatted String
     */
    public String statement(Customer customer) {
    List<RentalStatement> rentalStatements = new ArrayList<>();

    for (MovieRental movieRental : customer.rentals()) {
        Movie movie = movieDAO.findById(movieRental.movieId());
        if(Objects.isNull(movie)){
            throw new MovieNotFoundException(String.format(ValidationErrors.MOVIE_NOT_FOUND.getMessage(),movieRental.movieId()));
        }
        RentalChargeService rentalChargeService= rentalChargeServiceResolver.resolve(movie.movieType());
        rentalStatements.add(new RentalStatement(movie.title(),rentalChargeService.calculateCharge(movieRental.days()),rentalChargeService.calculateFrequentEnterPoints(movieRental.days())));
    }
        log.info("Statement data calculation done");
        return statementFormatterService.formatStatement(customer.name(), rentalStatements);
  }
}
