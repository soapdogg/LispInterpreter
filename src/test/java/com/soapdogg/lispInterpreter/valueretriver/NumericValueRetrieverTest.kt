package com.soapdogg.lispInterpreter.valueretriver

import com.soapdogg.lispInterpreter.constants.FunctionNameConstants
import com.soapdogg.lispInterpreter.datamodels.AtomNode
import com.soapdogg.lispInterpreter.datamodels.NodeV2
import com.soapdogg.lispInterpreter.determiner.NumericStringDeterminer
import com.soapdogg.lispInterpreter.exceptions.NotNumericException
import com.soapdogg.lispInterpreter.printer.ListNotationPrinter
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class NumericValueRetrieverTest {
    private val functionName = FunctionNameConstants.QUOTE
    private val index = 1
    private val numericStringDeterminer = Mockito.mock(NumericStringDeterminer::class.java)
    private val listNotationPrinter = Mockito.mock(ListNotationPrinter::class.java)
    private val numericValueRetriever = NumericValueRetriever(
        numericStringDeterminer,
        listNotationPrinter
    )

    @Test
    fun nodeIsNotNumericTest() {
        val node = Mockito.mock(AtomNode::class.java)
        val value = "value"
        Mockito.`when`(node.value).thenReturn(value)
        Mockito.`when`(numericStringDeterminer.isStringNumeric(value)).thenReturn(false)
        Assertions.assertThrows(
            NotNumericException::class.java
        ) {
            numericValueRetriever.retrieveNumericValue(
                node,
                functionName,
                index
            )
        }
    }

    @Test
    fun nodeIsNumericTest() {
        val value = 34
        val node = Mockito.mock(AtomNode::class.java)
        Mockito.`when`(node.value).thenReturn(value.toString())
        Mockito.`when`(numericStringDeterminer.isStringNumeric(value.toString())).thenReturn(true)
        val actual = numericValueRetriever.retrieveNumericValue(
            node,
            functionName,
            index
        )
        Assertions.assertEquals(value, actual)
    }
}