package com.etraveli.movierental.dao;

import com.etraveli.movierental.model.Movie;
import com.etraveli.movierental.model.MovieType;

import java.util.HashMap;
import java.util.List;
import java.util.function.Predicate;

public class MovieDAO {
    private static HashMap<String, Movie> movies = new HashMap();
    static{
        movies.put("F001", new Movie("You've Got Mail", MovieType.REGULAR));
        movies.put("F002", new Movie("Matrix",  MovieType.REGULAR));
        movies.put("F003", new Movie("Cars", MovieType.CHILDRENS));
        movies.put("F004", new Movie("Fast & Furious X", MovieType.NEW));
    }

    public Movie findById(String id){
       return movies.get(id);
    }

    public List<String> findAllMissingId(List<String> ids){
        return ids.stream().filter(Predicate.not(movies::containsKey)).toList();
    }

}
