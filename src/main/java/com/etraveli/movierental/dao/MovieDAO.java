package com.etraveli.movierental.dao;

import com.etraveli.movierental.model.Movie;
import com.etraveli.movierental.model.MovieType;

import java.util.HashMap;

public class MovieDAO implements DAO {
    private static final HashMap<String, Movie> movies = new HashMap<>();

    static {
        movies.put("F001", new Movie("You've Got Mail", MovieType.REGULAR));
        movies.put("F002", new Movie("Matrix", MovieType.REGULAR));
        movies.put("F003", new Movie("Cars", MovieType.CHILDRENS));
        movies.put("F004", new Movie("Fast & Furious X", MovieType.NEW));
    }

    @Override
    public Movie findById(String id) {
        return movies.get(id);
    }
}
