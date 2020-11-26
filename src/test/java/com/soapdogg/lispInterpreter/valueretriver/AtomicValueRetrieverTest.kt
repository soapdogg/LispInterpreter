package com.soapdogg.lispInterpreter.valueretriver

import com.soapdogg.lispInterpreter.constants.FunctionNameConstants
import com.soapdogg.lispInterpreter.datamodels.AtomNode
import com.soapdogg.lispInterpreter.datamodels.Node
import com.soapdogg.lispInterpreter.determiner.ExpressionNodeDeterminer
import com.soapdogg.lispInterpreter.exceptions.NotAtomicException
import com.soapdogg.lispInterpreter.printer.ListNotationPrinter
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class AtomicValueRetrieverTest {
    private val node: Node = Mockito.mock(AtomNode::class.java)
    private val position = 1
    private val functionName = FunctionNameConstants.TIMES
    private val expressionNodeDeterminer = Mockito.mock(ExpressionNodeDeterminer::class.java)
    private val listNotationPrinter = Mockito.mock(ListNotationPrinter::class.java)
    private val atomicValueRetriever = AtomicValueRetriever(
        expressionNodeDeterminer,
        listNotationPrinter
    )

    @Test
    fun nodeIsListTest() {
        Mockito.`when`(expressionNodeDeterminer.isExpressionNode(node)).thenReturn(true)
        Assertions.assertThrows(
            NotAtomicException::class.java
        ) {
            atomicValueRetriever.retrieveAtomicValue(
                node,
                position,
                functionName
            )
        }
    }

    @Test
    fun nodeIsNotListTest() {
        Mockito.`when`(expressionNodeDeterminer.isExpressionNode(node)).thenReturn(false)
        val value = "value"
        Mockito.`when`((node as AtomNode).value).thenReturn(value)
        val actual = atomicValueRetriever.retrieveAtomicValue(
            node,
            position,
            functionName
        )
        Assertions.assertEquals(value, actual)
    }
}