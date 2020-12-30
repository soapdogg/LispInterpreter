package com.soapdogg.lispInterpreter.evaluator

import com.soapdogg.lispInterpreter.asserter.AtomRootNodeAsserter
import com.soapdogg.lispInterpreter.datamodels.*
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class ProgramEvaluatorTest {
    private val userDefinedFunctions: Map<String, UserDefinedFunction> = emptyMap()

    private val atomRootNodeAsserter = Mockito.mock(AtomRootNodeAsserter::class.java)
    private val nodeEvaluatorIterative = Mockito.mock(RootNodeEvaluator::class.java)

    private val programEvaluator = ProgramEvaluator(
        atomRootNodeAsserter,
        nodeEvaluatorIterative
    )

    @Test
    fun rootNodeIsAnAtomNodeTest() {
        val atomNode = Mockito.mock(AtomNode::class.java)
        val rootNodes = listOf(atomNode)

        val actual = programEvaluator.evaluatePostOrder(
            rootNodes,
            userDefinedFunctions
        )
        Assertions.assertEquals(1, actual.size)
        Assertions.assertEquals(atomNode, actual[0])
        Mockito.verify(atomRootNodeAsserter).assertAtomRootNode(atomNode)
    }
/*
    @Test
    fun rootNodeIsExpressionNodeTest() {
        val expressionNode = Mockito.mock(ExpressionListNode::class.java)
        val rootNodes = listOf(expressionNode)
        val evaluatedNode = Mockito.mock(NodeV2::class.java)
        Mockito.`when`(
            nodeEvaluatorIterative.evaluate(
                expressionNode,
                userDefinedFunctions,
                MyStack(),
                Stack()
            )
        ).thenReturn(evaluatedNode)
        val actual = programEvaluator.evaluatePostOrder(
            rootNodes,
            userDefinedFunctions
        )
        Assertions.assertEquals(1, actual.size)
        Assertions.assertEquals(evaluatedNode, actual[0])
        Mockito.verifyNoInteractions(atomRootNodeAsserter)
    }*/
}