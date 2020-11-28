package com.soapdogg.lispInterpreter.function

import com.soapdogg.lispInterpreter.asserter.FunctionLengthAsserter
import com.soapdogg.lispInterpreter.constants.FunctionLengthConstants
import com.soapdogg.lispInterpreter.constants.FunctionNameConstants
import com.soapdogg.lispInterpreter.datamodels.ExpressionNode
import com.soapdogg.lispInterpreter.datamodels.Node
import com.soapdogg.lispInterpreter.datamodels.UserDefinedFunction
import com.soapdogg.lispInterpreter.evaluator.NodeEvaluator
import com.soapdogg.lispInterpreter.functions.ConsFunction
import com.soapdogg.lispInterpreter.generator.NodeGenerator
import com.soapdogg.lispInterpreter.valueretriver.ListValueRetriever
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class ConsFunctionTest {
    private val params = Mockito.mock(Node::class.java)
    private val userDefinedFunctions: List<UserDefinedFunction> = emptyList()
    private val variableNameToValueMap: Map<String, Node> = emptyMap()

    private val functionLengthAsserter = Mockito.mock(FunctionLengthAsserter::class.java)
    private val listValueRetriever = Mockito.mock(ListValueRetriever::class.java)
    private val nodeEvaluator = Mockito.mock(NodeEvaluator::class.java)
    private val nodeGenerator = Mockito.mock(NodeGenerator::class.java)

    private val consFunction = ConsFunction(
        functionLengthAsserter,
        listValueRetriever,
        nodeEvaluator,
        nodeGenerator
    )

    @Test
    fun consFunctionTest() {
        val expressionNodeParams = Mockito.mock(ExpressionNode::class.java)
        Mockito.`when`(
            listValueRetriever.retrieveListValue(
                params,
                FunctionNameConstants.CONS,
                variableNameToValueMap
            )
        ).thenReturn(expressionNodeParams)

        val address = Mockito.mock(Node::class.java)
        Mockito.`when`(expressionNodeParams.address).thenReturn(address)

        val evaluatedAddress = Mockito.mock(Node::class.java)
        Mockito.`when`(
            nodeEvaluator.evaluate(
                address,
                userDefinedFunctions,
                variableNameToValueMap,
                true
            )
        ).thenReturn(evaluatedAddress)

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

        val expected = Mockito.mock(ExpressionNode::class.java)
        Mockito.`when`(
            nodeGenerator.generateExpressionNode(
                evaluatedAddress,
                evaluatedData
            )
        ).thenReturn(expected)

        val actual = consFunction.evaluateLispFunction(
            params,
            userDefinedFunctions,
            variableNameToValueMap
        )
        Assertions.assertEquals(expected, actual)
        Mockito.verify(functionLengthAsserter).assertLengthIsAsExpected(
            FunctionNameConstants.CONS,
            FunctionLengthConstants.THREE,
            params
        )
    }
}