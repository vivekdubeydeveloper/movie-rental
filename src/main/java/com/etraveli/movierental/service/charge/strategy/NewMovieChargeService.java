package com.etraveli.movierental.service.charge.strategy;

public class NewMovieChargeService implements RentalChargeService {
    @Override
    public double calculateCharge(int daysRented) {
        double thisAmount = daysRented * 3;
        return thisAmount;
    }

    @Override
    public int calculateFrequentEnterPoints(int daysRented) {
        if(daysRented > 2){
            return 2;
        };
        return 1;
    }
}
