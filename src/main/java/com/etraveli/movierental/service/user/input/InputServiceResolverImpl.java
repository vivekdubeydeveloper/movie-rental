package com.etraveli.movierental.service.user.input;

import com.etraveli.movierental.model.InputType;
import com.etraveli.movierental.model.MovieType;
import com.etraveli.movierental.service.charge.strategy.RentalChargeService;
import com.etraveli.movierental.service.charge.strategy.RentalChargeServiceResolver;

import java.util.Map;

public record InputServiceResolverImpl(
        Map<InputType, InputService> inputServiceCache) implements InputServiceResolver {

    @Override
    public InputService resolve(InputType inputType) {
        return inputServiceCache.get(inputType);
    }
}
