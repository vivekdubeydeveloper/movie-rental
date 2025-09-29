package com.etraveli.movierental.dao;

import com.etraveli.movierental.model.Movie;

public interface DAO {
    Movie findById(String id);
}
