package com.etraveli.movierental.service.statement;

import com.etraveli.movierental.model.Customer;

public interface RentalStatementService {
    String statement(Customer customer);
}
