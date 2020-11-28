package com.soapdogg.lispInterpreter.function

import com.soapdogg.lispInterpreter.asserter.FunctionLengthAsserter
import com.soapdogg.lispInterpreter.constants.FunctionLengthConstants
import com.soapdogg.lispInterpreter.constants.FunctionNameConstants
import com.soapdogg.lispInterpreter.datamodels.AtomNode
import com.soapdogg.lispInterpreter.datamodels.Node
import com.soapdogg.lispInterpreter.datamodels.UserDefinedFunction
import com.soapdogg.lispInterpreter.determiner.ExpressionNodeDeterminer
import com.soapdogg.lispInterpreter.evaluator.NodeEvaluator
import com.soapdogg.lispInterpreter.functions.AtomFunction
import com.soapdogg.lispInterpreter.generator.NodeGenerator
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class AtomFunctionTest {
    private val params = Mockito.mock(Node::class.java)
    private val userDefinedFunctions: List<UserDefinedFunction> = listOf()
    private val variableNameToValueMap: Map<String, Node> = mapOf()
    private val functionLengthAsserter = Mockito.mock(FunctionLengthAsserter::class.java)
    private val nodeEvaluator = Mockito.mock(NodeEvaluator::class.java)
    private val expressionNodeDeterminer = Mockito.mock(ExpressionNodeDeterminer::class.java)
    private val nodeGenerator = Mockito.mock(NodeGenerator::class.java)
    private val atomFunction = AtomFunction(
        functionLengthAsserter,
        nodeEvaluator,
        expressionNodeDeterminer,
        nodeGenerator
    )

    @Test
    fun atomFunctionTest() {
        val evaluatedResult = Mockito.mock(Node::class.java)
        Mockito.`when`(
            nodeEvaluator.evaluate(
                params,
                userDefinedFunctions,
                variableNameToValueMap,
                true
            )
        ).thenReturn(evaluatedResult)
        val result = true
        Mockito.`when`(expressionNodeDeterminer.isExpressionNode(evaluatedResult)).thenReturn(result)
        val expected = Mockito.mock(AtomNode::class.java)
        Mockito.`when`(nodeGenerator.generateAtomNode(!result)).thenReturn(expected)
        val actual = atomFunction.evaluateLispFunction(
            params,
            userDefinedFunctions,
            variableNameToValueMap
        )
        Assertions.assertEquals(expected, actual)
        Mockito.verify(functionLengthAsserter).assertLengthIsAsExpected(
            FunctionNameConstants.ATOM,
            FunctionLengthConstants.TWO,
            params
        )
    }

    @Test
    fun atomFunctionFalseTest() {
        val evaluatedResult = Mockito.mock(Node::class.java)
        Mockito.`when`(
            nodeEvaluator.evaluate(
                params,
                userDefinedFunctions,
                variableNameToValueMap,
                true
            )
        ).thenReturn(evaluatedResult)
        val result = false
        Mockito.`when`(expressionNodeDeterminer.isExpressionNode(evaluatedResult)).thenReturn(result)
        val expected = Mockito.mock(AtomNode::class.java)
        Mockito.`when`(nodeGenerator.generateAtomNode(!result)).thenReturn(expected)
        val actual = atomFunction.evaluateLispFunction(
            params,
            userDefinedFunctions,
            variableNameToValueMap
        )
        Assertions.assertEquals(expected, actual)
        Mockito.verify(functionLengthAsserter).assertLengthIsAsExpected(
            FunctionNameConstants.ATOM,
            FunctionLengthConstants.TWO,
            params
        )
    }
}