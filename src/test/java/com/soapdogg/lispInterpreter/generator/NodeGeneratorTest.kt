package com.soapdogg.lispInterpreter.generator

import com.soapdogg.lispInterpreter.constants.ReservedValuesConstants
import com.soapdogg.lispInterpreter.datamodels.NodeV2
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

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
    fun generateParserResultForExpressionListNodeTest() {
        val children = emptyList<NodeV2>()

        val actual = nodeGenerator.generateExpressionListNode(children)

        Assertions.assertEquals(children, actual.children)
    }
}