package com.etraveli.movierental.dao;

import com.etraveli.movierental.model.Movie;
import com.etraveli.movierental.model.MovieType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MovieDAOTest {
private DAO movieDAO;
    @BeforeEach
    void setUp() {
        movieDAO = new MovieDAO();
    }

    @Test
    void findByIdWhenGivenValidIdThenGetExpectedResponse() {
        Movie movie = movieDAO.findById("F001");
        assertEquals("You've Got Mail",movie.title());
    }

    @Test
    void findByIdWhenGivenInvalidIdThenGetNullResponse() {
        assertNull(movieDAO.findById("F0002"));
    }
}