package com.soapdogg.lispInterpreter.function

import com.soapdogg.lispInterpreter.datamodels.AtomNode
import com.soapdogg.lispInterpreter.datamodels.NodeV2
import com.soapdogg.lispInterpreter.generator.NodeGenerator
import com.soapdogg.lispInterpreter.valueretriver.AtomNodeValueRetriever
import java.util.*

class EqFunction(
    private val atomNodeValueRetriever: AtomNodeValueRetriever,
    private val nodeGenerator: NodeGenerator
): Function {
    override fun evaluate(
        params: Stack<NodeV2>,
        variableMap: Map<String, NodeV2>
    ): NodeV2 {
        val first = params.pop()
        val second = params.pop()
        val evaluatedAddress = if (first is AtomNode) {
            atomNodeValueRetriever.retrieveAtomNode(
                first,
                variableMap
            )
        } else {
            first
        }
        val evaluatedData = if (second is AtomNode) {
            atomNodeValueRetriever.retrieveAtomNode(
                second,
                variableMap
            )
        } else {
            second
        }
        val isEqual = evaluatedAddress == evaluatedData
        return nodeGenerator.generateAtomNode(isEqual)
    }
}