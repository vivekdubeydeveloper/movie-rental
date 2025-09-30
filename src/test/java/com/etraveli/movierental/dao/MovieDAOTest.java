package com.etraveli.movierental.dao;

import com.etraveli.movierental.model.Movie;
import com.etraveli.movierental.model.MovieType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MovieDAOTest {
DAO movieDAO;
    @BeforeEach
    void setUp() {
        movieDAO = new MovieDAO();
    }

    @Test
    void findById() {
        Movie movie = movieDAO.findById("F001");
        assertEquals("You've Got Mail",movie.title());
        assertEquals(MovieType.REGULAR,movie.movieType());
        assertNull(movieDAO.findById("F0002"));
    }
}