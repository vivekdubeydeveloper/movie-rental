package com.etraveli.movierental.service.user.input;

import com.etraveli.movierental.model.InputType;
import com.etraveli.movierental.model.MovieType;
import com.etraveli.movierental.service.charge.strategy.RentalChargeService;
import com.etraveli.movierental.service.charge.strategy.RentalChargeServiceResolver;

import java.util.Map;

/**
 * This record keeps cache with Input type as key and Input Service implementations as value.
 * We can get appropriate InputService implementation based on the input tpe from the cache
 * @author vivek
 * @param inputServiceCache
 */
public record InputServiceResolverImpl(
        Map<InputType, InputService> inputServiceCache) implements InputServiceResolver {

    /**
     * Return InputService implementation from cache
     * @param inputType
     * @return InputService implementation
     */
    @Override
    public InputService resolve(InputType inputType) {
        return inputServiceCache.get(inputType);
    }
}
