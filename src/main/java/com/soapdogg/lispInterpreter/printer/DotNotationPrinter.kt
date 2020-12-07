package com.soapdogg.lispInterpreter.printer

import com.soapdogg.lispInterpreter.constants.ReservedValuesConstants
import com.soapdogg.lispInterpreter.datamodels.*

class DotNotationPrinter (
    private val atomNodePrinter: AtomNodePrinter,
    private val dotNotationExpressionNodePrinter: DotNotationExpressionNodePrinter
) {

    fun printInDotNotation(nodes: List<NodeV2>): String {
        if (nodes.isEmpty()) return """${ReservedValuesConstants.NIL}${ReservedValuesConstants.NEW_LINE}"""
        return nodes.joinToString(
            ReservedValuesConstants.NEW_LINE.toString(),
            ReservedValuesConstants.EMPTY,
            ReservedValuesConstants.NEW_LINE.toString()
        ) {node -> printInDotNotation(node)}
    }

    fun printInDotNotation(node: NodeV2): String {
        return if (node is ExpressionListNode) dotNotationExpressionNodePrinter.printExpressionNodeInDotNotation(node)
        else atomNodePrinter.printAtomNode(node as AtomNode)
    }
}