package com.soapdogg.lispInterpreter.function

import com.soapdogg.lispInterpreter.asserter.FunctionLengthAsserter
import com.soapdogg.lispInterpreter.constants.FunctionLengthConstants
import com.soapdogg.lispInterpreter.constants.FunctionNameConstants
import com.soapdogg.lispInterpreter.datamodels.*
import com.soapdogg.lispInterpreter.evaluator.NodeEvaluator
import com.soapdogg.lispInterpreter.functions.TimesFunction
import com.soapdogg.lispInterpreter.generator.NodeGenerator
import com.soapdogg.lispInterpreter.valueretriver.NumericValueRetriever
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class TimesFunctionTest {
    private val params = Mockito.mock(ExpressionListNode::class.java)
    private val userDefinedFunctions: List<UserDefinedFunction> = emptyList()
    private val variableNameToValueMap: Map<String, NodeV2> = emptyMap()

    private val functionLengthAsserter = Mockito.mock(FunctionLengthAsserter::class.java)
    private val nodeEvaluator = Mockito.mock(NodeEvaluator::class.java)
    private val numericValueRetriever = Mockito.mock(NumericValueRetriever::class.java)
    private val nodeGenerator = Mockito.mock(NodeGenerator::class.java)

    private val timesFunction = TimesFunction(
        functionLengthAsserter,
        nodeEvaluator,
        numericValueRetriever,
        nodeGenerator
    )

    @Test
    fun timesFunctionTest() {
        val child0 = Mockito.mock(NodeV2::class.java)
        val child1 = Mockito.mock(NodeV2::class.java)
        val child2 = Mockito.mock(NodeV2::class.java)
        Mockito.`when`(params.children).thenReturn(listOf(child0, child1, child2))

        val evaluatedAddress = Mockito.mock(NodeV2::class.java)
        Mockito.`when`(
            nodeEvaluator.evaluateV2(
                child1,
                userDefinedFunctions,
                variableNameToValueMap,
                true
            )
        ).thenReturn(evaluatedAddress)

        val evaluatedData = Mockito.mock(NodeV2::class.java)
        Mockito.`when`(
            nodeEvaluator.evaluateV2(
                child2,
                userDefinedFunctions,
                variableNameToValueMap,
                true
            )
        ).thenReturn(evaluatedData)

        val leftValue = 3
        Mockito.`when`(
            numericValueRetriever.retrieveNumericValue(
                evaluatedAddress,
                1,
                FunctionNameConstants.TIMES
            )
        ).thenReturn(leftValue)

        val rightValue = 5
        Mockito.`when`(
            numericValueRetriever.retrieveNumericValue(
                evaluatedData,
                2,
                FunctionNameConstants.TIMES
            )
        ).thenReturn(rightValue)

        val expected = Mockito.mock(AtomNode::class.java)
        Mockito.`when`(
            nodeGenerator.generateAtomNode(
                leftValue * rightValue
            )
        ).thenReturn(expected)

        val actual = timesFunction.evaluateLispFunction(
            params,
            userDefinedFunctions,
            variableNameToValueMap
        )
        Assertions.assertEquals(expected, actual)
        Mockito.verify(functionLengthAsserter).assertLengthIsAsExpected(
            FunctionNameConstants.TIMES,
            FunctionLengthConstants.THREE,
            params
        )
    }
}