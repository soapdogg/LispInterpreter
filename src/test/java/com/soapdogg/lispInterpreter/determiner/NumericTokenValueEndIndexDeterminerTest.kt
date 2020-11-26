package com.soapdogg.lispInterpreter.determiner

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class NumericTokenValueEndIndexDeterminerTest {
    private val numericTokenValueEndIndexDeterminer = NumericTokenValueEndIndexDeterminer()

    @Test
    fun wholeWordIsLiteralTest() {
        val literal = "1323"
        val result = numericTokenValueEndIndexDeterminer.determineEndIndexOfNumericTokenValue(
            literal,
            0
        )
        Assertions.assertEquals(result, literal.length)
    }

    @Test
    fun nonLiteralValueHappensBeforeStartingPosTest() {
        val literal = ")1323"
        val result = numericTokenValueEndIndexDeterminer.determineEndIndexOfNumericTokenValue(
            literal,
            1
        )
        Assertions.assertEquals(result, literal.length)
    }

    @Test
    fun wordContainsNonLiteralValueAfterStartingPos() {
        val literal = "1)1323"
        val result = numericTokenValueEndIndexDeterminer.determineEndIndexOfNumericTokenValue(
            literal,
            0
        )
        Assertions.assertEquals(result, 1)
    }
}