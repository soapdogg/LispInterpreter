package com.soapdogg.lispInterpreter.function

import com.soapdogg.lispInterpreter.datamodels.AtomNode
import com.soapdogg.lispInterpreter.datamodels.ExpressionListNode
import com.soapdogg.lispInterpreter.datamodels.NodeV2
import com.soapdogg.lispInterpreter.generator.NodeGenerator
import java.util.*

class AtomFunction(
    private val nodeGenerator: NodeGenerator
): Function {

    override fun evaluate(
        params: Stack<NodeV2>,
        variableMap: Map<String, NodeV2>
    ): NodeV2 {
        var first = params.pop()
        if (first is AtomNode) {
            first = variableMap.getOrDefault(first.value, first)
        }
        val isAtom = first !is ExpressionListNode
        return nodeGenerator.generateAtomNode(isAtom)
    }
}