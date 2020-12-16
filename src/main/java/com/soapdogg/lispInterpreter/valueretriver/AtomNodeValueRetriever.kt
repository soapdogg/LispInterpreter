package com.soapdogg.lispInterpreter.valueretriver

import com.soapdogg.lispInterpreter.datamodels.AtomNode
import com.soapdogg.lispInterpreter.datamodels.NodeV2

class AtomNodeValueRetriever {
    fun retrieveAtomNode(
        atomNode: AtomNode,
        variableNameToValueMap: Map<String, NodeV2>
    ): NodeV2 {
        val value = atomNode.value
        return variableNameToValueMap.getOrDefault(value, atomNode)
    }
}