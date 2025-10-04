package com.etraveli.movierental.service.charge.strategy;

/**
 * This is contract for calculating movie rental charges and frequent enter points based on days rented
 * @author vivek
 */
public interface RentalChargeService {
    double calculateCharge(int daysRented);
    int calculateFrequentEnterPoints(int daysRented);
}
