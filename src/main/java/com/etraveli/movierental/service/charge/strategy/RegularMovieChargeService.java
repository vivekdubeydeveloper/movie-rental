package com.etraveli.movierental.service.charge.strategy;

/**
 * This class calculate charge and frequent enter points for regular movie based on the days rented
 *
 * @author vivek
 */
public class RegularMovieChargeService implements RentalChargeService {
    private static final double BASE_CHARGE = 2.0;
    private static final int BASE_DAYS = 2;
    private static final double DAILY_RATE = 1.5;
    private static final int FREQUENT_ENTER_POINTS = 1;

    /**
     * Calculate charges for regular movie based on days rented
     *
     * @param daysRented movie is rented for days
     * @return charges
     */
    @Override
    public double calculateCharge(int daysRented) {
        double thisAmount = BASE_CHARGE;
        if (daysRented > BASE_DAYS) {
            thisAmount = ((daysRented - BASE_DAYS) * DAILY_RATE) + thisAmount;
        }
        return thisAmount;
    }

    /**
     * Calculate frequent enter point for regular movie based on days rented
     *
     * @param daysRented movie is rented for day
     * @return frequent enter points
     */
    @Override
    public int calculateFrequentEnterPoints(int daysRented) {
        return FREQUENT_ENTER_POINTS;
    }
}
