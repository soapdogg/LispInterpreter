package com.soapdogg.lispInterpreter.function

import com.soapdogg.lispInterpreter.constants.FunctionNameConstants
import com.soapdogg.lispInterpreter.datamodels.*
import com.soapdogg.lispInterpreter.evaluator.NodeEvaluator
import com.soapdogg.lispInterpreter.functions.CdrFunction
import com.soapdogg.lispInterpreter.generator.NodeGenerator
import com.soapdogg.lispInterpreter.valueretriver.ListValueRetriever
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class CdrFunctionTest {

    private val params = Mockito.mock(ExpressionListNode::class.java)
    private val userDefinedFunctions: List<UserDefinedFunction> = emptyList()
    private val variableNameToValueMap: Map<String, NodeV2> = emptyMap()

    private val listValueRetriever = Mockito.mock(ListValueRetriever::class.java)
    private val nodeEvaluator = Mockito.mock(NodeEvaluator::class.java)
    private val nodeGenerator = Mockito.mock(NodeGenerator::class.java)

    private val cdrFunction = CdrFunction(
        listValueRetriever,
        nodeEvaluator,
        nodeGenerator
    )

    @Test
    fun cdrFunctionOneChildTest() {
        val child0 = Mockito.mock(NodeV2::class.java)
        val child1 = Mockito.mock(NodeV2::class.java)
        Mockito.`when`(params.children).thenReturn(listOf(child0, child1))

        val evaluatedChild = Mockito.mock(NodeV2::class.java)
        Mockito.`when`(
            nodeEvaluator.evaluateV2(
                child1,
                userDefinedFunctions,
                variableNameToValueMap
            )
        ).thenReturn(evaluatedChild)

        val evaluatedChildExpressionList = Mockito.mock(ExpressionListNode::class.java)
        Mockito.`when`(
            listValueRetriever.retrieveListValue(
                evaluatedChild,
                FunctionNameConstants.CDR,
                variableNameToValueMap
            )
        ).thenReturn(evaluatedChildExpressionList)

        val c0 = Mockito.mock(NodeV2::class.java)
        Mockito.`when`(evaluatedChildExpressionList.children).thenReturn(listOf(c0))

        val actual = cdrFunction.evaluateLispFunction(
            params,
            userDefinedFunctions,
            variableNameToValueMap
        )
        Assertions.assertEquals(c0, actual)
    }

    @Test
    fun cdrFunctionTwoChildrenTest() {
        val child0 = Mockito.mock(NodeV2::class.java)
        val child1 = Mockito.mock(NodeV2::class.java)
        Mockito.`when`(params.children).thenReturn(listOf(child0, child1))

        val evaluatedChild = Mockito.mock(NodeV2::class.java)
        Mockito.`when`(
            nodeEvaluator.evaluateV2(
                child1,
                userDefinedFunctions,
                variableNameToValueMap
            )
        ).thenReturn(evaluatedChild)

        val evaluatedChildExpressionList = Mockito.mock(ExpressionListNode::class.java)
        Mockito.`when`(
            listValueRetriever.retrieveListValue(
                evaluatedChild,
                FunctionNameConstants.CDR,
                variableNameToValueMap
            )
        ).thenReturn(evaluatedChildExpressionList)

        val c0 = Mockito.mock(NodeV2::class.java)
        val c1 = Mockito.mock(NodeV2::class.java)
        Mockito.`when`(evaluatedChildExpressionList.children).thenReturn(listOf(c0, c1))

        val actual = cdrFunction.evaluateLispFunction(
            params,
            userDefinedFunctions,
            variableNameToValueMap
        )
        Assertions.assertEquals(c1, actual)
    }

    @Test
    fun cdrFunctionThreeChildrenTest() {
        val child0 = Mockito.mock(NodeV2::class.java)
        val child1 = Mockito.mock(NodeV2::class.java)
        Mockito.`when`(params.children).thenReturn(listOf(child0, child1))

        val evaluatedChild = Mockito.mock(NodeV2::class.java)
        Mockito.`when`(
            nodeEvaluator.evaluateV2(
                child1,
                userDefinedFunctions,
                variableNameToValueMap
            )
        ).thenReturn(evaluatedChild)

        val evaluatedChildExpressionList = Mockito.mock(ExpressionListNode::class.java)
        Mockito.`when`(
            listValueRetriever.retrieveListValue(
                evaluatedChild,
                FunctionNameConstants.CDR,
                variableNameToValueMap
            )
        ).thenReturn(evaluatedChildExpressionList)

        val c0 = Mockito.mock(NodeV2::class.java)
        val c1 = Mockito.mock(NodeV2::class.java)
        val c2 = Mockito.mock(NodeV2::class.java)
        Mockito.`when`(evaluatedChildExpressionList.children).thenReturn(listOf(c0, c1, c2))

        val expected = Mockito.mock(ExpressionListNode::class.java)
        Mockito.`when`(
            nodeGenerator.generateExpressionListNode(
                listOf(c1, c2)
            )
        ).thenReturn(expected)

        val actual = cdrFunction.evaluateLispFunction(
            params,
            userDefinedFunctions,
            variableNameToValueMap
        )
        Assertions.assertEquals(expected, actual)
    }
}