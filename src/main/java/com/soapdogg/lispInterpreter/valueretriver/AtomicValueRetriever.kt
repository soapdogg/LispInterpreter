package com.soapdogg.lispInterpreter.valueretriver

import com.soapdogg.lispInterpreter.converter.NodeConverter
import com.soapdogg.lispInterpreter.datamodels.AtomNode
import com.soapdogg.lispInterpreter.datamodels.ExpressionNode
import com.soapdogg.lispInterpreter.datamodels.Node
import com.soapdogg.lispInterpreter.exceptions.NotAtomicException
import com.soapdogg.lispInterpreter.printer.ListNotationPrinter

class AtomicValueRetriever (
    private val listNotationPrinter: ListNotationPrinter,
    private val nodeConverter: NodeConverter
){
    fun retrieveAtomicValue(
        node: Node,
        position: Int,
        functionName: String
    ): String {
        if (node is ExpressionNode) {
            val nodeV2 = nodeConverter.convertNodeToNodeV2(node)
            val listNotation = listNotationPrinter.printInListNotation(
                nodeV2
            )
            val sb = """Error! Parameter at position: $position of function $functionName is not atomic!    Actual: $listNotation${'\n'}"""
            throw NotAtomicException(sb)
        }
        return (node as AtomNode).value
    }
}