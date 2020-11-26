package com.soapdogg.lispInterpreter.valueretriver

import com.soapdogg.lispInterpreter.datamodels.AtomNode
import com.soapdogg.lispInterpreter.datamodels.Node
import com.soapdogg.lispInterpreter.determiner.ExpressionNodeDeterminer
import com.soapdogg.lispInterpreter.exceptions.NotAtomicException
import com.soapdogg.lispInterpreter.printer.ListNotationPrinter

class AtomicValueRetriever (
    private val expressionNodeDeterminer: ExpressionNodeDeterminer,
    private val listNotationPrinter: ListNotationPrinter
){
    fun retrieveAtomicValue(
        node: Node,
        position: Int,
        functionName: String
    ): String {
        val isList = expressionNodeDeterminer.isExpressionNode(node)
        if (isList) {
            val listNotation = listNotationPrinter.printInListNotation(
                node
            )
            val sb = """Error! Parameter at position: $position of function $functionName is not atomic!    Actual: $listNotation${'\n'}"""
            throw NotAtomicException(sb)
        }
        return (node as AtomNode).value
    }
}