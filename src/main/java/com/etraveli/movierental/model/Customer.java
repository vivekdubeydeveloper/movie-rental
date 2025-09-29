package com.etraveli.movierental.model;

import java.util.List;

public record Customer(String name, List<MovieRental> rentals) {
}
