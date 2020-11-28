package com.soapdogg.lispInterpreter.function

import com.soapdogg.lispInterpreter.asserter.FunctionLengthAsserter
import com.soapdogg.lispInterpreter.constants.FunctionLengthConstants
import com.soapdogg.lispInterpreter.constants.FunctionNameConstants
import com.soapdogg.lispInterpreter.datamodels.AtomNode
import com.soapdogg.lispInterpreter.datamodels.ExpressionNode
import com.soapdogg.lispInterpreter.datamodels.Node
import com.soapdogg.lispInterpreter.datamodels.UserDefinedFunction
import com.soapdogg.lispInterpreter.evaluator.NodeEvaluator
import com.soapdogg.lispInterpreter.functions.EqFunction
import com.soapdogg.lispInterpreter.generator.NodeGenerator
import com.soapdogg.lispInterpreter.valueretriver.AtomicValueRetriever
import com.soapdogg.lispInterpreter.valueretriver.ListValueRetriever
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class EqFunctionTest {
    private val params = Mockito.mock(Node::class.java)
    private val userDefinedFunctions: List<UserDefinedFunction> = emptyList()
    private val variableNameToValueMap: Map<String, Node> = emptyMap()

    private val functionLengthAsserter = Mockito.mock(FunctionLengthAsserter::class.java)
    private val nodeEvaluator = Mockito.mock(NodeEvaluator::class.java)
    private val atomicValueRetriever = Mockito.mock(AtomicValueRetriever::class.java)
    private val listValueRetriever = Mockito.mock(ListValueRetriever::class.java)
    private val nodeGenerator = Mockito.mock(NodeGenerator::class.java)

    private val eqFunction = EqFunction(
        functionLengthAsserter,
        nodeEvaluator,
        atomicValueRetriever,
        listValueRetriever,
        nodeGenerator
    )

    @Test
    fun eqFunctionTest() {
        val evaluatedAddress = Mockito.mock(Node::class.java)
        Mockito.`when`(
            nodeEvaluator.evaluate(
                params,
                userDefinedFunctions,
                variableNameToValueMap,
                true
            )
        ).thenReturn(evaluatedAddress)

        val leftValue = "leftValue"
        Mockito.`when`(
            atomicValueRetriever.retrieveAtomicValue(
                evaluatedAddress,
                1,
                FunctionNameConstants.EQ
            )
        ).thenReturn(leftValue)

        val expressionNodeParams = Mockito.mock(ExpressionNode::class.java)
        Mockito.`when`(
            listValueRetriever.retrieveListValue(
                params,
                FunctionNameConstants.EQ,
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

        val rightValue = "rightValue"
        Mockito.`when`(
            atomicValueRetriever.retrieveAtomicValue(
                evaluatedData,
                2,
                FunctionNameConstants.EQ
            )
        ).thenReturn(rightValue)

        val expected = Mockito.mock(AtomNode::class.java)
        Mockito.`when`(
            nodeGenerator.generateAtomNode(leftValue == rightValue)
        ).thenReturn(expected)

        val actual = eqFunction.evaluateLispFunction(
            params,
            userDefinedFunctions,
            variableNameToValueMap
        )
        Assertions.assertEquals(expected, actual)
        Mockito.verify(functionLengthAsserter).assertLengthIsAsExpected(
            FunctionNameConstants.EQ,
            FunctionLengthConstants.THREE,
            params
        )
    }
}