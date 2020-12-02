package com.soapdogg.lispInterpreter.evaluator

import com.soapdogg.lispInterpreter.asserter.AtomRootNodeAsserter
import com.soapdogg.lispInterpreter.datamodels.AtomNode
import com.soapdogg.lispInterpreter.datamodels.ExpressionNode
import com.soapdogg.lispInterpreter.datamodels.Node
import com.soapdogg.lispInterpreter.datamodels.UserDefinedFunction
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class ProgramEvaluatorTest {
    private val userDefinedFunctions: List<UserDefinedFunction> = emptyList()
    private val variableNameToValueMap: Map<String, Node> = emptyMap()

    private val atomRootNodeAsserter = Mockito.mock(AtomRootNodeAsserter::class.java)
    private val nodeEvaluator = Mockito.mock(NodeEvaluator::class.java)

    private val programEvaluator = ProgramEvaluator(
        atomRootNodeAsserter,
        nodeEvaluator
    )

    @Test
    fun rootNodeIsAnAtomNodeTest() {
        val atomNode = Mockito.mock(AtomNode::class.java)
        val rootNodes: List<Node> = listOf(atomNode)
        val evaluatedNode = Mockito.mock(Node::class.java)
        Mockito.`when`(
            nodeEvaluator.evaluate(
                atomNode,
                userDefinedFunctions,
                variableNameToValueMap,
                false
            )
        ).thenReturn(evaluatedNode)

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
        val expressionNode = Mockito.mock(ExpressionNode::class.java)
        val rootNodes: List<Node> = listOf(expressionNode)
        val evaluatedNode = Mockito.mock(Node::class.java)
        Mockito.`when`(
            nodeEvaluator.evaluate(
                expressionNode,
                userDefinedFunctions,
                variableNameToValueMap,
                false
            )
        ).thenReturn(evaluatedNode)
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