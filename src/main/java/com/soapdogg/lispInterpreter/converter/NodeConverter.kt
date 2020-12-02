package com.soapdogg.lispInterpreter.converter

import com.soapdogg.lispInterpreter.datamodels.*

class NodeConverter {

    fun convertNodeV2ToNode(version2Node: NodeV2): Node {
        if (version2Node is AtomNode) return version2Node
        val expressionListNode = version2Node as ExpressionListNode
        val convertedAddress = convertNodeV2ToNode(expressionListNode.children[0])
        return if (expressionListNode.children.size > 1) {
            val convertedData = convertNodeV2ToNode(
                ExpressionListNode(
                    expressionListNode.children.subList(1, expressionListNode.children.size)
                )
            )
            ExpressionNode(convertedAddress, convertedData)
        } else {
            convertedAddress
        }
    }

    fun convertNodeToNodeV2(version1Node: Node): NodeV2 {
        if (version1Node is AtomNode) return version1Node
        throw NotImplementedError()
    }
}