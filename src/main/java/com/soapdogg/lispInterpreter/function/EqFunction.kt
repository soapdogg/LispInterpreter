package com.soapdogg.lispInterpreter.function

import com.soapdogg.lispInterpreter.datamodels.MyStack
import com.soapdogg.lispInterpreter.datamodels.NodeV2
import com.soapdogg.lispInterpreter.generator.NodeGenerator
import java.util.*

class EqFunction(
    private val nodeGenerator: NodeGenerator
): Function {
    override fun evaluate(
        params: MyStack<NodeV2>
    ): NodeV2 {
        val first = params.pop()
        val second = params.pop()
        val isEqual = first == second
        return nodeGenerator.generateAtomNode(isEqual)
    }
}