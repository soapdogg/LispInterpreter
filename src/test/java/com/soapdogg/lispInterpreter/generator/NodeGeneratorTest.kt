package com.soapdogg.lispInterpreter.generator

import com.soapdogg.lispInterpreter.constants.ReservedValuesConstants
import com.soapdogg.lispInterpreter.datamodels.Node
import com.soapdogg.lispInterpreter.datamodels.NodeV2
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class NodeGeneratorTest {
    private val nodeGenerator: NodeGenerator = NodeGenerator()

    @Test
    fun generateTAtomNodeTest() {
        val (value) = nodeGenerator.generateAtomNode(true)
        Assertions.assertEquals(ReservedValuesConstants.T, value)
    }

    @Test
    fun generateNilAtomNodeTest() {
        val (value) = nodeGenerator.generateAtomNode(false)
        Assertions.assertEquals(ReservedValuesConstants.NIL, value)
    }

    @Test
    fun generateAtomNodeFromIntTest() {
        val value = 10
        val (value1) = nodeGenerator.generateAtomNode(value)
        Assertions.assertEquals(value.toString(), value1)
    }

    @Test
    fun generateAtomNodeFromStringTest() {
        val value = "value"
        val (value1) = nodeGenerator.generateAtomNode(value)
        Assertions.assertEquals(value, value1)
    }

    @Test
    fun generateNonEmptyExpressionNodeTest() {
        val address = Mockito.mock(Node::class.java)
        val data = Mockito.mock(Node::class.java)
        val (address1, data1) = nodeGenerator.generateExpressionNode(
                address,
                data
        )
        Assertions.assertEquals(address, address1)
        Assertions.assertEquals(data, data1)
    }

    @Test
    fun generateParserResultForExpressionListNodeTest() {
        val children = emptyList<NodeV2>()

        val actual = nodeGenerator.generateExpressionListNode(children)

        Assertions.assertEquals(children, actual.children)
    }
}