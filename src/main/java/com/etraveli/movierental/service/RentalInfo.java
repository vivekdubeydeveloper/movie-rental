package com.etraveli.movierental.service;

import com.etraveli.movierental.dao.MovieDAO;
import com.etraveli.movierental.model.Customer;
import com.etraveli.movierental.model.Movie;
import com.etraveli.movierental.model.MovieRental;

import java.util.HashMap;

public class RentalInfo {

    MovieDAO movieDAO;

    public RentalInfo() {
        movieDAO=new MovieDAO();
    }

    public String statement(Customer customer) {
   /* HashMap<String, Movie> movies = new HashMap();
    movies.put("F001", new Movie("You've Got Mail", "regular"));
    movies.put("F002", new Movie("Matrix", "regular"));
    movies.put("F003", new Movie("Cars", "childrens"));
    movies.put("F004", new Movie("Fast & Furious X", "new"));*/

    double totalAmount = 0;
    int frequentEnterPoints = 0;
    String result = "Rental Record for " + customer.getName() + "\n";
    for (MovieRental r : customer.getRentals()) {
      double thisAmount = 0;

      // determine amount for each movie
      if (movieDAO.findById(r.getMovieId()).getCode().equals("regular")) {
        thisAmount = 2;
        if (r.getDays() > 2) {
          thisAmount = ((r.getDays() - 2) * 1.5) + thisAmount;
        }
      }
      if (movieDAO.findById(r.getMovieId()).getCode().equals("new")) {
        thisAmount = r.getDays() * 3;
      }
      if (movieDAO.findById(r.getMovieId()).getCode().equals("childrens")) {
        thisAmount = 1.5;
        if (r.getDays() > 3) {
          thisAmount = ((r.getDays() - 3) * 1.5) + thisAmount;
        }
      }

      //add frequent bonus points
      frequentEnterPoints++;
      // add bonus for a two day new release rental
      if (movieDAO.findById(r.getMovieId()).getCode() == "new" && r.getDays() > 2) frequentEnterPoints++;

      //print figures for this rental
      result += "\t" + movieDAO.findById(r.getMovieId()).getTitle() + "\t" + thisAmount + "\n";
      totalAmount = totalAmount + thisAmount;
    }
    // add footer lines
    result += "Amount owed is " + totalAmount + "\n";
    result += "You earned " + frequentEnterPoints + " frequent points\n";

    return result;
  }
}
