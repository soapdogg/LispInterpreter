package com.soapdogg.lispInterpreter.determiner

import com.soapdogg.lispInterpreter.datamodels.AtomNode
import com.soapdogg.lispInterpreter.datamodels.ExpressionNode
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class ExpressionNodeDeterminerTest {
    private val expressionNodeDeterminer = ExpressionNodeDeterminer()

    @Test
    fun isTrueTest() {
        val expressionNode = Mockito.mock(ExpressionNode::class.java)
        val actual = expressionNodeDeterminer.isExpressionNode(expressionNode)
        Assertions.assertTrue(actual)
    }

    @Test
    fun isFalseTest() {
        val atomNode = Mockito.mock(AtomNode::class.java)
        val actual = expressionNodeDeterminer.isExpressionNode(atomNode)
        Assertions.assertFalse(actual)
    }
}