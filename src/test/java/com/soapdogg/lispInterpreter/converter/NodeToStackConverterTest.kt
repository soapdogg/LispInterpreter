package com.soapdogg.lispInterpreter.converter

import com.soapdogg.lispInterpreter.datamodels.AtomNode
import com.soapdogg.lispInterpreter.datamodels.ExpressionListNode
import com.soapdogg.lispInterpreter.determiner.FunctionLengthDeterminer
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class NodeToStackConverterTest {

    private val functionLengthDeterminer = Mockito.mock(FunctionLengthDeterminer::class.java)

    private val nodeToStackConverter = NodeToStackConverter(functionLengthDeterminer)

    @Test
    fun convertAtomNodeTest() {
        val node = Mockito.mock(AtomNode::class.java)

        val actual = nodeToStackConverter.convertToStack(node)

        Assertions.assertEquals(1, actual.size)
        Assertions.assertEquals(node, actual[0])
    }

    @Test
    fun convertExpressionListNodeTest() {
        val node = Mockito.mock(ExpressionListNode::class.java)

        val length = 1
        Mockito.`when`(functionLengthDeterminer.determineFunctionLength(node)).thenReturn(length)

        val child = Mockito.mock(AtomNode::class.java)
        Mockito.`when`(node.children).thenReturn(listOf(child))

        val actual = nodeToStackConverter.convertToStack(node)

        Assertions.assertEquals(1, actual.size)
        Assertions.assertEquals(child, actual[0])
    }
}