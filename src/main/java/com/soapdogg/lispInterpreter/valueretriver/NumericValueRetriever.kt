package com.soapdogg.lispInterpreter.valueretriver

import com.soapdogg.lispInterpreter.converter.NodeConverter
import com.soapdogg.lispInterpreter.datamodels.Node
import com.soapdogg.lispInterpreter.datamodels.NodeV2
import com.soapdogg.lispInterpreter.determiner.NumericStringDeterminer
import com.soapdogg.lispInterpreter.exceptions.NotNumericException
import com.soapdogg.lispInterpreter.printer.ListNotationPrinter

class NumericValueRetriever(
    private val numericStringDeterminer: NumericStringDeterminer,
    private val nodeConverter: NodeConverter,
    private val listNotationPrinter: ListNotationPrinter
) {

    fun retrieveNumericValue(
        node: Node,
        position: Int,
        functionName: String
    ): Int {
        val nodeV2 = nodeConverter.convertNodeToNodeV2(node)
        return retrieveNumericValue(
            nodeV2,
            position,
            functionName
        )
    }

    fun retrieveNumericValue(
        node: NodeV2,
        position: Int,
        functionName: String
    ): Int {
        val value = listNotationPrinter.printInListNotation(
            node
        )
        val isNumeric = numericStringDeterminer.isStringNumeric(value)
        if (!isNumeric) {
            val sb = """Error! Parameter at position: $position of function $functionName is not numeric!    Actual: $value${'\n'}"""
            throw NotNumericException(sb)
        }
        return value.toInt()
    }
}