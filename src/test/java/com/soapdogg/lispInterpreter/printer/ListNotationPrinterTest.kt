package com.soapdogg.lispInterpreter.printer

import com.soapdogg.lispInterpreter.constants.ReservedValuesConstants
import com.soapdogg.lispInterpreter.constants.TokenValueConstants
import com.soapdogg.lispInterpreter.datamodels.AtomNode
import com.soapdogg.lispInterpreter.datamodels.ExpressionNode
import com.soapdogg.lispInterpreter.datamodels.Node
import com.soapdogg.lispInterpreter.determiner.ExpressionNodeDeterminer
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class ListNotationPrinterTest {

    private val expressionNodeDeterminer = Mockito.mock(ExpressionNodeDeterminer::class.java)
    private val atomNodePrinter = Mockito.mock(AtomNodePrinter::class.java)
    private val listNotationExpressionNodePrinter = Mockito.mock(ListNotationExpressionNodePrinter::class.java)
    private val listNotationPrinter = ListNotationPrinter(
        expressionNodeDeterminer,
        atomNodePrinter,
        listNotationExpressionNodePrinter
    )

    @Test
    fun printNonEmptyListOfNodesTest() {
        val atomNode = Mockito.mock(AtomNode::class.java)
        Mockito.`when`(expressionNodeDeterminer.isExpressionNode(atomNode)).thenReturn(false)
        val value = "value"
        Mockito.`when`(atomNodePrinter.printAtomNode(atomNode)).thenReturn(value)
        val nodes: List<Node> = listOf(atomNode)
        val expected = """$value${ReservedValuesConstants.NEW_LINE}"""
        val actual = listNotationPrinter.printInListNotation(nodes)
        Assertions.assertEquals(expected, actual)
    }

    @Test
    fun printExpressionNodeListTest() {
        val expressionNode = Mockito.mock(ExpressionNode::class.java)
        Mockito.`when`(expressionNodeDeterminer.isExpressionNode(expressionNode)).thenReturn(true)
        val value = "value"
        Mockito.`when`(listNotationExpressionNodePrinter.printExpressionNodeInListNotation(expressionNode)).thenReturn(value)
        val expected = TokenValueConstants.OPEN_PARENTHESES + value
        val actual = listNotationPrinter.printInListNotation(expressionNode)
        Assertions.assertEquals(expected, actual)
    }
}