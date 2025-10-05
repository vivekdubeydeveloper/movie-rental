package com.etraveli.movierental.model;

import java.util.List;

/**
 * Model for storing customer data
 *
 * @param name    name of customer
 * @param rentals information of rented movies
 * @author vivek
 */
public record Customer(String name, List<MovieRental> rentals) {
}
