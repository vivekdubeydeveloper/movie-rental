package com.etraveli.movierental.service.rental.statement;

import com.etraveli.movierental.model.Customer;

/**
 * This interface keeps contract of statement generation
 *
 * @author vivek
 */
public interface RentalStatementService {
    String statement(Customer customer);
}
