package com.etraveli.movierental.service.user.input;

import com.etraveli.movierental.model.InputType;
import com.etraveli.movierental.service.charge.strategy.RentalChargeService;

public interface InputServiceResolver {
    InputService resolve(InputType inputType);
}
