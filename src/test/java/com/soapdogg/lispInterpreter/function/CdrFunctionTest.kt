package com.soapdogg.lispInterpreter.function

import com.soapdogg.lispInterpreter.asserter.FunctionLengthAsserter
import com.soapdogg.lispInterpreter.constants.FunctionLengthConstants
import com.soapdogg.lispInterpreter.constants.FunctionNameConstants
import com.soapdogg.lispInterpreter.datamodels.ExpressionNode
import com.soapdogg.lispInterpreter.datamodels.Node
import com.soapdogg.lispInterpreter.datamodels.UserDefinedFunction
import com.soapdogg.lispInterpreter.evaluator.NodeEvaluator
import com.soapdogg.lispInterpreter.functions.CdrFunction
import com.soapdogg.lispInterpreter.valueretriver.ListValueRetriever
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class CdrFunctionTest {

    private val params = Mockito.mock(Node::class.java)
    private val userDefinedFunctions: List<UserDefinedFunction> = emptyList()
    private val variableNameToValueMap: Map<String, Node> = emptyMap()

    private val functionLengthAsserter = Mockito.mock(FunctionLengthAsserter::class.java)
    private val listValueRetriever = Mockito.mock(ListValueRetriever::class.java)
    private val nodeEvaluator = Mockito.mock(NodeEvaluator::class.java)

    private val cdrFunction = CdrFunction(
        functionLengthAsserter,
        listValueRetriever,
        nodeEvaluator
    )

    @Test
    fun cdrFunctionTest() {
        val expressionNodeParams = Mockito.mock(ExpressionNode::class.java)
        Mockito.`when`(
            listValueRetriever.retrieveListValue(
                params,
                FunctionNameConstants.CDR,
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
                false
            )
        ).thenReturn(evaluatedAddress)

        val node = Mockito.mock(ExpressionNode::class.java)
        Mockito.`when`(
            listValueRetriever.retrieveListValue(
                evaluatedAddress,
                FunctionNameConstants.CDR,
                variableNameToValueMap
            )
        ).thenReturn(node)

        val expected = Mockito.mock(Node::class.java)
        Mockito.`when`(node.data).thenReturn(expected)

        val actual = cdrFunction.evaluateLispFunction(
            params,
            userDefinedFunctions,
            variableNameToValueMap
        )
        Assertions.assertEquals(expected, actual)
        Mockito.verify(functionLengthAsserter).assertLengthIsAsExpected(
            FunctionNameConstants.CDR,
            FunctionLengthConstants.TWO,
            params
        )
    }
}