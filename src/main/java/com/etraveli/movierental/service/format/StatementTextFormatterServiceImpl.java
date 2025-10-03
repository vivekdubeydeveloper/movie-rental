package com.etraveli.movierental.service.format;

import com.etraveli.movierental.model.RentalStatement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;

public class StatementTextFormatterServiceImpl implements StatementFormatterService {
    private static final Logger log = LogManager.getLogger(StatementTextFormatterServiceImpl.class);
    @Override
    public String formatStatement(String customerName, List<RentalStatement> rentalStatements) {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("Rental Record for %s\n", customerName));

        DecimalFormat currencyFormat = new DecimalFormat("0.00", DecimalFormatSymbols.getInstance(Locale.ENGLISH));

        double totalAmount = 0;
        int frequentEnterPoints = 0;

        for (RentalStatement rentalStatement : rentalStatements) {
            sb.append(String.format("\t%s\t%s\n",
                    rentalStatement.title(),
                    currencyFormat.format(rentalStatement.charge())));

            totalAmount += rentalStatement.charge();
            frequentEnterPoints += rentalStatement.frequentEnterPoints();
        }
        sb.append(String.format("Amount owed is %s\n", currencyFormat.format(totalAmount)));
        sb.append(String.format("You earned %d frequent points\n", frequentEnterPoints));
        log.info("Rental statement generated successfully for customer: {}",customerName);
        return sb.toString();
    }
}
