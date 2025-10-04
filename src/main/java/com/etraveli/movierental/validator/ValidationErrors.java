package com.etraveli.movierental.validator;

/**
 * Enum keeping error strings will be used as message while throwing exceptions
 * @author vivek
 */
public enum ValidationErrors {
    BLANK_NAME_INPUT("Name cannot be blank or empty"),
    CUSTOMER_NULL("Customer object can't be null"),
    CUSTOMER_NAME_NULL_OR_BLANK("Customer name can't be null or blank"),
    MOVIE_RENTAL_NULL_OR_EMPTY("Movie Rentals can't be null or empty"),
    MOVIE_ID_NULL_OR_EMPTY("Movie Id can't be null or empty"),
    RENTAL_DAYS_SHOULD_BE_POSITIVE("Rental days should be positive"),
    MOVIE_NOT_FOUND("Movie %s not found");

    private final String message;
    ValidationErrors(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
