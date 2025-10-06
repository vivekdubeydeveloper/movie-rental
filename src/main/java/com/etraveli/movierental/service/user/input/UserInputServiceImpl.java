package com.etraveli.movierental.service.user.input;

import com.etraveli.movierental.exception.UserInputException;
import com.etraveli.movierental.model.Customer;
import com.etraveli.movierental.model.MovieRental;
import com.etraveli.movierental.validator.ValidationErrors;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This class can be used for interactive input from the user instead of hard coded input.
 * This class will ask user to input data on the console
 * @author vivek
 */
public class UserInputServiceImpl implements InputService {

    /**
     * This class provide interactive environment for taking input from the user
     * @return Customer Object
     */
    @Override
    public Customer takeInput() {
        Scanner sc = new Scanner(System.in);

        //Ask for customer name
        System.out.print("Enter Your Name: ");
        String customerName = sc.nextLine();

        //if invalid customer name stop the process and throw exception
        if (customerName.isBlank()) {
            System.out.println("Name cannot be blank");
            System.out.println("exiting");
            sc.close();
            throw new UserInputException(ValidationErrors.BLANK_NAME_INPUT.getMessage());
        }

        //Ask for movie rental data
        List<MovieRental> movieRentals = new ArrayList<>();
        boolean moreRentals = true;
        while (moreRentals) {
            //Ask for movie id
            System.out.print("Enter Movie ID: ");
            String movieId = sc.nextLine().trim();

            //if invalid it will not save the record and again ask for movie id
            if (movieId.isBlank()) {
                System.out.println("Movie Id cannot be empty. Skipping entry.");
                continue;
            }

            //Ask for rental days
            int rentalDays = 0;
            System.out.print("Enter Rental Days: ");

            if (sc.hasNextInt()) {
                rentalDays = sc.nextInt();

                //ignore newline char due to press enter
                ignoreNewLine(sc);

                //if invalid it will not save the record and again ask for movie id
                if (rentalDays < 1) {
                    System.out.println("Rental rentalDays must be at least 1. Skipping entry.");
                    continue;
                }

                // Add to list
                movieRentals.add(new MovieRental(movieId, rentalDays));

            } else {
                // Handle non-integer input
                System.out.println("Invalid input. Rental days must be a number. Skipping entry.");
                //ignore newline char due to press enter
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
        sc.close();
        Customer customer = new Customer(customerName, movieRentals);
        return customer;
    }

    private void ignoreNewLine(Scanner sc) {
        sc.nextLine();
    }
}
