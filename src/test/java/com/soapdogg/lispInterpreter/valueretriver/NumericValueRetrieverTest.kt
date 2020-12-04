package com.soapdogg.lispInterpreter.valueretriver

import com.soapdogg.lispInterpreter.constants.FunctionNameConstants
import com.soapdogg.lispInterpreter.converter.NodeConverter
import com.soapdogg.lispInterpreter.datamodels.Node
import com.soapdogg.lispInterpreter.datamodels.NodeV2
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
    private val numericStringDeterminer = Mockito.mock(NumericStringDeterminer::class.java)
    private val nodeConverter = Mockito.mock(NodeConverter::class.java)
    private val listNotationPrinter = Mockito.mock(ListNotationPrinter::class.java)
    private val numericValueRetriever = NumericValueRetriever(
        numericStringDeterminer,
        nodeConverter,
        listNotationPrinter
    )

    @Test
    fun nodeIsNotNumericTest() {
        val v2 = Mockito.mock(NodeV2::class.java)
        Mockito.`when`(nodeConverter.convertNodeToNodeV2(node)).thenReturn(v2)

        val value = "value"
        Mockito.`when`(
           listNotationPrinter.printInListNotation(v2)
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
        val v2 = Mockito.mock(NodeV2::class.java)
        Mockito.`when`(nodeConverter.convertNodeToNodeV2(node)).thenReturn(v2)

        val value = 34
        Mockito.`when`(
            listNotationPrinter.printInListNotation(v2)
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