package com.soapdogg.lispInterpreter.converter

import com.soapdogg.lispInterpreter.datamodels.AtomNode
import com.soapdogg.lispInterpreter.datamodels.ExpressionNode
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class NodeConverterTest {
    private val nodeConverter = NodeConverter()

    @Test
    fun convertAtomNodeV2ToNodeTest() {
        val atomNode = Mockito.mock(AtomNode::class.java)
        val actual = nodeConverter.convertNodeV2ToNode(atomNode)

        Assertions.assertEquals(atomNode, actual)
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