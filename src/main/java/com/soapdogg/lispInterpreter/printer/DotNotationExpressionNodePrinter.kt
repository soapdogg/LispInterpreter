package com.soapdogg.lispInterpreter.printer

import com.soapdogg.lispInterpreter.constants.ReservedValuesConstants
import com.soapdogg.lispInterpreter.constants.TokenValueConstants
import com.soapdogg.lispInterpreter.datamodels.AtomNode
import com.soapdogg.lispInterpreter.datamodels.ExpressionListNode

class DotNotationExpressionNodePrinter (
    private val atomNodePrinter: AtomNodePrinter
){

    fun printExpressionNodeInDotNotation(node: ExpressionListNode): String {
        val addressDotNotation = if (node.children[0] is ExpressionListNode) printExpressionNodeInDotNotation(node.children[0] as ExpressionListNode) else atomNodePrinter.printAtomNode(node.children[0] as AtomNode)
        if (node.children.size == 1) return addressDotNotation
        val dataDotNotation = if (node.children.size > 2) printExpressionNodeInDotNotation(ExpressionListNode(node.children.subList(1, node.children.size))) else atomNodePrinter.printAtomNode(node.children[1] as AtomNode)
        return (TokenValueConstants.OPEN_PARENTHESES
            + addressDotNotation
            + ReservedValuesConstants.LIST_DELIMITER
            + dataDotNotation
            + TokenValueConstants.CLOSE_PARENTHESES)
    }
}