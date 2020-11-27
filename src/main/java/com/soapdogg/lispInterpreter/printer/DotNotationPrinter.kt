package com.soapdogg.lispInterpreter.printer

import com.soapdogg.lispInterpreter.constants.ReservedValuesConstants
import com.soapdogg.lispInterpreter.datamodels.AtomNode
import com.soapdogg.lispInterpreter.datamodels.ExpressionNode
import com.soapdogg.lispInterpreter.datamodels.Node
import com.soapdogg.lispInterpreter.determiner.ExpressionNodeDeterminer

class DotNotationPrinter (
    private val expressionNodeDeterminer: ExpressionNodeDeterminer,
    private val atomNodePrinter: AtomNodePrinter,
    private val dotNotationExpressionNodePrinter: DotNotationExpressionNodePrinter
) {

    fun printInDotNotation(nodes: List<Node>): String {
        if (nodes.isEmpty()) return """${ReservedValuesConstants.NIL}${ReservedValuesConstants.NEW_LINE}"""
        return nodes.joinToString(
            ReservedValuesConstants.NEW_LINE.toString(),
            ReservedValuesConstants.EMPTY,
            ReservedValuesConstants.NEW_LINE.toString()
        ) {node -> printInDotNotation(node)}
    }

    fun printInDotNotation(node: Node): String {
        val isExpressionNode = expressionNodeDeterminer.isExpressionNode(node)
        return if (isExpressionNode) dotNotationExpressionNodePrinter.printExpressionNodeInDotNotation(node as ExpressionNode)
        else atomNodePrinter.printAtomNode((node as AtomNode))
    }
}