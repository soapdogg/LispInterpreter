package com.soapdogg.lispInterpreter.function

import com.soapdogg.lispInterpreter.constants.ReservedValuesConstants
import com.soapdogg.lispInterpreter.datamodels.AtomNode
import com.soapdogg.lispInterpreter.datamodels.NodeV2
import com.soapdogg.lispInterpreter.generator.NodeGenerator
import com.soapdogg.lispInterpreter.valueretriver.AtomNodeValueRetriever
import java.util.*

class NullFunction(
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
            if (evaluatedAtom is AtomNode) {
                val value = evaluatedAtom.value
                val isNil = value == ReservedValuesConstants.NIL
                nodeGenerator.generateAtomNode(isNil)
            } else {
                nodeGenerator.generateAtomNode(false)
            }
        } else {
            nodeGenerator.generateAtomNode(false)
        }
    }
}