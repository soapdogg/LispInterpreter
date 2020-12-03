package com.soapdogg.lispInterpreter.printer

import com.soapdogg.lispInterpreter.constants.ReservedValuesConstants
import com.soapdogg.lispInterpreter.constants.TokenValueConstants
import com.soapdogg.lispInterpreter.converter.NodeConverter
import com.soapdogg.lispInterpreter.datamodels.*
import java.lang.StringBuilder

class ListNotationPrinter (
    private val atomNodePrinter: AtomNodePrinter,
    private val nodeConverter: NodeConverter
){

    fun printInListNotation(
        nodes: List<Node>
    ): String {
        return nodes.map {
            nodeConverter.convertNodeToNodeV2(it)
        }.joinToString(
            ReservedValuesConstants.NEW_LINE.toString(),
            ReservedValuesConstants.EMPTY,
            ReservedValuesConstants.NEW_LINE.toString()
        ) { nodeV2 ->
            printInListNotation(nodeV2)
        }
    }

    fun printInListNotation(
        node: Node
    ): String {
        val nodeV2 = nodeConverter.convertNodeToNodeV2(node)
        return printInListNotation(nodeV2)
    }

    fun printInListNotation(node: NodeV2):String {
        if (node is ExpressionListNode) {
            val sb = StringBuilder()
            sb.append(TokenValueConstants.OPEN_PARENTHESES)
            var i = 0
            while (i < node.children.size - 1){
                sb.append(printInListNotation(node.children[i]))
                sb.append(ReservedValuesConstants.SPACE)
                ++i
            }
            var result = sb.toString().trim()
            val lastChild = node.children[node.children.size - 1]
            if (lastChild is AtomNode && lastChild.value == ReservedValuesConstants.NIL) {
                result += TokenValueConstants.CLOSE_PARENTHESES
            } else {
                result += ReservedValuesConstants.LIST_DELIMITER
                result += printInListNotation(lastChild)
                result += TokenValueConstants.CLOSE_PARENTHESES
            }
            return result
        }
        return atomNodePrinter.printAtomNode(node as AtomNode)
    }
}