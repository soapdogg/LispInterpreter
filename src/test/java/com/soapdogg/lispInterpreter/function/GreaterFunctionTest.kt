package com.soapdogg.lispInterpreter.function

import com.soapdogg.lispInterpreter.asserter.FunctionLengthAsserter
import com.soapdogg.lispInterpreter.constants.FunctionLengthConstants
import com.soapdogg.lispInterpreter.constants.FunctionNameConstants
import com.soapdogg.lispInterpreter.converter.NodeConverter
import com.soapdogg.lispInterpreter.datamodels.*
import com.soapdogg.lispInterpreter.evaluator.NodeEvaluator
import com.soapdogg.lispInterpreter.functions.GreaterFunction
import com.soapdogg.lispInterpreter.generator.NodeGenerator
import com.soapdogg.lispInterpreter.valueretriver.ListValueRetriever
import com.soapdogg.lispInterpreter.valueretriver.NumericValueRetriever
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class GreaterFunctionTest {
    private val params = Mockito.mock(Node::class.java)
    private val paramsV2 = Mockito.mock(NodeV2::class.java)
    private val userDefinedFunctions: List<UserDefinedFunction> = emptyList()
    private val variableNameToValueMap: Map<String, Node> = emptyMap()

    private val nodeConverter = Mockito.mock(NodeConverter::class.java)
    private val functionLengthAsserter = Mockito.mock(FunctionLengthAsserter::class.java)
    private val nodeEvaluator = Mockito.mock(NodeEvaluator::class.java)
    private val numericValueRetriever = Mockito.mock(NumericValueRetriever::class.java)
    private val listValueRetriever = Mockito.mock(ListValueRetriever::class.java)
    private val nodeGenerator = Mockito.mock(NodeGenerator::class.java)

    private val greaterFunction = GreaterFunction(
        nodeConverter,
        functionLengthAsserter,
        nodeEvaluator,
        numericValueRetriever,
        listValueRetriever,
        nodeGenerator
    )

    @Test
    fun greaterFunctionTest() {
        Mockito.`when`(nodeConverter.convertNodeToNodeV2(params)).thenReturn(paramsV2)
        val evaluatedAddress = Mockito.mock(Node::class.java)
        Mockito.`when`(
            nodeEvaluator.evaluate(
                params,
                userDefinedFunctions,
                variableNameToValueMap,
                true
            )
        ).thenReturn(evaluatedAddress)
        val leftValue = 3
        Mockito.`when`(
            numericValueRetriever.retrieveNumericValue(
                evaluatedAddress,
                1,
                FunctionNameConstants.GREATER
            )
        ).thenReturn(leftValue)
        val expressionNodeParams = Mockito.mock(ExpressionNode::class.java)
        Mockito.`when`(
            listValueRetriever.retrieveListValue(
                params,
                FunctionNameConstants.GREATER,
                variableNameToValueMap
            )
        ).thenReturn(expressionNodeParams)
        val data = Mockito.mock(Node::class.java)
        Mockito.`when`(expressionNodeParams.data).thenReturn(data)
        val evaluatedData = Mockito.mock(Node::class.java)
        Mockito.`when`(
            nodeEvaluator.evaluate(
                data,
                userDefinedFunctions,
                variableNameToValueMap,
                true
            )
        ).thenReturn(evaluatedData)
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
        Mockito.verify(functionLengthAsserter).assertLengthIsAsExpected(
            FunctionNameConstants.GREATER,
            FunctionLengthConstants.TWO,
            paramsV2
        )
    }

    @Test
    fun greaterFunctionTrueTest() {
        Mockito.`when`(nodeConverter.convertNodeToNodeV2(params)).thenReturn(paramsV2)
        val evaluatedAddress = Mockito.mock(Node::class.java)
        Mockito.`when`(
            nodeEvaluator.evaluate(
                params,
                userDefinedFunctions,
                variableNameToValueMap,
                true
            )
        ).thenReturn(evaluatedAddress)
        val leftValue = 3
        Mockito.`when`(
            numericValueRetriever.retrieveNumericValue(
                evaluatedAddress,
                1,
                FunctionNameConstants.GREATER
            )
        ).thenReturn(leftValue)
        val expressionNodeParams = Mockito.mock(ExpressionNode::class.java)
        Mockito.`when`(
            listValueRetriever.retrieveListValue(
                params,
                FunctionNameConstants.GREATER,
                variableNameToValueMap
            )
        ).thenReturn(expressionNodeParams)
        val data = Mockito.mock(Node::class.java)
        Mockito.`when`(expressionNodeParams.data).thenReturn(data)
        val evaluatedData = Mockito.mock(Node::class.java)
        Mockito.`when`(
            nodeEvaluator.evaluate(
                data,
                userDefinedFunctions,
                variableNameToValueMap,
                true
            )
        ).thenReturn(evaluatedData)
        val rightValue = 1
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
        Mockito.verify(functionLengthAsserter).assertLengthIsAsExpected(
            FunctionNameConstants.GREATER,
            FunctionLengthConstants.TWO,
            paramsV2
        )
    }
}