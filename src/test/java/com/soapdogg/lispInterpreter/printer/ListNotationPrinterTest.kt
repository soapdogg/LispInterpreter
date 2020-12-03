package com.soapdogg.lispInterpreter.printer

import com.soapdogg.lispInterpreter.constants.ReservedValuesConstants
import com.soapdogg.lispInterpreter.converter.NodeConverter
import com.soapdogg.lispInterpreter.datamodels.AtomNode
import com.soapdogg.lispInterpreter.datamodels.Node
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class ListNotationPrinterTest {

    private val atomNodePrinter = Mockito.mock(AtomNodePrinter::class.java)
    private val nodeConverter = Mockito.mock(NodeConverter::class.java)
    private val listNotationPrinter = ListNotationPrinter(
        atomNodePrinter,
        nodeConverter
    )

    @Test
    fun printNonEmptyListOfNodesTest() {
        val atomNode = Mockito.mock(AtomNode::class.java)
        Mockito.`when`(nodeConverter.convertNodeToNodeV2(atomNode)).thenReturn(atomNode)
        val value = "value"
        Mockito.`when`(atomNodePrinter.printAtomNode(atomNode)).thenReturn(value)
        val nodes = listOf(atomNode)
        val expected = """$value${ReservedValuesConstants.NEW_LINE}"""
        val actual = listNotationPrinter.printInListNotation(nodes)
        Assertions.assertEquals(expected, actual)
    }

    @Test
    fun printSingleNodeTest() {
        val atomNode = Mockito.mock(AtomNode::class.java)
        Mockito.`when`(nodeConverter.convertNodeToNodeV2(atomNode)).thenReturn(atomNode)
        val value = "value"
        Mockito.`when`(atomNodePrinter.printAtomNode(atomNode)).thenReturn(value)
        val actual = listNotationPrinter.printInListNotation(atomNode as Node)
        Assertions.assertEquals(value, actual)
    }
}