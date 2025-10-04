package com.etraveli.movierental.service.user.input;

import com.etraveli.movierental.model.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FixedInputServiceImplTest {
private InputService inputService;
    @BeforeEach
    void setUp() {
        inputService=new FixedInputServiceImpl();
    }

    @Test
    void takeInput() {
        Customer customer = inputService.takeInput();
        assertNotNull(customer);
        assertEquals("C. U. Stomer",customer.name());
        assertEquals(2,customer.rentals().size());
        assertEquals("F001",customer.rentals().get(0).movieId());
        assertEquals(3,customer.rentals().get(0).days());
        assertEquals("F002",customer.rentals().get(1).movieId());
        assertEquals(1,customer.rentals().get(1).days());
    }
}