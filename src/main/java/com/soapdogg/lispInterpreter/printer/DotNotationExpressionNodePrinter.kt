package com.soapdogg.lispInterpreter.printer

import com.soapdogg.lispInterpreter.constants.ReservedValuesConstants
import com.soapdogg.lispInterpreter.constants.TokenValueConstants
import com.soapdogg.lispInterpreter.datamodels.AtomNode
import com.soapdogg.lispInterpreter.datamodels.ExpressionNode

class DotNotationExpressionNodePrinter (
    private val atomNodePrinter: AtomNodePrinter
){

    fun printExpressionNodeInDotNotation(node: ExpressionNode): String {
        val addressDotNotation = if (node.address is ExpressionNode) printExpressionNodeInDotNotation(node.address) else atomNodePrinter.printAtomNode(node.address as AtomNode)
        val dataDotNotation = if (node.data is ExpressionNode) printExpressionNodeInDotNotation(node.data) else atomNodePrinter.printAtomNode(node.data as AtomNode)
        return (TokenValueConstants.OPEN_PARENTHESES
            + addressDotNotation
            + ReservedValuesConstants.LIST_DELIMITER
            + dataDotNotation
            + TokenValueConstants.CLOSE_PARENTHESES)
    }
}