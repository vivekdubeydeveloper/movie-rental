package com.etraveli.movierental.service;

public interface RentalPriceService {
    double calculateCharge(int daysRented);
    int calculateFrequentEnterPoints(int daysRented);
}
