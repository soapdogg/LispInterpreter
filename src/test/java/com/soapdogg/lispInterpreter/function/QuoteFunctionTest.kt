package com.soapdogg.lispInterpreter.function

import com.soapdogg.lispInterpreter.asserter.FunctionLengthAsserter
import com.soapdogg.lispInterpreter.constants.FunctionLengthConstants
import com.soapdogg.lispInterpreter.constants.FunctionNameConstants
import com.soapdogg.lispInterpreter.datamodels.ExpressionNode
import com.soapdogg.lispInterpreter.datamodels.Node
import com.soapdogg.lispInterpreter.datamodels.UserDefinedFunction
import com.soapdogg.lispInterpreter.functions.QuoteFunction
import com.soapdogg.lispInterpreter.valueretriver.ListValueRetriever
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class QuoteFunctionTest {
    private val params: Node = Mockito.mock(ExpressionNode::class.java)
    private val userDefinedFunctions: List<UserDefinedFunction> = emptyList()
    private val variableNameToValueMap: Map<String, Node> = emptyMap()

    private val functionLengthAsserter = Mockito.mock(FunctionLengthAsserter::class.java)
    private val listValueRetriever = Mockito.mock(ListValueRetriever::class.java)

    private val quoteFunction = QuoteFunction(
        functionLengthAsserter,
        listValueRetriever
    )

    @Test
    fun quoteFunctionTest() {
        val expressionNodeParams = Mockito.mock(ExpressionNode::class.java)
        Mockito.`when`(
            listValueRetriever.retrieveListValue(
                params,
                FunctionNameConstants.QUOTE,
                variableNameToValueMap
            )
        ).thenReturn(expressionNodeParams)

        val address = Mockito.mock(Node::class.java)
        Mockito.`when`(expressionNodeParams.address).thenReturn(address)

        val actual = quoteFunction.evaluateLispFunction(
            params,
            userDefinedFunctions,
            variableNameToValueMap
        )
        Assertions.assertEquals(address, actual)
        Mockito.verify(
            functionLengthAsserter
        ).assertLengthIsAsExpected(
            FunctionNameConstants.QUOTE,
            FunctionLengthConstants.TWO,
            params
        )
    }
}