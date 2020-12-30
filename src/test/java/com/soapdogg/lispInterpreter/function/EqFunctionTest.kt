package com.soapdogg.lispInterpreter.function

import com.soapdogg.lispInterpreter.datamodels.AtomNode
import com.soapdogg.lispInterpreter.datamodels.ExpressionListNode
import com.soapdogg.lispInterpreter.datamodels.MyStack
import com.soapdogg.lispInterpreter.datamodels.NodeV2
import com.soapdogg.lispInterpreter.generator.NodeGenerator
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import java.util.*

class EqFunctionTest {

    private val nodeGenerator = Mockito.mock(NodeGenerator::class.java)

    private val params = MyStack<NodeV2>()

    private val eqFunction = EqFunction(
        nodeGenerator
    )

    @Test
    fun atomNodeTest() {
        val first = Mockito.mock(AtomNode::class.java)
        val second = Mockito.mock(AtomNode::class.java)

        params.push(second)
        params.push(first)

        val resultingNode = Mockito.mock(AtomNode::class.java)
        Mockito.`when`(
            nodeGenerator.generateAtomNode(false)
        ).thenReturn(resultingNode)

        val actual = eqFunction.evaluate(
            params
        )

        Assertions.assertEquals(resultingNode, actual)
    }

    @Test
    fun expressionNodeTest() {
        val first = Mockito.mock(ExpressionListNode::class.java)
        val second = Mockito.mock(ExpressionListNode::class.java)

        params.push(second)
        params.push(first)

        val resultingNode = Mockito.mock(AtomNode::class.java)
        Mockito.`when`(
            nodeGenerator.generateAtomNode(false)
        ).thenReturn(resultingNode)

        val actual = eqFunction.evaluate(
            params
        )

        Assertions.assertEquals(resultingNode, actual)
    }
}