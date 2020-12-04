package com.soapdogg.lispInterpreter.evaluator

import com.soapdogg.lispInterpreter.datamodels.AtomNode
import com.soapdogg.lispInterpreter.datamodels.NodeV2

class AtomNodeEvaluator {
    fun evaluate(
        atomNode: AtomNode,
        variableNameToValueMap: Map<String, NodeV2>
    ): NodeV2 {
        val value = atomNode.value
        return variableNameToValueMap.getOrDefault(value, atomNode)
    }
}