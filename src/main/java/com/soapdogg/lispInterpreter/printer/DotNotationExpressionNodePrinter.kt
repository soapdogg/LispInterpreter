package com.soapdogg.lispInterpreter.printer

import com.soapdogg.lispInterpreter.constants.ReservedValuesConstants
import com.soapdogg.lispInterpreter.constants.TokenValueConstants
import com.soapdogg.lispInterpreter.datamodels.AtomNode
import com.soapdogg.lispInterpreter.datamodels.ExpressionNode
import com.soapdogg.lispInterpreter.determiner.ExpressionNodeDeterminer

class DotNotationExpressionNodePrinter (
    private val expressionNodeDeterminer: ExpressionNodeDeterminer,
    private val atomNodePrinter: AtomNodePrinter
){

    fun printExpressionNodeInDotNotation(node: ExpressionNode): String {
        val isAddressExpressionNode = expressionNodeDeterminer.isExpressionNode(node.address)
        val addressDotNotation = if (isAddressExpressionNode) printExpressionNodeInDotNotation(node.address as ExpressionNode) else atomNodePrinter.printAtomNode((node.address as AtomNode))
        val isDataExpressionNode = expressionNodeDeterminer.isExpressionNode(node.data)
        val dataDotNotation = if (isDataExpressionNode) printExpressionNodeInDotNotation(node.data as ExpressionNode) else atomNodePrinter.printAtomNode((node.data as AtomNode))
        return (TokenValueConstants.OPEN_PARENTHESES
            + addressDotNotation
            + ReservedValuesConstants.LIST_DELIMITER
            + dataDotNotation
            + TokenValueConstants.CLOSE_PARENTHESES)
    }
}