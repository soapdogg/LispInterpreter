package com.soapdogg.lispInterpreter.function

import com.soapdogg.lispInterpreter.asserter.FunctionLengthAsserter
import com.soapdogg.lispInterpreter.constants.FunctionLengthConstants
import com.soapdogg.lispInterpreter.constants.FunctionNameConstants
import com.soapdogg.lispInterpreter.constants.ReservedValuesConstants
import com.soapdogg.lispInterpreter.datamodels.AtomNode
import com.soapdogg.lispInterpreter.datamodels.ExpressionNode
import com.soapdogg.lispInterpreter.datamodels.Node
import com.soapdogg.lispInterpreter.datamodels.UserDefinedFunction
import com.soapdogg.lispInterpreter.determiner.NumericStringDeterminer
import com.soapdogg.lispInterpreter.evaluator.NodeEvaluator
import com.soapdogg.lispInterpreter.functions.IntFunction
import com.soapdogg.lispInterpreter.generator.NodeGenerator
import com.soapdogg.lispInterpreter.valueretriver.AtomicValueRetriever
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class IntFunctionTest {
    private val params = Mockito.mock(Node::class.java)
    private val userDefinedFunctions: List<UserDefinedFunction> = emptyList()
    private val variableNameToValueMap: Map<String, Node> = emptyMap()

    private val functionLengthAsserter = Mockito.mock(FunctionLengthAsserter::class.java)
    private val nodeEvaluator = Mockito.mock(NodeEvaluator::class.java)
    private val atomicValueRetriever = Mockito.mock(AtomicValueRetriever::class.java)
    private val numericStringDeterminer = Mockito.mock(NumericStringDeterminer::class.java)
    private val nodeGenerator = Mockito.mock(NodeGenerator::class.java)

    private val intFunction = IntFunction(
        functionLengthAsserter,
        nodeEvaluator,
        atomicValueRetriever,
        numericStringDeterminer,
        nodeGenerator
    )

    @Test
    fun intFunctionTest() {
        val evaluatedResult = Mockito.mock(Node::class.java)
        Mockito.`when`(
            nodeEvaluator.evaluate(
                params,
                userDefinedFunctions,
                variableNameToValueMap,
                true
            )
        ).thenReturn(evaluatedResult)
        val value = ReservedValuesConstants.NIL
        Mockito.`when`(
            atomicValueRetriever.retrieveAtomicValue(
                evaluatedResult,
                1,
                FunctionNameConstants.INT
            )
        ).thenReturn(value)
        val result = true
        Mockito.`when`(numericStringDeterminer.isStringNumeric(value)).thenReturn(result)
        val expected = Mockito.mock(AtomNode::class.java)
        Mockito.`when`(nodeGenerator.generateAtomNode(result)).thenReturn(expected)
        val actual = intFunction.evaluateLispFunction(
            params,
            userDefinedFunctions,
            variableNameToValueMap
        )
        Assertions.assertEquals(expected, actual)
        Mockito.verify(functionLengthAsserter).assertLengthIsAsExpected(
            FunctionNameConstants.INT,
            FunctionLengthConstants.TWO,
            params
        )
    }

    @Test
    fun intFunctionIsListTest() {
        val evaluatedResult = Mockito.mock(ExpressionNode::class.java)
        Mockito.`when`(
            nodeEvaluator.evaluate(
                params,
                userDefinedFunctions,
                variableNameToValueMap,
                true
            )
        ).thenReturn(evaluatedResult)
        val expected = Mockito.mock(AtomNode::class.java)
        Mockito.`when`(nodeGenerator.generateAtomNode(false)).thenReturn(expected)
        val actual = intFunction.evaluateLispFunction(
            params,
            userDefinedFunctions,
            variableNameToValueMap
        )
        Assertions.assertEquals(expected, actual)
        Mockito.verify(functionLengthAsserter).assertLengthIsAsExpected(
            FunctionNameConstants.INT,
            FunctionLengthConstants.TWO,
            params
        )
        Mockito.verifyNoInteractions(atomicValueRetriever)
        Mockito.verifyNoInteractions(numericStringDeterminer)
    }
}