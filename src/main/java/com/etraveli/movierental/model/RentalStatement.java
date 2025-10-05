package com.etraveli.movierental.model;

/**
 * Model for storing Rental Statement
 *
 * @param title               title of movie
 * @param charge              calculated charge for movie
 * @param frequentEnterPoints calculated frequent enter points for movie
 * @author vivek
 */
public record RentalStatement(String title, double charge, int frequentEnterPoints) {
}
