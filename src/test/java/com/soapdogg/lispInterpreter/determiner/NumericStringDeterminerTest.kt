package com.soapdogg.lispInterpreter.determiner

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class NumericStringDeterminerTest {
    private val numericStringDeterminer = NumericStringDeterminer()

    @Test
    fun isNumberTest() {
        val actual = numericStringDeterminer.isStringNumeric("342")
        Assertions.assertTrue(actual)
    }

    @Test
    fun isNotNumberTest() {
        val actual = numericStringDeterminer.isStringNumeric("hello!")
        Assertions.assertFalse(actual)
    }
}