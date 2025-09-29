package com.etraveli.movierental.service.format;

import com.etraveli.movierental.model.RentalStatement;

import java.util.List;

public interface FormatService {
    String formatStatement(String customerName, List<RentalStatement> rentalStatements);
}
