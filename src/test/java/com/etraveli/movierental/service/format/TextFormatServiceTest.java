package com.etraveli.movierental.service.format;

import com.etraveli.movierental.model.RentalStatement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TextFormatServiceTest {
private FormatService formatService;
    @BeforeEach
    void setUp() {
        formatService=new TextFormatService();
    }

    @Test
    void formatStatementWhenValidInputThenReturnExpectedRentalStatement() {
        String expected = "Rental Record for C. U. Stomer\n\tYou've Got Mail\t3.50\n\tMatrix\t2.00\nAmount owed is 5.50\nYou earned 2 frequent points\n";
        assertEquals(expected,formatService.formatStatement("C. U. Stomer", List.of(new RentalStatement("You've Got Mail",3.50,1),new RentalStatement("Matrix",2.00,1))));
    }
}