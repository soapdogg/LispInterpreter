package com.soapdogg.lispInterpreter.asserter

import com.soapdogg.lispInterpreter.exceptions.InvalidUserDefinedNameException
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class UserDefinedFormalParametersAsserterTest {

    private val invalidName = "invalidName"
    private val invalidNamesSet = setOf(invalidName)
    private val userDefinedFormalParametersAsserter = UserDefinedFormalParametersAsserter(
        invalidNamesSet
    )

    @Test
    fun duplicateFormalParameterNameTest() {
        val duplicate = "duplicate"
        val formalParameters = listOf(duplicate, duplicate)
        Assertions.assertThrows(
            InvalidUserDefinedNameException::class.java
        ) {
            userDefinedFormalParametersAsserter.assertFormalParameters(
                formalParameters
            )
        }
    }

    @Test
    fun invalidNameTest() {
        val formalParameters = listOf(invalidName)
        Assertions.assertThrows(
            InvalidUserDefinedNameException::class.java
        ) {
            userDefinedFormalParametersAsserter.assertFormalParameters(
                formalParameters
            )
        }
    }

    @Test
    fun validNameTest() {
        val valid = "valid"
        val formalParameters = listOf(valid)
        Assertions.assertDoesNotThrow {
            userDefinedFormalParametersAsserter.assertFormalParameters(
                formalParameters
            )
        }
    }
}