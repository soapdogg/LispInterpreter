package com.soapdogg.lispInterpreter.printer

import com.soapdogg.lispInterpreter.constants.ReservedValuesConstants
import com.soapdogg.lispInterpreter.constants.TokenValueConstants
import com.soapdogg.lispInterpreter.datamodels.AtomNode
import com.soapdogg.lispInterpreter.datamodels.ExpressionNode
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito


class DotNotationExpressionNodePrinterTest {
    private val atomNodePrinter = Mockito.mock(AtomNodePrinter::class.java)
    private val dotNotationExpressionNodePrinter = DotNotationExpressionNodePrinter(
        atomNodePrinter
    )

    @Test
    fun printExpressionNodeListTest() {
        val address = Mockito.mock(ExpressionNode::class.java)

        val addressAtom = Mockito.mock(AtomNode::class.java)
        Mockito.`when`(address.address).thenReturn(addressAtom)

        val addressStr = "address"
        Mockito.`when`(atomNodePrinter.printAtomNode(addressAtom)).thenReturn(addressStr)

        val dataAtom = Mockito.mock(AtomNode::class.java)
        Mockito.`when`(address.data).thenReturn(dataAtom)

        val dataStr = "data"
        Mockito.`when`(atomNodePrinter.printAtomNode(dataAtom)).thenReturn(dataStr)

        val data = Mockito.mock(ExpressionNode::class.java)

        val addressAtom2 = Mockito.mock(AtomNode::class.java)
        Mockito.`when`(data.address).thenReturn(addressAtom2)

        val addressStr2 = "address2"
        Mockito.`when`(atomNodePrinter.printAtomNode(addressAtom2)).thenReturn(addressStr2)

        val dataAtom2 = Mockito.mock(AtomNode::class.java)
        Mockito.`when`(data.data).thenReturn(dataAtom2)

        val dataStr2 = "data"
        Mockito.`when`(atomNodePrinter.printAtomNode(dataAtom2)).thenReturn(dataStr2)

        val expressionNode = Mockito.mock(ExpressionNode::class.java)
        Mockito.`when`(expressionNode.address).thenReturn(address)
        Mockito.`when`(expressionNode.data).thenReturn(data)

        val expected = (TokenValueConstants.OPEN_PARENTHESES.toString()
            + TokenValueConstants.OPEN_PARENTHESES
            + addressStr
            + ReservedValuesConstants.LIST_DELIMITER
            + dataStr
            + TokenValueConstants.CLOSE_PARENTHESES
            + ReservedValuesConstants.LIST_DELIMITER
            + TokenValueConstants.OPEN_PARENTHESES
            + addressStr2
            + ReservedValuesConstants.LIST_DELIMITER
            + dataStr2
            + TokenValueConstants.CLOSE_PARENTHESES
            + TokenValueConstants.CLOSE_PARENTHESES)
        val actual = dotNotationExpressionNodePrinter.printExpressionNodeInDotNotation(expressionNode)
        Assertions.assertEquals(expected, actual)
    }
}