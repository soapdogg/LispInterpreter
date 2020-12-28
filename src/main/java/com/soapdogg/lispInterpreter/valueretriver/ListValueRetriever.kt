package com.soapdogg.lispInterpreter.valueretriver

import com.soapdogg.lispInterpreter.datamodels.*
import com.soapdogg.lispInterpreter.exceptions.NotAListException
import com.soapdogg.lispInterpreter.printer.DotNotationPrinter

class ListValueRetriever(
    private val dotNotationPrinter: DotNotationPrinter
){
    fun retrieveListValue(
        node: NodeV2,
        functionName: String
    ): ExpressionListNode {
        if (node is ExpressionListNode) {
            return node
        }
        val sb = """Error! Parameter of $functionName is not a list.    Actual: ${dotNotationPrinter.printInDotNotation(node)}${'\n'}"""
        throw NotAListException(sb)
    }
}