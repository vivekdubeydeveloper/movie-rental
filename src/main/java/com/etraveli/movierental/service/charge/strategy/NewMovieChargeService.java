package com.etraveli.movierental.service.charge.strategy;

/**
 * This class calculate charge and frequent enter points for new movie based on the days rented
 * @author vivek
 */
public class NewMovieChargeService implements RentalChargeService {
    private static final double DAILY_RATE=3.0;
    private static final int BASE_DAYS=2;
    private static final int FREQUENT_ENTER_BASE_POINTS=1;
    private static final int FREQUENT_ENTER_BONUS_POINTS=2;

    /**
     * Calculate charges for new movie based on days rented
     * @param daysRented movie is rented for days
     * @return charges
     */
    @Override
    public double calculateCharge(int daysRented) {
        return daysRented * DAILY_RATE;
    }

    /**
     * Calculate frequent enter point for new movie based on days rented
     * @param daysRented  movie is rented for day
     * @return frequent enter points
     */
    @Override
    public int calculateFrequentEnterPoints(int daysRented) {
        if(daysRented > BASE_DAYS){
            return FREQUENT_ENTER_BONUS_POINTS;
        }
        return FREQUENT_ENTER_BASE_POINTS;
    }
}
