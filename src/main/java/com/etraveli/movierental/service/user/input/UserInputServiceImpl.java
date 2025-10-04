package com.etraveli.movierental.service.user.input;

import com.etraveli.movierental.exception.UserInputException;
import com.etraveli.movierental.model.Customer;
import com.etraveli.movierental.model.MovieRental;
import com.etraveli.movierental.validator.ValidationErrors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserInputServiceImpl implements InputService {
    private static final Logger log = LogManager.getLogger(UserInputServiceImpl.class);
    @Override
    public Customer takeInput() {

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Your Name: ");
        String customerName = sc.nextLine();

        List<MovieRental> movieRentals = new ArrayList<>();

        if(customerName.isBlank()){
            System.out.println("Name cannot be blank");
            System.out.println("exiting");
            sc.close();
            throw new UserInputException(ValidationErrors.BLANK_NAME_INPUT.getMessage());
        }

        boolean moreRentals = true;
        while(moreRentals){
            System.out.print("Enter Movie ID: ");
            String movieId = sc.nextLine().trim();

            if (movieId.isBlank()) {
                System.out.println("Movie Id cannot be empty. Skipping entry.");
                continue;
            }

            int rentalDays = 0;
            System.out.print("Enter Rental Days: ");

            if (sc.hasNextInt()) {
                rentalDays = sc.nextInt();

                // Consume the leftover newline character left by nextInt()
                ignoreNewLine(sc);

                if (rentalDays < 1) {
                    System.out.println("Rental rentalDays must be at least 1. Skipping entry.");
                    continue;
                }

                // Add to list
                movieRentals.add(new MovieRental(movieId, rentalDays));

            } else {
                // Handle non-integer input
                System.out.println("Invalid input. Rental days must be a number. Skipping entry.");
                // Consume the invalid token to prevent an infinite loop
                ignoreNewLine(sc);
                continue;
            }

            // Ask if there are more rentals
            System.out.print("Want to add another movie? (y/n): ");
            String continueInput = sc.nextLine().trim().toLowerCase();
            if (!continueInput.toUpperCase().equals("Y")) {
                moreRentals = false;
            }
        }

        Customer customer = new Customer(customerName, movieRentals);
        return customer;
    }

    private void ignoreNewLine(Scanner sc) {
        sc.nextLine();
    }

    public static void main(String[] args) {
        UserInputServiceImpl userInputService = new UserInputServiceImpl();
        userInputService.takeInput();
    }
}
