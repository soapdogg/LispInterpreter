package com.soapdogg.lispInterpreter.printer

import com.soapdogg.lispInterpreter.comparator.NodeValueComparator
import com.soapdogg.lispInterpreter.constants.ReservedValuesConstants
import com.soapdogg.lispInterpreter.constants.TokenValueConstants
import com.soapdogg.lispInterpreter.datamodels.AtomNode
import com.soapdogg.lispInterpreter.datamodels.ExpressionNode

class ListNotationExpressionNodePrinter(
    private val nodeValueComparator: NodeValueComparator,
    private val atomNodePrinter: AtomNodePrinter
) {

    fun printExpressionNodeInListNotation(
        node: ExpressionNode
    ): String {
        val addressListNotation = if (node.address is ExpressionNode) {
            val addressExpressionNodeValue = printExpressionNodeInListNotation(
               node.address
            )
            TokenValueConstants.OPEN_PARENTHESES + addressExpressionNodeValue
        } else {
            atomNodePrinter.printAtomNode(node.address as AtomNode)
        }
        val dataListNotation = if (node.data is ExpressionNode) {
            val dataExpressionNodeValue = printExpressionNodeInListNotation(
                node.data
            )
            ReservedValuesConstants.SPACE + dataExpressionNodeValue
        } else {
            val dataAtomNodeValue = atomNodePrinter.printAtomNode(node.data as AtomNode)
            val isDataValueNil = nodeValueComparator.equalsNil(dataAtomNodeValue)
            val dataString = if (isDataValueNil) ReservedValuesConstants.EMPTY else ReservedValuesConstants.LIST_DELIMITER + dataAtomNodeValue
            dataString + TokenValueConstants.CLOSE_PARENTHESES
        }
        return addressListNotation + dataListNotation
    }
}