package com.soapdogg.lispInterpreter.determiner

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class LiteralTokenValueEndIndexDeterminerTest {
    private val literalTokenValueEndIndexDeterminer = LiteralTokenValueEndIndexDeterminer()

    @Test
    fun wholeWordIsLiteralTest() {
        val literal = "ERIC1323"
        val result = literalTokenValueEndIndexDeterminer.determineEndIndexOfLiteralTokenValue(
            literal,
            0
        )
        Assertions.assertEquals(result, literal.length)
    }

    @Test
    fun nonLiteralValueHappensBeforeStartingPosTest() {
        val literal = ")ERIC1323"
        val result = literalTokenValueEndIndexDeterminer.determineEndIndexOfLiteralTokenValue(
            literal,
            1
        )
        Assertions.assertEquals(result, literal.length)
    }

    @Test
    fun wordContainsNonLiteralValueAfterStartingPos() {
        val literal = "E)RIC1323"
        val result = literalTokenValueEndIndexDeterminer.determineEndIndexOfLiteralTokenValue(
            literal,
            0
        )
        Assertions.assertEquals(result, 1)
    }
}