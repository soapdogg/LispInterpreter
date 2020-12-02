package com.soapdogg.lispInterpreter.converter

import com.soapdogg.lispInterpreter.constants.ReservedValuesConstants
import com.soapdogg.lispInterpreter.datamodels.*

class NodeConverter {

    /*fun convertExpressionListNodeToExpressionNode(
        version2Nodes: List<NodeV2>
    ) : Node {
        if (version2Nodes.isEmpty()) return AtomNode(ReservedValuesConstants.NIL)
        val address = version2Nodes[0]
        val convertedAddress = if (address is AtomNode) address else convertExpressionListNodeToExpressionNode((address as ExpressionListNode).children)
        return if (version2Nodes.size > 1) {
            val convertedData = convertExpressionListNodeToExpressionNode(version2Nodes.subList(1, version2Nodes.size - 1))
            ExpressionNode(convertedAddress, convertedData)
        } else {
            convertedAddress
        }
    }*/

    fun convertNodeV2ToNode(version2Node: NodeV2): Node {
        if (version2Node is AtomNode) return version2Node
        throw NotImplementedError()
    }

    fun convertNodeToNodeV2(version1Node: Node): NodeV2 {
        if (version1Node is AtomNode) return version1Node
        throw NotImplementedError()
    }
}