package com.soapdogg.lispInterpreter.function

import com.soapdogg.lispInterpreter.datamodels.*
import com.soapdogg.lispInterpreter.evaluator.NodeEvaluator
import com.soapdogg.lispInterpreter.functions.AtomFunction
import com.soapdogg.lispInterpreter.generator.NodeGenerator
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class AtomFunctionTest {
    private val params = Mockito.mock(ExpressionListNode::class.java)
    private val userDefinedFunctions: List<UserDefinedFunction> = listOf()
    private val variableNameToValueMap: Map<String, NodeV2> = mapOf()
    private val nodeEvaluator = Mockito.mock(NodeEvaluator::class.java)
    private val nodeGenerator = Mockito.mock(NodeGenerator::class.java)
    private val atomFunction = AtomFunction(
        nodeEvaluator,
        nodeGenerator
    )

    @Test
    fun atomFunctionTest() {
        val child0 = Mockito.mock(NodeV2::class.java)
        val child1 = Mockito.mock(NodeV2::class.java)
        Mockito.`when`(params.children).thenReturn(listOf(child0, child1))

        val evaluatedResult = Mockito.mock(AtomNode::class.java)
        Mockito.`when`(
            nodeEvaluator.evaluateV2(
                child1,
                userDefinedFunctions,
                variableNameToValueMap
            )
        ).thenReturn(evaluatedResult)

        val result = true
        val expected = Mockito.mock(AtomNode::class.java)
        Mockito.`when`(nodeGenerator.generateAtomNode(result)).thenReturn(expected)

        val actual = atomFunction.evaluateLispFunction(
            params,
            userDefinedFunctions,
            variableNameToValueMap
        )
        Assertions.assertEquals(expected, actual)
    }
}