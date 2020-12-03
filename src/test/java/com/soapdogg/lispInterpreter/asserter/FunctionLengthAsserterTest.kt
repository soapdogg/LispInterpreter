package com.soapdogg.lispInterpreter.asserter

import com.soapdogg.lispInterpreter.converter.NodeConverter
import com.soapdogg.lispInterpreter.datamodels.Node
import com.soapdogg.lispInterpreter.datamodels.NodeV2
import com.soapdogg.lispInterpreter.determiner.FunctionLengthDeterminer
import com.soapdogg.lispInterpreter.exceptions.WrongFunctionLengthException
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class FunctionLengthAsserterTest {
    private val functionName = "functionName"
    private val expected = 2
    private val params = Mockito.mock(Node::class.java)
    private val converted = Mockito.mock(NodeV2::class.java)
    private val actual = 1
    private val functionLengthDeterminer = Mockito.mock(FunctionLengthDeterminer::class.java)
    private val nodeConverter = Mockito.mock(NodeConverter::class.java)
    private val functionLengthAsserter = FunctionLengthAsserter(
        functionLengthDeterminer,
        nodeConverter
    )

    @Test
    fun equalsTest() {
        Mockito.`when`(nodeConverter.convertNodeToNodeV2(params)).thenReturn(converted)
        Mockito.`when`(functionLengthDeterminer.determineFunctionLength(converted)).thenReturn(actual)
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
        Mockito.`when`(nodeConverter.convertNodeToNodeV2(params)).thenReturn(converted)
        Mockito.`when`(functionLengthDeterminer.determineFunctionLength(converted)).thenReturn(actual)
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