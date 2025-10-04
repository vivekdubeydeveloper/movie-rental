package com.etraveli.movierental.service.user.input;

import com.etraveli.movierental.model.InputType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.EnumMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class InputServiceResolverImplTest {
private InputServiceResolver inputServiceResolver;
    @BeforeEach
    void setUp() {
        Map<InputType, InputService> inputServiceCache=new EnumMap<>(InputType.class);
        inputServiceCache.put(InputType.FIXED,new FixedInputServiceImpl());
        inputServiceCache.put(InputType.USER_INPUT,new UserInputServiceImpl());
        inputServiceResolver = new InputServiceResolverImpl(inputServiceCache);
    }

    @Test
    void resolveWhenGivingCorrectInputTypeThenGetExpectedOutput() {
        assertInstanceOf(FixedInputServiceImpl.class,inputServiceResolver.resolve(InputType.FIXED));
        assertInstanceOf(UserInputServiceImpl.class,inputServiceResolver.resolve(InputType.USER_INPUT));
    }

}