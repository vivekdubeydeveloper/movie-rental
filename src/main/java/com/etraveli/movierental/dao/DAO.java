package com.etraveli.movierental.dao;

import com.etraveli.movierental.model.Movie;

/**
 * Contract for DB Layer
 *
 * @author vivek
 */
public interface DAO {
    Movie findById(String id);
}
