package com.etraveli.movierental.service;

public class RegularPriceService implements RentalPriceService {
    @Override
    public double calculateCharge(int daysRented) {
        return 0;
    }

    @Override
    public int calculateFrequentEnterPoints(int daysRented) {
        return 0;
    }
}
