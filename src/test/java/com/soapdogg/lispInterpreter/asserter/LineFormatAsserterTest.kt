package com.soapdogg.lispInterpreter.asserter

import com.soapdogg.lispInterpreter.constants.TokenValueConstants
import com.soapdogg.lispInterpreter.exceptions.InvalidTokenException
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class LineFormatAsserterTest {
    private val lineFormatAsserter = LineFormatAsserter(
        TokenValueConstants.ERROR_STATE_PATTERN
    )

    @Test
    fun lineAsserterDoesNotThrowTest() {
        Assertions.assertDoesNotThrow { lineFormatAsserter.assertLineFormat("") }
        Assertions.assertDoesNotThrow { lineFormatAsserter.assertLineFormat("2132132") }
        Assertions.assertDoesNotThrow { lineFormatAsserter.assertLineFormat("\tASW 34324 AS() ") }
    }

    @Test
    fun lineAsserterDoesThrowTest() {
        Assertions.assertThrows(
            InvalidTokenException::class.java
        ) { lineFormatAsserter.assertLineFormat("23432DFD") }
        Assertions.assertThrows(
            InvalidTokenException::class.java
        ) { lineFormatAsserter.assertLineFormat("2132132 343SDF") }
        Assertions.assertThrows(
            InvalidTokenException::class.java
        ) { lineFormatAsserter.assertLineFormat("\tASW 34324P AS() ") }
    }
}