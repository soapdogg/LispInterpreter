package com.soapdogg.lispInterpreter.printer

import com.soapdogg.lispInterpreter.constants.ReservedValuesConstants
import com.soapdogg.lispInterpreter.constants.TokenValueConstants
import com.soapdogg.lispInterpreter.datamodels.AtomNode
import com.soapdogg.lispInterpreter.datamodels.ExpressionNode
import com.soapdogg.lispInterpreter.determiner.ExpressionNodeDeterminer
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito


class DotNotationExpressionNodePrinterTest {
    private val expressionNodeDeterminer = Mockito.mock(ExpressionNodeDeterminer::class.java)
    private val atomNodePrinter = Mockito.mock(AtomNodePrinter::class.java)
    private val dotNotationExpressionNodePrinter = DotNotationExpressionNodePrinter(
        expressionNodeDeterminer,
        atomNodePrinter
    )

    @Test
    fun printExpressionNodeListTest() {
        val address = Mockito.mock(ExpressionNode::class.java)
        Mockito.`when`(expressionNodeDeterminer.isExpressionNode(address)).thenReturn(true)

        val addressAtom = Mockito.mock(AtomNode::class.java)
        Mockito.`when`(address.address).thenReturn(addressAtom)
        Mockito.`when`(expressionNodeDeterminer.isExpressionNode(addressAtom)).thenReturn(false)

        val addressStr = "address"
        Mockito.`when`(atomNodePrinter.printAtomNode(addressAtom)).thenReturn(addressStr)

        val dataAtom = Mockito.mock(AtomNode::class.java)
        Mockito.`when`(address.data).thenReturn(dataAtom)
        Mockito.`when`(expressionNodeDeterminer.isExpressionNode(dataAtom)).thenReturn(false)

        val dataStr = "data"
        Mockito.`when`(atomNodePrinter.printAtomNode(dataAtom)).thenReturn(dataStr)

        val data = Mockito.mock(ExpressionNode::class.java)
        Mockito.`when`(expressionNodeDeterminer.isExpressionNode(data)).thenReturn(true)

        val addressAtom2 = Mockito.mock(AtomNode::class.java)
        Mockito.`when`(data.address).thenReturn(addressAtom2)
        Mockito.`when`(expressionNodeDeterminer.isExpressionNode(addressAtom2)).thenReturn(false)

        val addressStr2 = "address2"
        Mockito.`when`(atomNodePrinter.printAtomNode(addressAtom2)).thenReturn(addressStr2)

        val dataAtom2 = Mockito.mock(AtomNode::class.java)
        Mockito.`when`(data.data).thenReturn(dataAtom2)
        Mockito.`when`(expressionNodeDeterminer.isExpressionNode(dataAtom2)).thenReturn(false)

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