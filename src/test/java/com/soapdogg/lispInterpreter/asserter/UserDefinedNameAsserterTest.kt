package com.soapdogg.lispInterpreter.asserter

import com.soapdogg.lispInterpreter.determiner.InvalidNameDeterminer
import org.mockito.Mockito

import org.junit.jupiter.api.Test
import com.soapdogg.lispInterpreter.exceptions.InvalidUserDefinedNameException
import org.junit.jupiter.api.Assertions

class UserDefinedNameAsserterTest {
    private val functionName = "functionName"
    private val invalidNameDeterminer = Mockito.mock(InvalidNameDeterminer::class.java)
    private val userDefinedFunctionNameAsserter = UserDefinedFunctionNameAsserter(
        invalidNameDeterminer
    )

    @Test
    fun invalidNameTest() {
        Mockito.`when`(invalidNameDeterminer.isInvalidName(functionName)).thenReturn(true)
        Assertions.assertThrows(
            InvalidUserDefinedNameException::class.java
        ) { userDefinedFunctionNameAsserter.assertFunctionNameIsValid(functionName) }
    }

    @Test
    fun validNameTest() {
        Mockito.`when`(invalidNameDeterminer.isInvalidName(functionName)).thenReturn(false)
        Assertions.assertDoesNotThrow { userDefinedFunctionNameAsserter.assertFunctionNameIsValid(functionName) }
    }
}