package com.soapdogg.lispInterpreter.function

import com.soapdogg.lispInterpreter.datamodels.AtomNode
import com.soapdogg.lispInterpreter.datamodels.ExpressionListNode
import com.soapdogg.lispInterpreter.datamodels.NodeV2
import com.soapdogg.lispInterpreter.generator.NodeGenerator
import com.soapdogg.lispInterpreter.valueretriver.AtomNodeValueRetriever
import java.util.*

class AtomFunction(
    private val nodeGenerator: NodeGenerator,
    private val atomNodeValueRetriever: AtomNodeValueRetriever
): Function {

    override fun evaluate(
        params: Stack<NodeV2>,
        variableMap: Map<String, NodeV2>
    ): NodeV2 {
        val first = params.pop()
        return if (first is AtomNode) {
            val evaluatedAtom = atomNodeValueRetriever.retrieveAtomNode(
                first,
                variableMap
            )
            val isAtom = evaluatedAtom !is ExpressionListNode
            nodeGenerator.generateAtomNode(isAtom)
        } else {
            nodeGenerator.generateAtomNode(false)
        }
    }
}