package com.soapdogg.lispInterpreter.determiner

import com.soapdogg.lispInterpreter.constants.ReservedValuesConstants
import com.soapdogg.lispInterpreter.datamodels.*
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class FunctionLengthDeterminerTest {
    private val functionLengthDeterminer = FunctionLengthDeterminer()

    @Test
    fun determineLengthOfNilNodeTest() {
        val nilNode = Mockito.mock(AtomNode::class.java)
        Mockito.`when`(nilNode.value).thenReturn(ReservedValuesConstants.NIL)
        val actual = functionLengthDeterminer.determineFunctionLength(nilNode as NodeV2)
        Assertions.assertEquals(0, actual)
    }

    @Test
    fun determineLengthOfNonNilAtomNodeTest() {
        val atomNode = Mockito.mock(AtomNode::class.java)
        Mockito.`when`(atomNode.value).thenReturn(ReservedValuesConstants.T)
        val actual = functionLengthDeterminer.determineFunctionLength(atomNode as NodeV2)
        Assertions.assertEquals(1, actual)
    }

    @Test
    fun determineExpressionNodeLengthTest() {
        val atomNode = Mockito.mock(AtomNode::class.java)
        Mockito.`when`(atomNode.value).thenReturn(ReservedValuesConstants.T)
        val expressionNode = Mockito.mock(ExpressionListNode::class.java)
        Mockito.`when`(expressionNode.children).thenReturn(listOf(atomNode))
        val actual = functionLengthDeterminer.determineFunctionLength(expressionNode)
        Assertions.assertEquals(1, actual)
    }

    @Test
    fun determineExpressionNodeWithLastElementNilTest() {
        val atomNode = Mockito.mock(AtomNode::class.java)
        Mockito.`when`(atomNode.value).thenReturn(ReservedValuesConstants.NIL)
        val expressionNode = Mockito.mock(ExpressionListNode::class.java)
        Mockito.`when`(expressionNode.children).thenReturn(listOf(atomNode))
        val actual = functionLengthDeterminer.determineFunctionLength(expressionNode)
        Assertions.assertEquals(0, actual)
    }
}