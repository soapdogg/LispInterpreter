package com.soapdogg.lispInterpreter.function

import com.soapdogg.lispInterpreter.datamodels.ExpressionListNode
import com.soapdogg.lispInterpreter.datamodels.NodeV2
import com.soapdogg.lispInterpreter.generator.NodeGenerator
import java.util.*

class ConsFunction(
    private val nodeGenerator: NodeGenerator
): Function {
    override fun evaluate(
        params: Stack<NodeV2>
    ): NodeV2 {
        val evaluatedAddress = params.pop()
        val evaluatedData = params.pop()

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