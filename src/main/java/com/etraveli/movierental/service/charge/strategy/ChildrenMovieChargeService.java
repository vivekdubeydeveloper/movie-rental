package com.etraveli.movierental.service.charge.strategy;

public class ChildrenMovieChargeService implements RentalChargeService {
    private static final double BASE_CHARGE=1.5;
    private static final int BASE_DAYS=3;
    private static final double DAILY_RATE=1.5;
    private static final int FREQUENT_ENTER_POINTS=1;

    @Override
    public double calculateCharge(int daysRented) {
        double thisAmount = BASE_CHARGE;
        if (daysRented > BASE_DAYS) {
            thisAmount = ((daysRented - BASE_DAYS) * DAILY_RATE) + thisAmount;
        }
        return thisAmount;
    }

    @Override
    public int calculateFrequentEnterPoints(int daysRented) {
        return FREQUENT_ENTER_POINTS;
    }
}
