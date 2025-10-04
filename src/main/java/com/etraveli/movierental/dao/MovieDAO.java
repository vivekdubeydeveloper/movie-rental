package com.etraveli.movierental.dao;

import com.etraveli.movierental.model.Movie;
import com.etraveli.movierental.model.MovieType;

import java.util.HashMap;

/**
 * Implementation of DAO for Movie
 * @author vivek
 */
public class MovieDAO implements DAO {
    private static final HashMap<String, Movie> movies = new HashMap<>();

    static {
        movies.put("F001", new Movie("You've Got Mail", MovieType.REGULAR));
        movies.put("F002", new Movie("Matrix", MovieType.REGULAR));
        movies.put("F003", new Movie("Cars", MovieType.CHILDREN));
        movies.put("F004", new Movie("Fast & Furious X", MovieType.NEW));
    }

    /**
     * Search movie by id
     * @param id unique id for movie
     * @return Movie
     */
    @Override
    public Movie findById(String id) {
        return movies.get(id);
    }
}
