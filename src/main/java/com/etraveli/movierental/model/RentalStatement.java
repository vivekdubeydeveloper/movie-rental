package com.etraveli.movierental.model;

/**
 * Model for storing Rental Statement
 * @author vivek
 * @param title title of movie
 * @param charge calculated charge for movie
 * @param frequentEnterPoints calculated frequent enter points for movie
 */
public record RentalStatement(String title,double charge,int frequentEnterPoints) {
}
