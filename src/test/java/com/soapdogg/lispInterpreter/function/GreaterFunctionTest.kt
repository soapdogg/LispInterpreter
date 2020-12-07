package com.soapdogg.lispInterpreter.function

import com.soapdogg.lispInterpreter.constants.FunctionNameConstants
import com.soapdogg.lispInterpreter.datamodels.*
import com.soapdogg.lispInterpreter.evaluator.NodeEvaluator
import com.soapdogg.lispInterpreter.functions.GreaterFunction
import com.soapdogg.lispInterpreter.generator.NodeGenerator
import com.soapdogg.lispInterpreter.valueretriver.NumericValueRetriever
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class GreaterFunctionTest {
    private val params = Mockito.mock(ExpressionListNode::class.java)
    private val userDefinedFunctions: List<UserDefinedFunction> = emptyList()
    private val variableNameToValueMap: Map<String, NodeV2> = emptyMap()

    private val nodeEvaluator = Mockito.mock(NodeEvaluator::class.java)
    private val numericValueRetriever = Mockito.mock(NumericValueRetriever::class.java)
    private val nodeGenerator = Mockito.mock(NodeGenerator::class.java)

    private val greaterFunction = GreaterFunction(
        nodeEvaluator,
        numericValueRetriever,
        nodeGenerator
    )

    @Test
    fun greaterFunctionTest() {
        val child0 = Mockito.mock(NodeV2::class.java)
        val child1 = Mockito.mock(NodeV2::class.java)
        val child2 = Mockito.mock(NodeV2::class.java)
        Mockito.`when`(params.children).thenReturn(listOf(child0, child1, child2))

        val evaluatedAddress = Mockito.mock(NodeV2::class.java)
        Mockito.`when`(
            nodeEvaluator.evaluateV2(
                child1,
                userDefinedFunctions,
                variableNameToValueMap
            )
        ).thenReturn(evaluatedAddress)

        val evaluatedData = Mockito.mock(NodeV2::class.java)
        Mockito.`when`(
            nodeEvaluator.evaluateV2(
                child2,
                userDefinedFunctions,
                variableNameToValueMap
            )
        ).thenReturn(evaluatedData)

        val leftValue = 3
        Mockito.`when`(
            numericValueRetriever.retrieveNumericValue(
                evaluatedAddress,
                1,
                FunctionNameConstants.GREATER
            )
        ).thenReturn(leftValue)

        val rightValue = 5
        Mockito.`when`(
            numericValueRetriever.retrieveNumericValue(
                evaluatedData,
                2,
                FunctionNameConstants.GREATER
            )
        ).thenReturn(rightValue)

        val expected = Mockito.mock(AtomNode::class.java)
        Mockito.`when`(
            nodeGenerator.generateAtomNode(
                leftValue > rightValue
            )
        ).thenReturn(expected)

        val actual = greaterFunction.evaluateLispFunction(
            params,
            userDefinedFunctions,
            variableNameToValueMap
        )
        Assertions.assertEquals(expected, actual)
    }
}