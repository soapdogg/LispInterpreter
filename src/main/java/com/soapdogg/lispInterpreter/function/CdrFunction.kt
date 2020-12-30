package com.soapdogg.lispInterpreter.function

import com.soapdogg.lispInterpreter.constants.FunctionNameConstants
import com.soapdogg.lispInterpreter.datamodels.Stack
import com.soapdogg.lispInterpreter.datamodels.NodeV2
import com.soapdogg.lispInterpreter.generator.NodeGenerator
import com.soapdogg.lispInterpreter.valueretriver.ListValueRetriever

class CdrFunction(
   private val listValueRetriever: ListValueRetriever,
   private val nodeGenerator: NodeGenerator
): Function {
    override fun evaluate(
        params: Stack<NodeV2>
    ): NodeV2 {
        val first = params.pop()

        val firstExpressionListNode = listValueRetriever.retrieveListValue(
            first,
            FunctionNameConstants.CDR
        )

        return when (firstExpressionListNode.children.size) {
            1 -> {
                firstExpressionListNode.children[0]
            }
            2 -> {
                firstExpressionListNode.children[1]
            }
            else -> {
                nodeGenerator.generateExpressionListNode(
                    firstExpressionListNode.children.subList(1, firstExpressionListNode.children.size)
                )
            }
        }
    }

}