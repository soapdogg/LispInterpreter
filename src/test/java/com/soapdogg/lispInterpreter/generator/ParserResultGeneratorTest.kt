package com.soapdogg.lispInterpreter.generator

import com.soapdogg.lispInterpreter.constants.ReservedValuesConstants
import com.soapdogg.lispInterpreter.datamodels.AtomNode
import com.soapdogg.lispInterpreter.datamodels.ExpressionListNode
import com.soapdogg.lispInterpreter.datamodels.NodeV2
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class ParserResultGeneratorTest {

    private val nodeGenerator = Mockito.mock(NodeGenerator::class.java)
    private val parserResultGenerator = ParserResultGenerator(nodeGenerator)


    @Test
    fun generateParserResultForAtomNodeTest() {
        val value = "value"
        val resultingNode = Mockito.mock(AtomNode::class.java)
        Mockito.`when`(nodeGenerator.generateAtomNode(value)).thenReturn(resultingNode)

        val actual = parserResultGenerator.generateParserResultForAtomNode(value)

        Assertions.assertEquals(resultingNode, actual.resultingNode)
        Assertions.assertEquals(1, actual.nextIndex)
    }

    @Test
    fun generateParserResultForExpressionListNodeTest() {
        val children = emptyList<NodeV2>()
        val nextIndex = 2

        val resultingNode = Mockito.mock(ExpressionListNode::class.java)
        Mockito.`when`(nodeGenerator.generateExpressionListNode(children)).thenReturn(resultingNode)

        val actual = parserResultGenerator.generateParserResultForExpressionListNode(children, nextIndex)

        Assertions.assertEquals(resultingNode, actual.resultingNode)
        Assertions.assertEquals(nextIndex, actual.nextIndex)
    }
}