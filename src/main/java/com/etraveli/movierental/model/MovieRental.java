package com.etraveli.movierental.model;

/**
 * Model for storing Movie Rental Details
 * @author vivek
 * @param movieId unique id for movie
 * @param days no of rental days
 */
public record MovieRental(String movieId, int days) {
}
