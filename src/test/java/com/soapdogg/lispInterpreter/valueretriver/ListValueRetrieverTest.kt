package com.soapdogg.lispInterpreter.valueretriver

import com.soapdogg.lispInterpreter.datamodels.*
import com.soapdogg.lispInterpreter.exceptions.NotAListException
import com.soapdogg.lispInterpreter.printer.DotNotationPrinter
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito


class ListValueRetrieverTest {
    private val functionName = "functionName"
    private val dotNotationPrinter = Mockito.mock(DotNotationPrinter::class.java)
    private val listValueRetriever = ListValueRetriever(
        dotNotationPrinter
    )

    @Test
    fun inputIsAListTest() {
        val node = Mockito.mock(ExpressionListNode::class.java)
        val variableNameToValueMap: Map<String, NodeV2> = mapOf()
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
        val nodeValue = "nodeValue"
        Mockito.`when`(node.value).thenReturn(nodeValue)

        val result = Mockito.mock(ExpressionListNode::class.java)
        val variableNameToValueMap = mapOf(Pair(nodeValue, result))
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
        val nodeValue = "nodeValue"
        Mockito.`when`(node.value).thenReturn(nodeValue)

        val result = Mockito.mock(AtomNode::class.java)
        val variableNameToValueMap = mapOf(Pair(nodeValue, result))
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
        val nodeValue = "nodeValue"
        Mockito.`when`(node.value).thenReturn(nodeValue)

        val variableNameToValueMap: Map<String, NodeV2> = mapOf()
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