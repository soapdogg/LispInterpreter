package com.soapdogg.lispInterpreter.evaluator

import com.soapdogg.lispInterpreter.datamodels.AtomNode
import com.soapdogg.lispInterpreter.datamodels.Node

class AtomNodeEvaluator {
    fun evaluate(
        atomNode: AtomNode,
        variableNameToValueMap: Map<String, Node>
    ): Node {
        val value = atomNode.value
        return variableNameToValueMap.getOrDefault(value, atomNode)
    }
}