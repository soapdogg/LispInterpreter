package com.soapdogg.lispInterpreter.function

import com.soapdogg.lispInterpreter.asserter.FunctionLengthAsserter
import com.soapdogg.lispInterpreter.constants.FunctionLengthConstants
import com.soapdogg.lispInterpreter.constants.FunctionNameConstants
import com.soapdogg.lispInterpreter.datamodels.AtomNode
import com.soapdogg.lispInterpreter.datamodels.ExpressionNode
import com.soapdogg.lispInterpreter.datamodels.Node
import com.soapdogg.lispInterpreter.datamodels.UserDefinedFunction
import com.soapdogg.lispInterpreter.evaluator.NodeEvaluator
import com.soapdogg.lispInterpreter.functions.TimesFunction
import com.soapdogg.lispInterpreter.generator.NodeGenerator
import com.soapdogg.lispInterpreter.valueretriver.ListValueRetriever
import com.soapdogg.lispInterpreter.valueretriver.NumericValueRetriever
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class TimesFunctionTest {
    private val params = Mockito.mock(Node::class.java)
    private val userDefinedFunctions: List<UserDefinedFunction> = listOf()
    private val variableNameToValueMap: Map<String, Node> = mapOf()
    private val functionLengthAsserter = Mockito.mock(FunctionLengthAsserter::class.java)
    private val nodeEvaluator = Mockito.mock(NodeEvaluator::class.java)
    private val numericValueRetriever = Mockito.mock(NumericValueRetriever::class.java)
    private val listValueRetriever = Mockito.mock(ListValueRetriever::class.java)
    private val nodeGenerator = Mockito.mock(NodeGenerator::class.java)
    private val timesFunction = TimesFunction(
        functionLengthAsserter,
        nodeEvaluator,
        numericValueRetriever,
        listValueRetriever,
        nodeGenerator
    )

    @Test
    fun timesFunctionTest() {
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
                FunctionNameConstants.TIMES
            )
        ).thenReturn(leftValue)

        val expressionNodeParams = Mockito.mock(ExpressionNode::class.java)
        Mockito.`when`(
            listValueRetriever.retrieveListValue(
                params,
                FunctionNameConstants.TIMES,
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