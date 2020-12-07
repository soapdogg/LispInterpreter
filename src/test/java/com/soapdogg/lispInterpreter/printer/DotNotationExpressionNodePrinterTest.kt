package com.soapdogg.lispInterpreter.printer

import com.soapdogg.lispInterpreter.constants.ReservedValuesConstants
import com.soapdogg.lispInterpreter.constants.TokenValueConstants
import com.soapdogg.lispInterpreter.datamodels.AtomNode
import com.soapdogg.lispInterpreter.datamodels.ExpressionListNode
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito


class DotNotationExpressionNodePrinterTest {
    private val atomNodePrinter = Mockito.mock(AtomNodePrinter::class.java)
    private val dotNotationExpressionNodePrinter = DotNotationExpressionNodePrinter(
        atomNodePrinter
    )

    @Test
    fun printExpressionNodeOneChildIsAtomTest() {
        val node = Mockito.mock(ExpressionListNode::class.java)
        val addressChild = Mockito.mock(AtomNode::class.java)
        Mockito.`when`(node.children).thenReturn(listOf(addressChild))

        val expected = "expected"
        Mockito.`when`(atomNodePrinter.printAtomNode(addressChild)).thenReturn(expected)

        val actual = dotNotationExpressionNodePrinter.printExpressionNodeInDotNotation(
            node
        )

        Assertions.assertEquals(expected, actual)
    }

    @Test
    fun printExpressionNodeTwoChildrenBothAtomsTest() {
        val node = Mockito.mock(ExpressionListNode::class.java)

        val child0 = Mockito.mock(AtomNode::class.java)
        val child1 = Mockito.mock(AtomNode::class.java)
        Mockito.`when`(node.children).thenReturn(listOf(child0, child1))

        val c0 = "c0"
        Mockito.`when`(atomNodePrinter.printAtomNode(child0)).thenReturn(c0)

        val c1 = "c1"
        Mockito.`when`(atomNodePrinter.printAtomNode(child1)).thenReturn(c1)

        val expected = (TokenValueConstants.OPEN_PARENTHESES
            + c0
            + ReservedValuesConstants.LIST_DELIMITER
            + c1
            + TokenValueConstants.CLOSE_PARENTHESES)

        val actual = dotNotationExpressionNodePrinter.printExpressionNodeInDotNotation(
            node
        )

        Assertions.assertEquals(expected, actual)
    }
}