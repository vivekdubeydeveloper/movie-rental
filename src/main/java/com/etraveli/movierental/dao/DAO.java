package com.etraveli.movierental.dao;

import com.etraveli.movierental.model.Movie;

import java.util.List;

public interface DAO {
    Movie findById(String id);
    List<String> findAllMissingId(List<String> ids);
}
