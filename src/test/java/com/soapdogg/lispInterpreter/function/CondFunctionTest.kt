package com.soapdogg.lispInterpreter.function

import com.soapdogg.lispInterpreter.asserter.CondFunctionParameterAsserter
import com.soapdogg.lispInterpreter.comparator.NodeValueComparator
import com.soapdogg.lispInterpreter.datamodels.AtomNode
import com.soapdogg.lispInterpreter.datamodels.ExpressionListNode
import com.soapdogg.lispInterpreter.datamodels.NodeV2
import com.soapdogg.lispInterpreter.datamodels.UserDefinedFunction
import com.soapdogg.lispInterpreter.evaluator.NodeEvaluator
import com.soapdogg.lispInterpreter.exceptions.NotAListException
import com.soapdogg.lispInterpreter.functions.CondFunction
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class CondFunctionTest {
    private val params = Mockito.mock(ExpressionListNode::class.java)
    private val userDefinedFunctions: List<UserDefinedFunction> = emptyList()
    private val variableNameToValueMap: Map<String, NodeV2> = emptyMap()

    private val condFunctionParameterAsserter = Mockito.mock(CondFunctionParameterAsserter::class.java)
    private val nodeEvaluator = Mockito.mock(NodeEvaluator::class.java)
    private val nodeValueComparator = Mockito.mock(NodeValueComparator::class.java)

    private val condFunction = CondFunction(
        condFunctionParameterAsserter,
        nodeEvaluator,
        nodeValueComparator
    )

    @Test
    fun evaluatedNodeIsNotAtomNodeTest() {
        val child0 = Mockito.mock(NodeV2::class.java)
        val child1 = Mockito.mock(NodeV2::class.java)
        Mockito.`when`(params.children).thenReturn(listOf(child0, child1))

        val child1ExpressionList = Mockito.mock(ExpressionListNode::class.java)
        Mockito.`when`(
            condFunctionParameterAsserter.assertCondFunctionParameters(
                listOf(child1)
            )
        ).thenReturn(listOf(child1ExpressionList))

        val c1 = Mockito.mock(NodeV2::class.java)
        val c2 = Mockito.mock(NodeV2::class.java)
        Mockito.`when`(child1ExpressionList.children).thenReturn(listOf(c1, c2))

        val evaluatedNode = Mockito.mock(ExpressionListNode::class.java)
        Mockito.`when`(
            nodeEvaluator.evaluateV2(
                c1,
                userDefinedFunctions,
                variableNameToValueMap
            )
        ).thenReturn(evaluatedNode)

        Assertions.assertThrows(
            NotAListException::class.java
        ){
            condFunction.evaluateLispFunction(
                params,
                userDefinedFunctions,
                variableNameToValueMap
            )
        }
    }

    @Test
    fun evaluatedNodeIsNilTest() {
        val child0 = Mockito.mock(NodeV2::class.java)
        val child1 = Mockito.mock(NodeV2::class.java)
        Mockito.`when`(params.children).thenReturn(listOf(child0, child1))

        val child1ExpressionList = Mockito.mock(ExpressionListNode::class.java)
        Mockito.`when`(
            condFunctionParameterAsserter.assertCondFunctionParameters(
                listOf(child1)
            )
        ).thenReturn(listOf(child1ExpressionList))

        val c1 = Mockito.mock(NodeV2::class.java)
        val c2 = Mockito.mock(NodeV2::class.java)
        Mockito.`when`(child1ExpressionList.children).thenReturn(listOf(c1, c2))

        val evaluatedNode = Mockito.mock(AtomNode::class.java)
        Mockito.`when`(
            nodeEvaluator.evaluateV2(
                c1,
                userDefinedFunctions,
                variableNameToValueMap
            )
        ).thenReturn(evaluatedNode)

        val value = "value"
        Mockito.`when`(evaluatedNode.value).thenReturn(value)

        Mockito.`when`(nodeValueComparator.equalsNil(value)).thenReturn(true)

        Assertions.assertThrows(
            NotAListException::class.java
        ){
            condFunction.evaluateLispFunction(
                params,
                userDefinedFunctions,
                variableNameToValueMap
            )
        }
    }

    @Test
    fun evaluatedNodeIsValidTest() {
        val child0 = Mockito.mock(NodeV2::class.java)
        val child1 = Mockito.mock(NodeV2::class.java)
        Mockito.`when`(params.children).thenReturn(listOf(child0, child1))

        val child1ExpressionList = Mockito.mock(ExpressionListNode::class.java)
        Mockito.`when`(
            condFunctionParameterAsserter.assertCondFunctionParameters(
                listOf(child1)
            )
        ).thenReturn(listOf(child1ExpressionList))

        val c1 = Mockito.mock(NodeV2::class.java)
        val c2 = Mockito.mock(NodeV2::class.java)
        Mockito.`when`(child1ExpressionList.children).thenReturn(listOf(c1, c2))

        val evaluatedNode = Mockito.mock(AtomNode::class.java)
        Mockito.`when`(
            nodeEvaluator.evaluateV2(
                c1,
                userDefinedFunctions,
                variableNameToValueMap
            )
        ).thenReturn(evaluatedNode)

        val value = "value"
        Mockito.`when`(evaluatedNode.value).thenReturn(value)

        Mockito.`when`(nodeValueComparator.equalsNil(value)).thenReturn(false)

        val expected = Mockito.mock(NodeV2::class.java)
        Mockito.`when`(
            nodeEvaluator.evaluateV2(
                c2,
                userDefinedFunctions,
                variableNameToValueMap
            )
        ).thenReturn(expected)

        val actual = condFunction.evaluateLispFunction(
            params,
            userDefinedFunctions,
            variableNameToValueMap
        )

        Assertions.assertEquals(expected, actual)
    }
}