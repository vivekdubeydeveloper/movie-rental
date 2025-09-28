package com.etraveli.movierental.dao;

import com.etraveli.movierental.model.Movie;

import java.util.HashMap;

public class MovieDAO {
    private static HashMap<String, Movie> movies = new HashMap();
    static{
        movies.put("F001", new Movie("You've Got Mail", "regular"));
        movies.put("F002", new Movie("Matrix", "regular"));
        movies.put("F003", new Movie("Cars", "childrens"));
        movies.put("F004", new Movie("Fast & Furious X", "new"));
    }

    public Movie findById(String id){
       return movies.get(id);
    }

}
