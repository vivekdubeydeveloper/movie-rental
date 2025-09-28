package com.etraveli.movierental.service.format;

import com.etraveli.movierental.model.RentalStatement;

import java.text.DecimalFormat;
import java.util.List;

public class TextFormatService implements FormatService {
    private static final DecimalFormat CURRENCY_FORMAT = new DecimalFormat("0.00");

    @Override
    public String formatStatement(String customerName, List<RentalStatement> rentalStatements) {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("Rental Record for %s\n", customerName));

        double totalAmount = 0;
        int frequentEnterPoints = 0;

        for (RentalStatement rentalStatement : rentalStatements) {
            sb.append(String.format("\t%s\t%s\n",
                    rentalStatement.title(),
                    CURRENCY_FORMAT.format(rentalStatement.charge())));

            totalAmount += rentalStatement.charge();
            frequentEnterPoints += rentalStatement.frequentEnterPoints();
        }
        sb.append(String.format("Amount owed is %s\n", CURRENCY_FORMAT.format(totalAmount)));
        sb.append(String.format("You earned %d frequent points\n", frequentEnterPoints));
        return sb.toString();
    }
}
