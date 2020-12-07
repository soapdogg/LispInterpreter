package com.soapdogg.lispInterpreter.printer

import com.soapdogg.lispInterpreter.constants.ReservedValuesConstants
import com.soapdogg.lispInterpreter.constants.TokenValueConstants
import com.soapdogg.lispInterpreter.datamodels.AtomNode
import com.soapdogg.lispInterpreter.datamodels.ExpressionListNode
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class ListNotationPrinterTest {

    private val atomNodePrinter = Mockito.mock(AtomNodePrinter::class.java)
    private val listNotationPrinter = ListNotationPrinter(
        atomNodePrinter
    )

    @Test
    fun printNonEmptyListOfNodesTest() {
        val atomNode = Mockito.mock(AtomNode::class.java)
        val value = "value"
        Mockito.`when`(atomNodePrinter.printAtomNode(atomNode)).thenReturn(value)
        val nodes = listOf(atomNode)
        val expected = """$value${ReservedValuesConstants.NEW_LINE}"""
        val actual = listNotationPrinter.printInListNotation(nodes)
        Assertions.assertEquals(expected, actual)
    }

    @Test
    fun printExpressionListOfSizeTwoTest() {
        val node = Mockito.mock(ExpressionListNode::class.java)

        val c0 = Mockito.mock(AtomNode::class.java)
        val c0Value = "c0"
        Mockito.`when`(atomNodePrinter.printAtomNode(c0)).thenReturn(c0Value)

        val c1 = Mockito.mock(AtomNode::class.java)
        val c1Value = "c1"
        Mockito.`when`(atomNodePrinter.printAtomNode(c1)).thenReturn(c1Value)

        Mockito.`when`(node.children).thenReturn(listOf(c0, c1))

        val expected = (
            TokenValueConstants.OPEN_PARENTHESES.toString()
            + c0Value
            + ReservedValuesConstants.LIST_DELIMITER
            + c1Value
            + TokenValueConstants.CLOSE_PARENTHESES.toString()
        )

        val actual = listNotationPrinter.printInListNotation(node)

        Assertions.assertEquals(expected, actual)
    }

    @Test
    fun printExpressionListOfSizeTwoLastElementIsNilTest() {
        val node = Mockito.mock(ExpressionListNode::class.java)

        val c0 = Mockito.mock(AtomNode::class.java)
        val c0Value = "c0"
        Mockito.`when`(atomNodePrinter.printAtomNode(c0)).thenReturn(c0Value)

        val c1 = Mockito.mock(AtomNode::class.java)
        val c1Value = ReservedValuesConstants.NIL
        Mockito.`when`(c1.value).thenReturn(c1Value)

        Mockito.`when`(node.children).thenReturn(listOf(c0, c1))

        val expected = (
            TokenValueConstants.OPEN_PARENTHESES.toString()
                + c0Value
                + TokenValueConstants.CLOSE_PARENTHESES.toString()
            )

        val actual = listNotationPrinter.printInListNotation(node)

        Assertions.assertEquals(expected, actual)
    }
}