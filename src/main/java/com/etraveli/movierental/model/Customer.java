package com.etraveli.movierental.model;

import java.util.List;

/**
 * Model for storing customer data
 * @author vivek
 * @param name name of customer
 * @param rentals information of rented movies
 */
public record Customer(String name, List<MovieRental> rentals) {
}
