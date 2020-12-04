package com.soapdogg.lispInterpreter.function

import com.soapdogg.lispInterpreter.asserter.FunctionLengthAsserter
import com.soapdogg.lispInterpreter.constants.FunctionLengthConstants
import com.soapdogg.lispInterpreter.constants.FunctionNameConstants
import com.soapdogg.lispInterpreter.constants.ReservedValuesConstants
import com.soapdogg.lispInterpreter.datamodels.*
import com.soapdogg.lispInterpreter.determiner.NumericStringDeterminer
import com.soapdogg.lispInterpreter.evaluator.NodeEvaluator
import com.soapdogg.lispInterpreter.functions.IntFunction
import com.soapdogg.lispInterpreter.generator.NodeGenerator
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class IntFunctionTest {
    private val params = Mockito.mock(ExpressionListNode::class.java)
    private val userDefinedFunctions: List<UserDefinedFunction> = emptyList()
    private val variableNameToValueMap: Map<String, NodeV2> = emptyMap()

    private val functionLengthAsserter = Mockito.mock(FunctionLengthAsserter::class.java)
    private val nodeEvaluator = Mockito.mock(NodeEvaluator::class.java)
    private val numericStringDeterminer = Mockito.mock(NumericStringDeterminer::class.java)
    private val nodeGenerator = Mockito.mock(NodeGenerator::class.java)

    private val intFunction = IntFunction(
        functionLengthAsserter,
        nodeEvaluator,
        numericStringDeterminer,
        nodeGenerator
    )

    @Test
    fun intFunctionTest() {
        val child0 = Mockito.mock(NodeV2::class.java)
        val child1 = Mockito.mock(NodeV2::class.java)
        Mockito.`when`(params.children).thenReturn(listOf(child0, child1))

        val evaluatedResult = Mockito.mock(AtomNode::class.java)
        Mockito.`when`(
            nodeEvaluator.evaluateV2(
                child1,
                userDefinedFunctions,
                variableNameToValueMap,
                true
            )
        ).thenReturn(evaluatedResult)

        val value = ReservedValuesConstants.NIL
        Mockito.`when`(
            evaluatedResult.value
        ).thenReturn(value)

        val result = true
        Mockito.`when`(numericStringDeterminer.isStringNumeric(value)).thenReturn(result)

        val expected = Mockito.mock(AtomNode::class.java)
        Mockito.`when`(nodeGenerator.generateAtomNode(result)).thenReturn(expected)

        val actual = intFunction.evaluateLispFunction(
            params,
            userDefinedFunctions,
            variableNameToValueMap
        )

        Assertions.assertEquals(expected, actual)
        Mockito.verify(functionLengthAsserter).assertLengthIsAsExpected(
            FunctionNameConstants.INT,
            FunctionLengthConstants.TWO,
            params
        )
    }

    @Test
    fun intFunctionIsListTest() {
        val child0 = Mockito.mock(NodeV2::class.java)
        val child1 = Mockito.mock(NodeV2::class.java)
        Mockito.`when`(params.children).thenReturn(listOf(child0, child1))

        val evaluatedResult = Mockito.mock(ExpressionListNode::class.java)
        Mockito.`when`(
            nodeEvaluator.evaluateV2(
                child1,
                userDefinedFunctions,
                variableNameToValueMap,
                true
            )
        ).thenReturn(evaluatedResult)

        val expected = Mockito.mock(AtomNode::class.java)
        Mockito.`when`(nodeGenerator.generateAtomNode(false)).thenReturn(expected)

        val actual = intFunction.evaluateLispFunction(
            params,
            userDefinedFunctions,
            variableNameToValueMap
        )
        Assertions.assertEquals(expected, actual)

        Mockito.verify(functionLengthAsserter).assertLengthIsAsExpected(
            FunctionNameConstants.INT,
            FunctionLengthConstants.TWO,
            params
        )
        Mockito.verifyNoInteractions(numericStringDeterminer)
    }
}