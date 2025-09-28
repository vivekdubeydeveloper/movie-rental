package com.etraveli.movierental.model;

public class Movie {
    private String title;
    private MovieType movieType;

    public Movie(String title,  MovieType movieType) {

        this.title = title;
        this.movieType = movieType;
    }

    public String getTitle() {
        return title;
    }

    public MovieType getMovieType() {
        return movieType;
    }
}
