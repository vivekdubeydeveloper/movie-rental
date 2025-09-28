package com.etraveli.movierental.service;

import com.etraveli.movierental.model.Customer;

public interface RentalStatementService {
    public String statement(Customer customer);
}
