package com.soapdogg.lispInterpreter.evaluator

import com.soapdogg.lispInterpreter.datamodels.MyStack
import com.soapdogg.lispInterpreter.datamodels.NodeV2
import com.soapdogg.lispInterpreter.datamodels.ProgramStackItem
import java.util.*

class QuoteFunctionEvaluator(
    private val postEvaluationStackUpdater: PostEvaluationStackUpdater
) {

    fun evaluateQuoteFunction(
        top: ProgramStackItem,
        evalStack: Stack<NodeV2>,
        programStack: MyStack<ProgramStackItem>
    ) {
        val quoteExprNode = top.functionExpressionNode
        val quoted = quoteExprNode.children[1]
        return postEvaluationStackUpdater.updateStacksAfterEvaluation(
            quoted,
            top.variableMap,
            evalStack,
            programStack
        )
    }
}