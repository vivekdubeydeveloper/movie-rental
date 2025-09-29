package com.etraveli.movierental.service.statement;

import com.etraveli.movierental.dao.DAO;
import com.etraveli.movierental.dao.MovieDAO;
import com.etraveli.movierental.exception.MovieNotFoundException;
import com.etraveli.movierental.model.*;
import com.etraveli.movierental.service.charge.strategy.RentalChargeService;
import com.etraveli.movierental.service.charge.strategy.RentalChargeServiceFactory;
import com.etraveli.movierental.service.format.FormatService;
import com.etraveli.movierental.service.format.TextFormatService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RentalStatementServiceImpl implements RentalStatementService {

    private final DAO movieDAO;
    private final FormatService formatService;

    public RentalStatementServiceImpl() {
        movieDAO=new MovieDAO();
        formatService = new TextFormatService();
    }

    public String statement(Customer customer) {
    RentalChargeService rentalChargeService;
    List<RentalStatement> rentalStatements = new ArrayList<>();

    for (MovieRental r : customer.rentals()) {
        Movie movie = movieDAO.findById(r.movieId());
        if(Objects.isNull(movie)){
            throw new MovieNotFoundException(String.format("Movie %s not found",r.movieId()));
        }
        rentalChargeService= RentalChargeServiceFactory.getRentalChargeService(movie.movieType());
        rentalStatements.add(new RentalStatement(movie.title(),rentalChargeService.calculateCharge(r.days()),rentalChargeService.calculateFrequentEnterPoints(r.days())));
    }
        return formatService.formatStatement(customer.name(), rentalStatements);
  }
}
