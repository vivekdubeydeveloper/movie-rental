package com.etraveli.movierental.service.format;

import com.etraveli.movierental.model.RentalStatement;

import java.util.List;

public class TextFormatService implements FormatService {
    @Override
    public String formatStatement(String customerName, List<RentalStatement> rentalStatements) {
        String result = "Rental Record for " + customerName + "\n";
        double totalAmount=0;
        int frequentEnterPoints=0;

        for (RentalStatement rentalStatement : rentalStatements) {
            result += "\t" + rentalStatement.title()+ "\t" + rentalStatement.charge() + "\n";
            totalAmount+=rentalStatement.charge();
            frequentEnterPoints+=rentalStatement.frequentEnterPoints();
        }
        result += "Amount owed is " + totalAmount + "\n";
        result += "You earned " + frequentEnterPoints + " frequent points\n";
        return result;
    }
}
