package com.soapdogg.lispInterpreter.valueretriver

import com.soapdogg.lispInterpreter.constants.FunctionNameConstants
import com.soapdogg.lispInterpreter.datamodels.AtomNode
import com.soapdogg.lispInterpreter.datamodels.ExpressionNode
import com.soapdogg.lispInterpreter.exceptions.NotAtomicException
import com.soapdogg.lispInterpreter.printer.ListNotationPrinter
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class AtomicValueRetrieverTest {
    private val position = 1
    private val functionName = FunctionNameConstants.TIMES
    private val listNotationPrinter = Mockito.mock(ListNotationPrinter::class.java)
    private val atomicValueRetriever = AtomicValueRetriever(
        listNotationPrinter
    )

    @Test
    fun nodeIsListTest() {
        val node = Mockito.mock(ExpressionNode::class.java)
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
        val node = Mockito.mock(AtomNode::class.java)
        val value = "value"
        Mockito.`when`(node.value).thenReturn(value)
        val actual = atomicValueRetriever.retrieveAtomicValue(
            node,
            position,
            functionName
        )
        Assertions.assertEquals(value, actual)
    }
}