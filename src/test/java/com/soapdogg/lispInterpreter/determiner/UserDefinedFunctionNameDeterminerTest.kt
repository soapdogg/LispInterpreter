package com.soapdogg.lispInterpreter.determiner

import com.soapdogg.lispInterpreter.datamodels.UserDefinedFunction
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class UserDefinedFunctionNameDeterminerTest {

    private var userDefinedFunctions: List<UserDefinedFunction> = listOf()
    private val functionName = "functionName"
    private val userDefinedFunctionNameDeterminer = UserDefinedFunctionNameDeterminer()

    @Test
    fun isUserDefinedFunctionTrueTest() {
        val userDefinedFunction = Mockito.mock(UserDefinedFunction::class.java)
        Mockito.`when`(userDefinedFunction.functionName).thenReturn(functionName)
        userDefinedFunctions = listOf(userDefinedFunction)
        val actual = userDefinedFunctionNameDeterminer.determineIfUserDefinedFunctionName(
            userDefinedFunctions,
            functionName
        )
        Assertions.assertTrue(actual)
    }
}