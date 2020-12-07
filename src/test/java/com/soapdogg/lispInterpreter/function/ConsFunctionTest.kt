package com.soapdogg.lispInterpreter.function

import com.soapdogg.lispInterpreter.datamodels.*
import com.soapdogg.lispInterpreter.evaluator.NodeEvaluator
import com.soapdogg.lispInterpreter.functions.ConsFunction
import com.soapdogg.lispInterpreter.generator.NodeGenerator
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class ConsFunctionTest {
    private val params = Mockito.mock(ExpressionListNode::class.java)
    private val userDefinedFunctions: List<UserDefinedFunction> = emptyList()
    private val variableNameToValueMap: Map<String, NodeV2> = emptyMap()

    private val nodeEvaluator = Mockito.mock(NodeEvaluator::class.java)
    private val nodeGenerator = Mockito.mock(NodeGenerator::class.java)

    private val consFunction = ConsFunction(
        nodeEvaluator,
        nodeGenerator
    )

    @Test
    fun consFunctionDataIsListTest() {
        val child0 = Mockito.mock(NodeV2::class.java)
        val child1 = Mockito.mock(NodeV2::class.java)
        val child2 = Mockito.mock(NodeV2::class.java)
        Mockito.`when`(params.children).thenReturn(listOf(child0, child1, child2))

        val evaluatedAddress = Mockito.mock(NodeV2::class.java)
        Mockito.`when`(
            nodeEvaluator.evaluateV2(
                child1,
                userDefinedFunctions,
                variableNameToValueMap
            )
        ).thenReturn(evaluatedAddress)

        val evaluatedData = Mockito.mock(ExpressionListNode::class.java)
        Mockito.`when`(
            nodeEvaluator.evaluateV2(
                child2,
                userDefinedFunctions,
                variableNameToValueMap
            )
        ).thenReturn(evaluatedData)

        val dataChild = Mockito.mock(NodeV2::class.java)
        Mockito.`when`(
            evaluatedData.children
        ).thenReturn(listOf(dataChild))

        val c = listOf(evaluatedAddress, dataChild)
        val expected = Mockito.mock(ExpressionListNode::class.java)
        Mockito.`when`(
            nodeGenerator.generateExpressionListNode(
                c
            )
        ).thenReturn(expected)

        val actual = consFunction.evaluateLispFunction(
            params,
            userDefinedFunctions,
            variableNameToValueMap
        )
        Assertions.assertEquals(expected, actual)
    }

    @Test
    fun consFunctionDataIsNotListTest() {
        val child0 = Mockito.mock(NodeV2::class.java)
        val child1 = Mockito.mock(NodeV2::class.java)
        val child2 = Mockito.mock(NodeV2::class.java)
        Mockito.`when`(params.children).thenReturn(listOf(child0, child1, child2))

        val evaluatedAddress = Mockito.mock(NodeV2::class.java)
        Mockito.`when`(
            nodeEvaluator.evaluateV2(
                child1,
                userDefinedFunctions,
                variableNameToValueMap
            )
        ).thenReturn(evaluatedAddress)

        val evaluatedData = Mockito.mock(NodeV2::class.java)
        Mockito.`when`(
            nodeEvaluator.evaluateV2(
                child2,
                userDefinedFunctions,
                variableNameToValueMap
            )
        ).thenReturn(evaluatedData)

        val expected = Mockito.mock(ExpressionListNode::class.java)
        Mockito.`when`(
            nodeGenerator.generateExpressionListNode(
                listOf(
                    evaluatedAddress,
                    evaluatedData
                )
            )
        ).thenReturn(expected)

        val actual = consFunction.evaluateLispFunction(
            params,
            userDefinedFunctions,
            variableNameToValueMap
        )
        Assertions.assertEquals(expected, actual)
    }
}