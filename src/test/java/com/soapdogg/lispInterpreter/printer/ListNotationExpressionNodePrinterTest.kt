package com.soapdogg.lispInterpreter.printer

import com.soapdogg.lispInterpreter.comparator.NodeValueComparator
import com.soapdogg.lispInterpreter.constants.ReservedValuesConstants
import com.soapdogg.lispInterpreter.constants.TokenValueConstants
import com.soapdogg.lispInterpreter.datamodels.AtomNode
import com.soapdogg.lispInterpreter.datamodels.ExpressionNode
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class ListNotationExpressionNodePrinterTest {
    private val nodeValueComparator = Mockito.mock(NodeValueComparator::class.java)
    private val atomNodePrinter = Mockito.mock(AtomNodePrinter::class.java)
    private val listNotationExpressionNodePrinter = ListNotationExpressionNodePrinter(
        nodeValueComparator,
        atomNodePrinter
    )

    @Test
    fun printExpressionNodeInListNotationTest() {
        val rootNode = Mockito.mock(ExpressionNode::class.java)
        val rootAddress = Mockito.mock(ExpressionNode::class.java)
        Mockito.`when`(rootNode.address).thenReturn(rootAddress)

        val addressAddress = Mockito.mock(AtomNode::class.java)
        Mockito.`when`(rootAddress.address).thenReturn(addressAddress)

        val addressAddressValue = "addressAddress"
        Mockito.`when`(atomNodePrinter.printAtomNode(addressAddress)).thenReturn(addressAddressValue)

        val addressData = Mockito.mock(AtomNode::class.java)
        Mockito.`when`(rootAddress.data).thenReturn(addressData)

        val addressDataValue = "addressData"
        Mockito.`when`(atomNodePrinter.printAtomNode(addressData)).thenReturn(addressDataValue)

        val rootData = Mockito.mock(ExpressionNode::class.java)
        Mockito.`when`(rootNode.data).thenReturn(rootData)

        val dataAddress = Mockito.mock(AtomNode::class.java)
        Mockito.`when`(rootData.address).thenReturn(dataAddress)

        val dataAddressValue = "dataAddress"
        Mockito.`when`(atomNodePrinter.printAtomNode(dataAddress)).thenReturn(dataAddressValue)

        val dataData = Mockito.mock(AtomNode::class.java)
        Mockito.`when`(rootData.data).thenReturn(dataData)

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