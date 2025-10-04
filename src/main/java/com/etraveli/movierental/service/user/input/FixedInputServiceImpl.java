package com.etraveli.movierental.service.user.input;

import com.etraveli.movierental.model.Customer;
import com.etraveli.movierental.model.MovieRental;

import java.util.Arrays;

public class FixedInputServiceImpl implements InputService{
    @Override
    public Customer takeInput() {
        return new Customer("C. U. Stomer", Arrays.asList(new MovieRental("F001", 3), new MovieRental("F002", 1)));
    }
}
