package com.etraveli.movierental.service.user.input;

import com.etraveli.movierental.model.InputType;

/**
 * This is contract for selecting correct input service implementation based on the inputType
 *
 * @author vivek
 */
public interface InputServiceResolver {
    InputService resolve(InputType inputType);
}
