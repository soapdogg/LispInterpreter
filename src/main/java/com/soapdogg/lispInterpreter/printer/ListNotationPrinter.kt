package com.soapdogg.lispInterpreter.printer

import com.soapdogg.lispInterpreter.constants.ReservedValuesConstants
import com.soapdogg.lispInterpreter.constants.TokenValueConstants
import com.soapdogg.lispInterpreter.datamodels.AtomNode
import com.soapdogg.lispInterpreter.datamodels.ExpressionNode
import com.soapdogg.lispInterpreter.datamodels.Node

class ListNotationPrinter (
    private val atomNodePrinter: AtomNodePrinter,
    private val listNotationExpressionNodePrinter: ListNotationExpressionNodePrinter
){

    fun printInListNotation(
        nodes: List<Node>
    ): String {
        return nodes.joinToString(
            ReservedValuesConstants.NEW_LINE.toString(),
            ReservedValuesConstants.EMPTY,
            ReservedValuesConstants.NEW_LINE.toString()
        ) { node -> this.printInListNotation(node) }
    }

    fun printInListNotation(
        node: Node
    ): String {
        if (node is ExpressionNode) {
            val result = TokenValueConstants.OPEN_PARENTHESES
            return result.toString() + listNotationExpressionNodePrinter.printExpressionNodeInListNotation(
                node
            )
        }
        return atomNodePrinter.printAtomNode(node as AtomNode)
    }
}