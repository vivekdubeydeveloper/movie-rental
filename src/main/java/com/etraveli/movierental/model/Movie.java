package com.etraveli.movierental.model;

/**
 * Model for storing Movie data
 * @author vivek
 * @param title title of movie
 * @param movieType type of movie one of possible value from MovieType enum
 */
public record Movie(String title, MovieType movieType) {
}
