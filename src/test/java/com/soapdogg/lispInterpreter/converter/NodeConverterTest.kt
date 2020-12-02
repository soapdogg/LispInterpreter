package com.soapdogg.lispInterpreter.converter

import com.soapdogg.lispInterpreter.datamodels.AtomNode
import com.soapdogg.lispInterpreter.datamodels.ExpressionListNode
import com.soapdogg.lispInterpreter.datamodels.ExpressionNode
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class NodeConverterTest {
    private val nodeConverter = NodeConverter()

    @Test
    fun convertAtomNodeV2ToNodeTest() {
        val atomNode = Mockito.mock(AtomNode::class.java)

        val expressionListNode = Mockito.mock(ExpressionListNode::class.java)
        Mockito.`when`(expressionListNode.children).thenReturn(
            listOf(atomNode)
        )

        val actual = nodeConverter.convertNodeV2ToNode(expressionListNode)

        Assertions.assertEquals(atomNode, actual)
    }

    @Test
    fun convertExpressionListNodeV2ToNodeTest() {
        val address = Mockito.mock(AtomNode::class.java)
        val data = Mockito.mock(AtomNode::class.java)

        val expressionListNode = Mockito.mock(ExpressionListNode::class.java)
        Mockito.`when`(expressionListNode.children).thenReturn(
            listOf(address, data)
        )
        
        val actual = nodeConverter.convertNodeV2ToNode(expressionListNode)

        Assertions.assertTrue(actual is ExpressionNode)
        Assertions.assertEquals(address, (actual as ExpressionNode).address)
        Assertions.assertEquals(data, actual.data)
    }

    @Test
    fun convertAtomNodeToNodeV2Test() {
        val atomNode = Mockito.mock(AtomNode::class.java)
        val actual = nodeConverter.convertNodeToNodeV2(atomNode)

        Assertions.assertEquals(atomNode, actual)
    }

    @Test
    fun convertExpressionNodeToNodeV2Test() {
        val expressionNode = Mockito.mock(ExpressionNode::class.java)

        Assertions.assertThrows(
            NotImplementedError::class.java,
        ) {nodeConverter.convertNodeToNodeV2(expressionNode)}
    }
}