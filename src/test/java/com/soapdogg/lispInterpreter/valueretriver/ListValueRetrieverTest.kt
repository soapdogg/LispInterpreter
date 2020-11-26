package com.soapdogg.lispInterpreter.valueretriver

import com.soapdogg.lispInterpreter.datamodels.AtomNode
import com.soapdogg.lispInterpreter.datamodels.ExpressionNode
import com.soapdogg.lispInterpreter.datamodels.Node
import com.soapdogg.lispInterpreter.determiner.ExpressionNodeDeterminer
import com.soapdogg.lispInterpreter.exceptions.NotAListException
import com.soapdogg.lispInterpreter.printer.DotNotationPrinter
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito


class ListValueRetrieverTest {
    private val functionName = "functionName"
    private val expressionNodeDeterminer = Mockito.mock(ExpressionNodeDeterminer::class.java)
    private val dotNotationPrinter = Mockito.mock(DotNotationPrinter::class.java)
    private val listValueRetriever = ListValueRetriever(
        expressionNodeDeterminer,
        dotNotationPrinter
    )

    @Test
    fun inputIsAListTest() {
        val node: Node = Mockito.mock(ExpressionNode::class.java)
        Mockito.`when`(expressionNodeDeterminer.isExpressionNode(node)).thenReturn(true)
        val variableNameToValueMap: Map<String, Node> = mapOf()
        val actual = listValueRetriever.retrieveListValue(
            node,
            functionName,
            variableNameToValueMap
        )
        Assertions.assertEquals(node, actual)
        Mockito.verifyNoInteractions(dotNotationPrinter)
    }

    @Test
    fun inputIsAVariableListTest() {
        val node = Mockito.mock(AtomNode::class.java)
        Mockito.`when`(expressionNodeDeterminer.isExpressionNode(node)).thenReturn(false)
        val nodeValue = "nodeValue"
        Mockito.`when`(node.value).thenReturn(nodeValue)
        val result: Node = Mockito.mock(ExpressionNode::class.java)
        val variableNameToValueMap = mapOf(Pair(nodeValue, result))
        Mockito.`when`(expressionNodeDeterminer.isExpressionNode(result)).thenReturn(true)
        val actual = listValueRetriever.retrieveListValue(
            node,
            functionName,
            variableNameToValueMap
        )
        Assertions.assertEquals(result, actual)
        Mockito.verifyNoInteractions(dotNotationPrinter)
    }

    @Test
    fun inputIsVariableButNotListTest() {
        val node = Mockito.mock(AtomNode::class.java)
        Mockito.`when`(expressionNodeDeterminer.isExpressionNode(node)).thenReturn(false)
        val nodeValue = "nodeValue"
        Mockito.`when`(node.value).thenReturn(nodeValue)
        val result: Node = Mockito.mock(ExpressionNode::class.java)
        val variableNameToValueMap = mapOf(Pair(nodeValue, result))
        Mockito.`when`(expressionNodeDeterminer.isExpressionNode(result)).thenReturn(true)
        Mockito.`when`(expressionNodeDeterminer.isExpressionNode(result)).thenReturn(false)
        Assertions.assertThrows(
            NotAListException::class.java
        ) {
            listValueRetriever.retrieveListValue(
                node,
                functionName,
                variableNameToValueMap
            )
        }
    }

    @Test
    fun inputIsNotAVariableTest() {
        val node = Mockito.mock(AtomNode::class.java)
        Mockito.`when`(expressionNodeDeterminer.isExpressionNode(node)).thenReturn(false)
        val nodeValue = "nodeValue"
        Mockito.`when`(node.value).thenReturn(nodeValue)
        val variableNameToValueMap: Map<String, Node> = mapOf()
        Assertions.assertThrows(
            NotAListException::class.java
        ) {
            listValueRetriever.retrieveListValue(
                node,
                functionName,
                variableNameToValueMap
            )
        }
    }
}