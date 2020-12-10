package com.soapdogg.lispInterpreter.evaluator

import com.soapdogg.lispInterpreter.asserter.AtomRootNodeAsserter
import com.soapdogg.lispInterpreter.datamodels.*
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class ProgramEvaluatorTest {
    private val userDefinedFunctions: Map<String, UserDefinedFunction> = emptyMap()
    private val variableNameToValueMap: Map<String, NodeV2> = emptyMap()

    private val atomRootNodeAsserter = Mockito.mock(AtomRootNodeAsserter::class.java)
    private val nodeEvaluator = Mockito.mock(NodeEvaluator::class.java)
    private val stackEvaluator = Mockito.mock(StackEvaluator::class.java)

    private val programEvaluator = ProgramEvaluator(
        atomRootNodeAsserter,
        nodeEvaluator,
        stackEvaluator
    )

    @Test
    fun rootNodeIsAnAtomNodeTest() {
        val atomNode = Mockito.mock(AtomNode::class.java)
        val rootNodes = listOf(atomNode)
        val evaluatedNode = Mockito.mock(NodeV2::class.java)
        Mockito.`when`(
            nodeEvaluator.evaluateV2(
                listOf(atomNode),
                userDefinedFunctions,
                variableNameToValueMap
            )
        ).thenReturn(listOf(evaluatedNode))

        val actual = programEvaluator.evaluate(
            rootNodes,
            userDefinedFunctions,
            variableNameToValueMap
        )
        Assertions.assertEquals(1, actual.size)
        Assertions.assertEquals(evaluatedNode, actual[0])
        Mockito.verify(atomRootNodeAsserter).assertAtomRootNode(atomNode)
    }

    @Test
    fun rootNodeIsExpressionNodeTest() {
        val expressionNode = Mockito.mock(ExpressionListNode::class.java)
        val rootNodes = listOf(expressionNode)
        val evaluatedNode = Mockito.mock(NodeV2::class.java)
        Mockito.`when`(
            nodeEvaluator.evaluateV2(
                listOf(expressionNode),
                userDefinedFunctions,
                variableNameToValueMap
            )
        ).thenReturn(listOf(evaluatedNode))
        val actual = programEvaluator.evaluate(
            rootNodes,
            userDefinedFunctions,
            variableNameToValueMap
        )
        Assertions.assertEquals(1, actual.size)
        Assertions.assertEquals(evaluatedNode, actual[0])
        Mockito.verifyNoInteractions(atomRootNodeAsserter)
    }
}