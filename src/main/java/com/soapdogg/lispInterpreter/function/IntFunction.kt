package com.soapdogg.lispInterpreter.function

import com.soapdogg.lispInterpreter.datamodels.AtomNode
import com.soapdogg.lispInterpreter.datamodels.NodeV2
import com.soapdogg.lispInterpreter.determiner.NumericStringDeterminer
import com.soapdogg.lispInterpreter.generator.NodeGenerator
import java.util.*

class IntFunction (
    private val numericStringDeterminer: NumericStringDeterminer,
    private val nodeGenerator: NodeGenerator
): Function {

    override fun evaluate(
        params: Stack<NodeV2>,
        variableMap: Map<String, NodeV2>
    ): NodeV2 {
        val first = params.pop()
        return if (first is AtomNode) {
            val value = first.value
            val isNumeric = numericStringDeterminer.isStringNumeric(value)
            nodeGenerator.generateAtomNode(isNumeric)
        } else {
            nodeGenerator.generateAtomNode(false)
        }
    }
}