package com.etraveli.movierental.model;

/**
 * Model for storing Movie Rental Details
 *
 * @param movieId unique id for movie
 * @param days    no of rental days
 * @author vivek
 */
public record MovieRental(String movieId, int days) {
}
