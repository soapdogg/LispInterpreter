package com.soapdogg.lispInterpreter.function

import com.soapdogg.lispInterpreter.constants.ReservedValuesConstants
import com.soapdogg.lispInterpreter.datamodels.AtomNode
import com.soapdogg.lispInterpreter.datamodels.Stack
import com.soapdogg.lispInterpreter.datamodels.NodeV2
import com.soapdogg.lispInterpreter.generator.NodeGenerator

class NullFunction(
    private val nodeGenerator: NodeGenerator
): Function {

    override fun evaluate(
        params: Stack<NodeV2>
    ): NodeV2 {
        val first = params.pop()
        return if (first is AtomNode) {
            val value = first.value
            val isNil = value == ReservedValuesConstants.NIL
            nodeGenerator.generateAtomNode(isNil)
        } else {
            nodeGenerator.generateAtomNode(false)
        }
    }
}