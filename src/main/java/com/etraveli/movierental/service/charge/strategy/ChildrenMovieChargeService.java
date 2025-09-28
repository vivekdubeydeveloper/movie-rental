package com.etraveli.movierental.service.charge.strategy;

public class ChildrenMovieChargeService implements RentalChargeService {
    @Override
    public double calculateCharge(int daysRented) {
        double thisAmount = 1.5;
        if (daysRented > 3) {
            thisAmount = ((daysRented - 3) * 1.5) + thisAmount;
        }
        return daysRented;
    }

    @Override
    public int calculateFrequentEnterPoints(int daysRented) {
        return 1;
    }
}
