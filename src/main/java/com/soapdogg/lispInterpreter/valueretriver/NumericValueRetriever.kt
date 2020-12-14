package com.soapdogg.lispInterpreter.valueretriver

import com.soapdogg.lispInterpreter.datamodels.NodeV2
import com.soapdogg.lispInterpreter.determiner.NumericStringDeterminer
import com.soapdogg.lispInterpreter.exceptions.NotNumericException
import com.soapdogg.lispInterpreter.printer.ListNotationPrinter

class NumericValueRetriever(
    private val numericStringDeterminer: NumericStringDeterminer,
    private val listNotationPrinter: ListNotationPrinter
) {

    fun retrieveNumericValue(
        nodes: List<NodeV2>,
        functionName: String
    ): List<Int> {
        return nodes.withIndex().map { (index, node) ->
            val value = listNotationPrinter.printInListNotation(
                node
            )
            val isNumeric = numericStringDeterminer.isStringNumeric(value)
            if (!isNumeric) {
                val sb = """Error! Parameter at position: ${index + 1} of function $functionName is not numeric!    Actual: $value${'\n'}"""
                throw NotNumericException(sb)
            }
            value.toInt()
        }
    }

    fun retrieveNumericValue(
        node: NodeV2,
        functionName: String,
        index: Int
    ): Int {
        val value = listNotationPrinter.printInListNotation(
            node
        )
        val isNumeric = numericStringDeterminer.isStringNumeric(value)
        if (!isNumeric) {
            val sb = """Error! Parameter at position: ${index} of function $functionName is not numeric!    Actual: $value${'\n'}"""
            throw NotNumericException(sb)
        }
        return value.toInt()
    }
}