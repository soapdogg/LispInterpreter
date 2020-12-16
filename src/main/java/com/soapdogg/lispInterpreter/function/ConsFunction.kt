package com.soapdogg.lispInterpreter.function

import com.soapdogg.lispInterpreter.datamodels.AtomNode
import com.soapdogg.lispInterpreter.datamodels.ExpressionListNode
import com.soapdogg.lispInterpreter.datamodels.NodeV2
import com.soapdogg.lispInterpreter.generator.NodeGenerator
import java.util.*

class ConsFunction(
    private val nodeGenerator: NodeGenerator
): Function {
    override fun evaluate(
        params: Stack<NodeV2>,
        variableMap: Map<String, NodeV2>
    ): NodeV2 {
        var evaluatedAddress = params.pop()
        var evaluatedData = params.pop()

        if (evaluatedAddress is AtomNode) {
            evaluatedAddress = variableMap.getOrDefault(evaluatedAddress.value, evaluatedAddress)
        }

        if (evaluatedData is AtomNode) {
            evaluatedData = variableMap.getOrDefault(evaluatedData.value, evaluatedData)
        }

        return if (evaluatedData is ExpressionListNode) {
            nodeGenerator.generateExpressionListNode(
                listOf(evaluatedAddress) + evaluatedData.children
            )
        } else {
            nodeGenerator.generateExpressionListNode(
                listOf(evaluatedAddress, evaluatedData)
            )
        }
    }
}