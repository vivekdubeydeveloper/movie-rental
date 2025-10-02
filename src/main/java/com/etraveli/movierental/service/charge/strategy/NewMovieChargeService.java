package com.etraveli.movierental.service.charge.strategy;

public class NewMovieChargeService implements RentalChargeService {
    private static final double DAILY_RATE=3.0;
    private static final int BASE_DAYS=2;
    private static final int FREQUENT_ENTER_BASE_POINTS=1;
    private static final int FREQUENT_ENTER_BONUS_POINTS=2;
    @Override
    public double calculateCharge(int daysRented) {
        return daysRented * DAILY_RATE;
    }

    @Override
    public int calculateFrequentEnterPoints(int daysRented) {
        if(daysRented > BASE_DAYS){
            return FREQUENT_ENTER_BONUS_POINTS;
        }
        return FREQUENT_ENTER_BASE_POINTS;
    }
}
