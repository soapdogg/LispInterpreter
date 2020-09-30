package com.soapdogg.lispInterpreter.determiner;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Set;

class InvalidNameDeterminerTest {

    private String value;

    private String invalidName;
    private Set<String> invalidNames;
    private NumericStringDeterminer numericStringDeterminer;

    private InvalidNameDeterminer invalidNameDeterminer;

    @BeforeEach
    void setup() {
        invalidName = "invalidName";
        invalidNames = Set.of(invalidName);

        numericStringDeterminer = Mockito.mock(NumericStringDeterminer.class);

        invalidNameDeterminer = InvalidNameDeterminer.newInstance(
            invalidNames,
            numericStringDeterminer
        );
    }

    @Test
    void invalidNamesContainsValueTest() {
        value = invalidName;

        boolean actual = invalidNameDeterminer.isInvalidName(value);

        Assertions.assertTrue(actual);
        Mockito.verifyNoInteractions(numericStringDeterminer);
    }

    @Test
    void valueIsNumericTest() {
        value = "0";
        Mockito.when(numericStringDeterminer.isStringNumeric(value)).thenReturn(true);

        boolean actual = invalidNameDeterminer.isInvalidName(value);

        Assertions.assertTrue(actual);
    }

    @Test
    void valueIsValidTest() {
        value = "valid";
        Mockito.when(numericStringDeterminer.isStringNumeric(value)).thenReturn(false);

        boolean actual = invalidNameDeterminer.isInvalidName(value);

        Assertions.assertFalse(actual);
    }
}
