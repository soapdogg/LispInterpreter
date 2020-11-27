package com.soapdogg.lispInterpreter.printer

import com.soapdogg.lispInterpreter.determiner.ExpressionNodeDeterminer
import com.soapdogg.lispInterpreter.constants.ReservedValuesConstants
import com.soapdogg.lispInterpreter.constants.TokenValueConstants
import com.soapdogg.lispInterpreter.datamodels.AtomNode
import com.soapdogg.lispInterpreter.datamodels.ExpressionNode
import com.soapdogg.lispInterpreter.datamodels.Node

class ListNotationPrinter (
    private val expressionNodeDeterminer: ExpressionNodeDeterminer,
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
        val isExpressionNode = expressionNodeDeterminer.isExpressionNode(node)
        if (isExpressionNode) {
            val result = TokenValueConstants.OPEN_PARENTHESES
            return result.toString() + listNotationExpressionNodePrinter.printExpressionNodeInListNotation(
                node as ExpressionNode
            )
        }
        return atomNodePrinter.printAtomNode((node as AtomNode))
    }
}