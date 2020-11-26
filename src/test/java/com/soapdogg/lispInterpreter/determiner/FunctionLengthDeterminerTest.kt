package com.soapdogg.lispInterpreter.determiner

import com.soapdogg.lispInterpreter.constants.ReservedValuesConstants
import com.soapdogg.lispInterpreter.datamodels.AtomNode
import com.soapdogg.lispInterpreter.datamodels.ExpressionNode
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class FunctionLengthDeterminerTest {
    private val functionLengthDeterminer = FunctionLengthDeterminer()

    @Test
    fun determineLengthOfNilNodeTest() {
        val nilNode = Mockito.mock(AtomNode::class.java)
        Mockito.`when`(nilNode.value).thenReturn(ReservedValuesConstants.NIL)
        val actual = functionLengthDeterminer.determineFunctionLength(nilNode)
        Assertions.assertEquals(0, actual)
    }

    @Test
    fun determineLengthOfNonNilAtomNodeTest() {
        val atomNode = Mockito.mock(AtomNode::class.java)
        Mockito.`when`(atomNode.value).thenReturn(ReservedValuesConstants.T)
        val actual = functionLengthDeterminer.determineFunctionLength(atomNode)
        Assertions.assertEquals(1, actual)
    }

    @Test
    fun determineExpressionNodeLengthTest() {
        val atomNode = Mockito.mock(AtomNode::class.java)
        Mockito.`when`(atomNode.value).thenReturn(ReservedValuesConstants.T)
        val expressionNode = Mockito.mock(ExpressionNode::class.java)
        Mockito.`when`(expressionNode.data).thenReturn(atomNode)
        val actual = functionLengthDeterminer.determineFunctionLength(expressionNode)
        Assertions.assertEquals(2, actual)
    }
}