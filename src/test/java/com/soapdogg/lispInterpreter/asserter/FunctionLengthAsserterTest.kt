package com.soapdogg.lispInterpreter.asserter

import com.soapdogg.lispInterpreter.datamodels.Node
import com.soapdogg.lispInterpreter.determiner.FunctionLengthDeterminer
import com.soapdogg.lispInterpreter.exceptions.WrongFunctionLengthException
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class FunctionLengthAsserterTest {
    private val functionName = "functionName"
    private val expected = 2
    private val params = Mockito.mock(Node::class.java)
    private val actual = 1
    private val functionLengthDeterminer = Mockito.mock(FunctionLengthDeterminer::class.java)
    private val functionLengthAsserter = FunctionLengthAsserter(
        functionLengthDeterminer
    )

    @Test
    fun equalsTest() {
        Mockito.`when`(functionLengthDeterminer.determineFunctionLength(params)).thenReturn(actual)
        Assertions.assertDoesNotThrow {
            functionLengthAsserter.assertLengthIsAsExpected(
                functionName,
                expected,
                params
            )
        }
    }

    @Test
    fun doesNotEqualTest() {
        Mockito.`when`(functionLengthDeterminer.determineFunctionLength(params)).thenReturn(actual)
        Assertions.assertThrows(
            WrongFunctionLengthException::class.java
        ) {
            functionLengthAsserter.assertLengthIsAsExpected(
                functionName,
                expected + 1,
                params
            )
        }
    }
}