package com.etraveli.movierental.service.user.input;

import com.etraveli.movierental.model.InputType;

import java.util.Map;

/**
 * This record keeps cache with Input type as key and Input Service implementations as value.
 * We can get appropriate InputService implementation based on the input tpe from the cache
 *
 * @param inputServiceCache
 * @author vivek
 */
public record InputServiceResolverImpl(
        Map<InputType, InputService> inputServiceCache) implements InputServiceResolver {

    /**
     * Return InputService implementation from cache
     *
     * @param inputType
     * @return InputService implementation
     */
    @Override
    public InputService resolve(InputType inputType) {
        return inputServiceCache.get(inputType);
    }
}
