package com.etraveli.movierental.service.user.input;

import com.etraveli.movierental.exception.UserInputException;
import com.etraveli.movierental.model.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

class UserInputServiceImplTest {
private InputService inputService;
    @BeforeEach
    void setUp() {
        inputService=new UserInputServiceImpl();
    }

    @Test
    void takeInputWhenCustomerNameIsBlankThenThrowUserInputException() {
        String input = " ";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        assertThrows(UserInputException.class, () -> inputService.takeInput());
    }

    @Test
    void takeInputWhenInputIsInvalidValidCombinationThenGetExpectedOutput() {
        //This testcase is combination of first invalid input then valid input
        //First movie id is blank,then movie id value is there but rented days are 0
        //then correct movie id and rented days 3 with n for no more rental record to
        //add
        String input = "Vivek\n \nF0001\n0\nF001\n3\nn";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Customer customer = inputService.takeInput();
        assertEquals("Vivek",customer.name());
        assertEquals("F001",customer.rentals().get(0).movieId());
        assertEquals(3,customer.rentals().get(0).days());
    }


    @Test
    void takeInputWhenRentalDaysIsStringThenGetExpectedOutput() {
        //This testcase is combination of first invalid input then valid input
        //Here first rental days is abc then 3
        String input = "Vivek\nF0001\nabc\nF001\n3\nn";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Customer customer = inputService.takeInput();
        assertEquals("Vivek",customer.name());
        assertEquals("F001",customer.rentals().get(0).movieId());
        assertEquals(3,customer.rentals().get(0).days());
    }

    @Test
    void takeInputWhenAddTwoMovieRentalsThenGetExpectedOutput() {
        String input = "Vivek\nF001\n3\ny\nF002\n5\nn";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Customer customer = inputService.takeInput();
        assertEquals("Vivek",customer.name());
        assertEquals("F001",customer.rentals().get(0).movieId());
        assertEquals(3,customer.rentals().get(0).days());
        assertEquals("F002",customer.rentals().get(1).movieId());
        assertEquals(5,customer.rentals().get(1).days());
    }

    @Test
    void takeInputWhenInputIsValidThenGetExpectedOutput() {
        String input = "Vivek\nF001\n3\nn";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Customer customer = inputService.takeInput();
        assertEquals("Vivek",customer.name());
        assertEquals("F001",customer.rentals().get(0).movieId());
        assertEquals(3,customer.rentals().get(0).days());
    }
}