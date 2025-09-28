package com.etraveli.movierental.service.charge.strategy;

public class RegularMovieChargeService implements RentalChargeService {
    @Override
    public double calculateCharge(int daysRented) {
        double thisAmount = 2;
        if (daysRented > 2) {
            thisAmount = ((daysRented - 2) * 1.5) + thisAmount;
        }
        return thisAmount;
    }

    @Override
    public int calculateFrequentEnterPoints(int daysRented) {
        return 1;
    }
}
