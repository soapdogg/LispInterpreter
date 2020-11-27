package com.soapdogg.lispInterpreter.printer

import com.soapdogg.lispInterpreter.comparator.NodeValueComparator
import com.soapdogg.lispInterpreter.constants.ReservedValuesConstants
import com.soapdogg.lispInterpreter.constants.TokenValueConstants
import com.soapdogg.lispInterpreter.datamodels.AtomNode
import com.soapdogg.lispInterpreter.datamodels.ExpressionNode
import com.soapdogg.lispInterpreter.determiner.ExpressionNodeDeterminer
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class ListNotationExpressionNodePrinterTest {
    private val expressionNodeDeterminer = Mockito.mock(ExpressionNodeDeterminer::class.java)
    private val nodeValueComparator = Mockito.mock(NodeValueComparator::class.java)
    private val atomNodePrinter = Mockito.mock(AtomNodePrinter::class.java)
    private val listNotationExpressionNodePrinter = ListNotationExpressionNodePrinter(
        expressionNodeDeterminer,
        nodeValueComparator,
        atomNodePrinter
    )

    @Test
    fun printExpressionNodeInListNotationTest() {
        val rootNode = Mockito.mock(ExpressionNode::class.java)
        val rootAddress = Mockito.mock(ExpressionNode::class.java)
        Mockito.`when`(rootNode.address).thenReturn(rootAddress)
        Mockito.`when`(expressionNodeDeterminer.isExpressionNode(rootAddress)).thenReturn(true)

        val addressAddress = Mockito.mock(AtomNode::class.java)
        Mockito.`when`(rootAddress.address).thenReturn(addressAddress)
        Mockito.`when`(expressionNodeDeterminer.isExpressionNode(addressAddress)).thenReturn(false)

        val addressAddressValue = "addressAddress"
        Mockito.`when`(atomNodePrinter.printAtomNode(addressAddress)).thenReturn(addressAddressValue)

        val addressData = Mockito.mock(AtomNode::class.java)
        Mockito.`when`(rootAddress.data).thenReturn(addressData)
        Mockito.`when`(expressionNodeDeterminer.isExpressionNode(addressData)).thenReturn(false)

        val addressDataValue = "addressData"
        Mockito.`when`(atomNodePrinter.printAtomNode(addressData)).thenReturn(addressDataValue)

        val rootData = Mockito.mock(ExpressionNode::class.java)
        Mockito.`when`(rootNode.data).thenReturn(rootData)
        Mockito.`when`(expressionNodeDeterminer.isExpressionNode(rootData)).thenReturn(true)

        val dataAddress = Mockito.mock(AtomNode::class.java)
        Mockito.`when`(rootData.address).thenReturn(dataAddress)
        Mockito.`when`(expressionNodeDeterminer.isExpressionNode(dataAddress)).thenReturn(false)

        val dataAddressValue = "dataAddress"
        Mockito.`when`(atomNodePrinter.printAtomNode(dataAddress)).thenReturn(dataAddressValue)

        val dataData = Mockito.mock(AtomNode::class.java)
        Mockito.`when`(rootData.data).thenReturn(dataData)
        Mockito.`when`(expressionNodeDeterminer.isExpressionNode(dataData)).thenReturn(false)

        val dataDataValue = ReservedValuesConstants.NIL
        Mockito.`when`(nodeValueComparator.equalsNil(dataDataValue)).thenReturn(true)
        Mockito.`when`(atomNodePrinter.printAtomNode(dataData)).thenReturn(dataDataValue)

        val expected = (TokenValueConstants.OPEN_PARENTHESES
            + addressAddressValue
            + ReservedValuesConstants.LIST_DELIMITER
            + addressDataValue
            + TokenValueConstants.CLOSE_PARENTHESES
            + ReservedValuesConstants.SPACE
            + dataAddressValue
            + TokenValueConstants.CLOSE_PARENTHESES)
        val actual = listNotationExpressionNodePrinter.printExpressionNodeInListNotation(rootNode)
        Assertions.assertEquals(expected, actual)
    }
}