package com.etraveli.movierental.service.statement;

import com.etraveli.movierental.dao.MovieDAO;
import com.etraveli.movierental.model.*;
import com.etraveli.movierental.service.charge.strategy.RentalChargeService;
import com.etraveli.movierental.service.charge.strategy.RentalChargeServiceFactory;
import com.etraveli.movierental.service.format.FormatService;
import com.etraveli.movierental.service.format.TextFormatService;

import java.util.ArrayList;
import java.util.List;

public class RentalStatementServiceImpl implements RentalStatementService {

    MovieDAO movieDAO;
    FormatService formatService;

    public RentalStatementServiceImpl() {
        movieDAO=new MovieDAO();
        formatService = new TextFormatService();
    }

    public String statement(Customer customer) {
    RentalChargeService rentalChargeService;
    List<RentalStatement> rentalStatements = new ArrayList<>();

    for (MovieRental r : customer.getRentals()) {
        Movie movie = movieDAO.findById(r.getMovieId());
        rentalChargeService= RentalChargeServiceFactory.getRentalChargeService(movie.getMovieType());
        rentalStatements.add(new RentalStatement(movie.getTitle(),rentalChargeService.calculateCharge(r.getDays()),rentalChargeService.calculateFrequentEnterPoints(r.getDays())));
    }
        return formatService.formatStatement(customer.getName(), rentalStatements);
  }
}
