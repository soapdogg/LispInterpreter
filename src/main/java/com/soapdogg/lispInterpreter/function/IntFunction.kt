package com.soapdogg.lispInterpreter.function

import com.soapdogg.lispInterpreter.datamodels.AtomNode
import com.soapdogg.lispInterpreter.datamodels.NodeV2
import com.soapdogg.lispInterpreter.determiner.NumericStringDeterminer
import com.soapdogg.lispInterpreter.generator.NodeGenerator
import com.soapdogg.lispInterpreter.valueretriver.AtomNodeValueRetriever
import java.util.*

class IntFunction (
    private val atomNodeValueRetriever: AtomNodeValueRetriever,
    private val numericStringDeterminer: NumericStringDeterminer,
    private val nodeGenerator: NodeGenerator
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
                val isNumeric = numericStringDeterminer.isStringNumeric(value)
                nodeGenerator.generateAtomNode(isNumeric)
            } else {
                nodeGenerator.generateAtomNode(false)
            }
        } else {
            nodeGenerator.generateAtomNode(false)
        }
    }
}