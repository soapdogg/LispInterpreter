package com.soapdogg.lispInterpreter.valueretriver

import com.soapdogg.lispInterpreter.datamodels.Node
import com.soapdogg.lispInterpreter.determiner.NumericStringDeterminer
import com.soapdogg.lispInterpreter.exceptions.NotNumericException
import com.soapdogg.lispInterpreter.printer.ListNotationPrinter

class NumericValueRetriever(
    private val atomicValueRetriever: AtomicValueRetriever,
    private val numericStringDeterminer: NumericStringDeterminer,
    private val listNotationPrinter: ListNotationPrinter
) {

    fun retrieveNumericValue(
        node: Node,
        position: Int,
        functionName: String
    ): Int {
        val value = atomicValueRetriever.retrieveAtomicValue(
            node,
            position,
            functionName
        )
        val isNumeric = numericStringDeterminer.isStringNumeric(value)
        if (!isNumeric) {
            val listNotation = listNotationPrinter.printInListNotation(
                node
            )
            val sb = """Error! Parameter at position: $position of function $functionName is not numeric!    Actual: $listNotation${'\n'}"""
            throw NotNumericException(sb)
        }
        return value.toInt()
    }
}