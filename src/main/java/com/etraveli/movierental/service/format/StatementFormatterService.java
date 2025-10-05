package com.etraveli.movierental.service.format;

import com.etraveli.movierental.model.RentalStatement;

import java.util.List;

/**
 * This is contract for format the rental statement
 *
 * @author vivek
 */
public interface StatementFormatterService {
    String formatStatement(String customerName, List<RentalStatement> rentalStatements);
}
