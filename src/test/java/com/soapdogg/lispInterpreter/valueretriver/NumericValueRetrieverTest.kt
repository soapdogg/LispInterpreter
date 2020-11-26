package com.soapdogg.lispInterpreter.valueretriver

import com.soapdogg.lispInterpreter.constants.FunctionNameConstants
import com.soapdogg.lispInterpreter.datamodels.Node
import com.soapdogg.lispInterpreter.determiner.NumericStringDeterminer
import com.soapdogg.lispInterpreter.exceptions.NotNumericException
import com.soapdogg.lispInterpreter.printer.ListNotationPrinter
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class NumericValueRetrieverTest {
    private val node = Mockito.mock(Node::class.java)
    private val position = 1
    private val functionName = FunctionNameConstants.QUOTE
    private val atomicValueRetriever = Mockito.mock(AtomicValueRetriever::class.java)
    private val numericStringDeterminer = Mockito.mock(NumericStringDeterminer::class.java)
    private val listNotationPrinter = Mockito.mock(ListNotationPrinter::class.java)
    private val numericValueRetriever = NumericValueRetriever(
        atomicValueRetriever,
        numericStringDeterminer,
        listNotationPrinter
    )

    @Test
    fun nodeIsNotNumericTest() {
        val value = "value"
        Mockito.`when`(
            atomicValueRetriever.retrieveAtomicValue(
                node,
                position,
                functionName
            )
        ).thenReturn(value)
        Mockito.`when`(numericStringDeterminer.isStringNumeric(value)).thenReturn(false)
        Assertions.assertThrows(
            NotNumericException::class.java
        ) {
            numericValueRetriever.retrieveNumericValue(
                node,
                position,
                functionName
            )
        }
    }

    @Test
    fun nodeIsNumericTest() {
        val value = 34
        Mockito.`when`(
            atomicValueRetriever.retrieveAtomicValue(
                node,
                position,
                functionName
            )
        ).thenReturn(value.toString())
        Mockito.`when`(numericStringDeterminer.isStringNumeric(value.toString())).thenReturn(true)
        val actual = numericValueRetriever.retrieveNumericValue(
            node,
            position,
            functionName
        )
        Assertions.assertEquals(value, actual)
    }
}