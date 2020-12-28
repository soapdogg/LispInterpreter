package com.soapdogg.lispInterpreter.function

import com.soapdogg.lispInterpreter.datamodels.AtomNode
import com.soapdogg.lispInterpreter.datamodels.ExpressionListNode
import com.soapdogg.lispInterpreter.datamodels.NodeV2
import com.soapdogg.lispInterpreter.generator.NodeGenerator
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import java.util.*

class ConsFunctionTest {

    private val nodeGenerator = Mockito.mock(NodeGenerator::class.java)

    private val params = Stack<NodeV2>()
    private val variableMap = mapOf<String, NodeV2>()

    private val consFunction = ConsFunction(
        nodeGenerator
    )

    @Test
    fun atomNodeTest() {
        val first = Mockito.mock(AtomNode::class.java)
        val second = Mockito.mock(AtomNode::class.java)

        params.push(second)
        params.push(first)

        val resultingNode = Mockito.mock(ExpressionListNode::class.java)
        Mockito.`when`(
            nodeGenerator.generateExpressionListNode(
                listOf(first, second)
            )
        ).thenReturn(resultingNode)

        val actual = consFunction.evaluate(
            params
        )

        Assertions.assertEquals(resultingNode, actual)
    }

    @Test
    fun expressionListNodeTest() {
        val first = Mockito.mock(ExpressionListNode::class.java)
        val second = Mockito.mock(ExpressionListNode::class.java)

        params.push(second)
        params.push(first)

        val dataChild0 = Mockito.mock(NodeV2::class.java)
        val dataChild1 = Mockito.mock(NodeV2::class.java)
        Mockito.`when`(
            second.children
        ).thenReturn(listOf(dataChild0, dataChild1))

        val resultingNode = Mockito.mock(ExpressionListNode::class.java)
        Mockito.`when`(
            nodeGenerator.generateExpressionListNode(
                listOf(first, dataChild0, dataChild1)
            )
        ).thenReturn(resultingNode)

        val actual = consFunction.evaluate(
            params
        )

        Assertions.assertEquals(resultingNode, actual)
    }
}