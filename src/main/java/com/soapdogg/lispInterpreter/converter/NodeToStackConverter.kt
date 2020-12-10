package com.soapdogg.lispInterpreter.converter

import com.soapdogg.lispInterpreter.datamodels.AtomNode
import com.soapdogg.lispInterpreter.datamodels.ExpressionListNode
import com.soapdogg.lispInterpreter.datamodels.NodeV2
import com.soapdogg.lispInterpreter.determiner.FunctionLengthDeterminer
import java.util.*

class NodeToStackConverter(
    private val functionLengthDeterminer: FunctionLengthDeterminer
) {

    fun convertToStack(node: NodeV2): Stack<AtomNode> {
        val result = Stack<AtomNode>()
        if (node is ExpressionListNode) {
            val length = functionLengthDeterminer.determineFunctionLength(node)
            for (i in 0 until length) {
                val stack = convertToStack(node.children[i])
                result.addAll(stack)
            }
        } else {
            result.add(node as AtomNode)
        }
        return result
    }
}