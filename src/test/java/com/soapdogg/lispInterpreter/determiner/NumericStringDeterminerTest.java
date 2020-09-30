package com.soapdogg.lispInterpreter.determiner;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class NumericStringDeterminerTest {

    private NumericStringDeterminer numericStringDeterminer;

    @BeforeEach
    void setup() {
        numericStringDeterminer = NumericStringDeterminer.newInstance();
    }

    @Test
    void isNumberTest() {
        boolean actual = numericStringDeterminer.isStringNumeric("342");

        Assertions.assertTrue(actual);
    }

    @Test
    void isNotNumberTest() {
        boolean actual = numericStringDeterminer.isStringNumeric("hello!");

        Assertions.assertFalse(actual);
    }
}
