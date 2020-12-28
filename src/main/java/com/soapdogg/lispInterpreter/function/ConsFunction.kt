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
        val first = params.pop()
        val second = params.pop()

        return if (second is ExpressionListNode) {
            nodeGenerator.generateExpressionListNode(
                listOf(first) + second.children
            )
        } else {
            nodeGenerator.generateExpressionListNode(
                listOf(first, second)
            )
        }
    }
}