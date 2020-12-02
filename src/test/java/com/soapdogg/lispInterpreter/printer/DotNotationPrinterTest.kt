package com.soapdogg.lispInterpreter.printer

import com.soapdogg.lispInterpreter.constants.ReservedValuesConstants
import com.soapdogg.lispInterpreter.datamodels.AtomNode
import com.soapdogg.lispInterpreter.datamodels.ExpressionNode
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class DotNotationPrinterTest {
    private val atomNodePrinter = Mockito.mock(AtomNodePrinter::class.java)
    private val dotNotationExpressionNodePrinter = Mockito.mock(DotNotationExpressionNodePrinter::class.java)
    private val dotNotationPrinter = DotNotationPrinter(
        atomNodePrinter,
        dotNotationExpressionNodePrinter
    )

    @Test
    fun printEmptyListOfNodesTest() {
        val expected = """${ReservedValuesConstants.NIL}${ReservedValuesConstants.NEW_LINE}"""
        val actual = dotNotationPrinter.printInDotNotation(emptyList())
        Assertions.assertEquals(expected, actual)
    }

    @Test
    fun printNonEmptyListOfNodesTest() {
        val atomNode = Mockito.mock(AtomNode::class.java)
        val value = "value"
        Mockito.`when`(atomNodePrinter.printAtomNode(atomNode)).thenReturn(value)
        val nodes= listOf(atomNode)
        val expected = """$value${ReservedValuesConstants.NEW_LINE}"""
        val actual = dotNotationPrinter.printInDotNotation(nodes)
        Assertions.assertEquals(expected, actual)
    }

    @Test
    fun printAtomNodeTest() {
        val atomNode = Mockito.mock(AtomNode::class.java)
        val value = "value"
        Mockito.`when`(atomNodePrinter.printAtomNode(atomNode)).thenReturn(value)
        val actual = dotNotationPrinter.printInDotNotation(atomNode)
        Assertions.assertEquals(value, actual)
    }

    @Test
    fun printExpressionNodeListTest() {
        val expressionNode = Mockito.mock(ExpressionNode::class.java)
        val expected = "value"
        Mockito.`when`(dotNotationExpressionNodePrinter.printExpressionNodeInDotNotation(expressionNode)).thenReturn(expected)
        val actual = dotNotationPrinter.printInDotNotation(expressionNode)
        Assertions.assertEquals(expected, actual)
    }
}