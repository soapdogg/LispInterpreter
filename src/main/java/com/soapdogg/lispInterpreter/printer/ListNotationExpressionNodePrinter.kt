package com.soapdogg.lispInterpreter.printer

import com.soapdogg.lispInterpreter.comparator.NodeValueComparator
import com.soapdogg.lispInterpreter.constants.ReservedValuesConstants
import com.soapdogg.lispInterpreter.constants.TokenValueConstants
import com.soapdogg.lispInterpreter.datamodels.AtomNode
import com.soapdogg.lispInterpreter.datamodels.ExpressionNode
import com.soapdogg.lispInterpreter.determiner.ExpressionNodeDeterminer

class ListNotationExpressionNodePrinter(
    private val expressionNodeDeterminer: ExpressionNodeDeterminer,
    private val nodeValueComparator: NodeValueComparator,
    private val atomNodePrinter: AtomNodePrinter
) {

    fun printExpressionNodeInListNotation(
        node: ExpressionNode
    ): String {
        val address = node.address
        val isAddressList = expressionNodeDeterminer.isExpressionNode(address)
        val addressListNotation = if (isAddressList) {
            val expressionNodeAddress = address as ExpressionNode
            val addressExpressionNodeValue = printExpressionNodeInListNotation(
                expressionNodeAddress
            )
            TokenValueConstants.OPEN_PARENTHESES + addressExpressionNodeValue
        } else {
            atomNodePrinter.printAtomNode((address as AtomNode))
        }
        val data = node.data
        val isDataList = expressionNodeDeterminer.isExpressionNode(data)
        val dataListNotation = if (isDataList) {
            val expressionNodeData = data as ExpressionNode
            val dataExpressionNodeValue = printExpressionNodeInListNotation(
                expressionNodeData
            )
            ReservedValuesConstants.SPACE + dataExpressionNodeValue
        } else {
            val dataAtomNodeValue = atomNodePrinter.printAtomNode((data as AtomNode))
            val isDataValueNil = nodeValueComparator.equalsNil(dataAtomNodeValue)
            val dataString = if (isDataValueNil) ReservedValuesConstants.EMPTY else ReservedValuesConstants.LIST_DELIMITER + dataAtomNodeValue
            dataString + TokenValueConstants.CLOSE_PARENTHESES
        }
        return addressListNotation + dataListNotation
    }
}