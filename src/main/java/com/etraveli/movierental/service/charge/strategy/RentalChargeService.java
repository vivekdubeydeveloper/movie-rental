package com.etraveli.movierental.service.charge.strategy;

public interface RentalChargeService {
    double calculateCharge(int daysRented);
    int calculateFrequentEnterPoints(int daysRented);
}
