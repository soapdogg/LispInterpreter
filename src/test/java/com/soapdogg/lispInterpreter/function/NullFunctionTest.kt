package com.soapdogg.lispInterpreter.function

import com.soapdogg.lispInterpreter.asserter.FunctionLengthAsserter
import com.soapdogg.lispInterpreter.comparator.NodeValueComparator
import com.soapdogg.lispInterpreter.constants.FunctionLengthConstants
import com.soapdogg.lispInterpreter.constants.FunctionNameConstants
import com.soapdogg.lispInterpreter.constants.ReservedValuesConstants
import com.soapdogg.lispInterpreter.datamodels.AtomNode
import com.soapdogg.lispInterpreter.datamodels.Node
import com.soapdogg.lispInterpreter.datamodels.UserDefinedFunction
import com.soapdogg.lispInterpreter.determiner.ExpressionNodeDeterminer
import com.soapdogg.lispInterpreter.evaluator.NodeEvaluator
import com.soapdogg.lispInterpreter.functions.NullFunction
import com.soapdogg.lispInterpreter.generator.NodeGenerator
import com.soapdogg.lispInterpreter.valueretriver.AtomicValueRetriever
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class NullFunctionTest {
    private val params = Mockito.mock(Node::class.java)
    private val userDefinedFunctions: List<UserDefinedFunction> = emptyList()
    private val variableNameToValueMap: Map<String, Node> = emptyMap()

    private val functionLengthAsserter = Mockito.mock(FunctionLengthAsserter::class.java)
    private val nodeEvaluator = Mockito.mock(NodeEvaluator::class.java)
    private val expressionNodeDeterminer = Mockito.mock(ExpressionNodeDeterminer::class.java)
    private val atomicValueRetriever = Mockito.mock(AtomicValueRetriever::class.java)
    private val nodeValueComparator = Mockito.mock(NodeValueComparator::class.java)
    private val nodeGenerator = Mockito.mock(NodeGenerator::class.java)

    private val nullFunction = NullFunction(
        functionLengthAsserter,
        nodeEvaluator,
        expressionNodeDeterminer,
        atomicValueRetriever,
        nodeValueComparator,
        nodeGenerator
    )

    @Test
    fun nullFunctionTest() {
        val evaluatedResult = Mockito.mock(Node::class.java)
        Mockito.`when`(
            nodeEvaluator.evaluate(
                params,
                userDefinedFunctions,
                variableNameToValueMap,
                true
            )
        ).thenReturn(evaluatedResult)
        Mockito.`when`(expressionNodeDeterminer.isExpressionNode(evaluatedResult)).thenReturn(false)

        val value = ReservedValuesConstants.NIL
        Mockito.`when`(
            atomicValueRetriever.retrieveAtomicValue(
                evaluatedResult,
                1,
                FunctionNameConstants.NULL
            )
        ).thenReturn(value)

        val result = true
        Mockito.`when`(nodeValueComparator.equalsNil(value)).thenReturn(result)

        val expected = Mockito.mock(AtomNode::class.java)
        Mockito.`when`(nodeGenerator.generateAtomNode(result)).thenReturn(expected)

        val actual = nullFunction.evaluateLispFunction(
            params,
            userDefinedFunctions,
            variableNameToValueMap
        )
        Assertions.assertEquals(expected, actual)
        Mockito.verify(functionLengthAsserter).assertLengthIsAsExpected(
            FunctionNameConstants.NULL,
            FunctionLengthConstants.TWO,
            params
        )
    }

    @Test
    fun nullFunctionIsListTest() {
        val evaluatedResult = Mockito.mock(Node::class.java)
        Mockito.`when`(
            nodeEvaluator.evaluate(
                params,
                userDefinedFunctions,
                variableNameToValueMap,
                true
            )
        ).thenReturn(evaluatedResult)
        Mockito.`when`(expressionNodeDeterminer.isExpressionNode(evaluatedResult)).thenReturn(true)

        val expected = Mockito.mock(AtomNode::class.java)
        Mockito.`when`(nodeGenerator.generateAtomNode(false)).thenReturn(expected)

        val actual = nullFunction.evaluateLispFunction(
            params,
            userDefinedFunctions,
            variableNameToValueMap
        )
        Assertions.assertEquals(expected, actual)
        Mockito.verify(functionLengthAsserter).assertLengthIsAsExpected(
            FunctionNameConstants.NULL,
            FunctionLengthConstants.TWO,
            params
        )
        Mockito.verifyNoInteractions(atomicValueRetriever)
        Mockito.verifyNoInteractions(nodeValueComparator)
    }
}