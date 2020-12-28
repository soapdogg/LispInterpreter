package com.soapdogg.lispInterpreter.evaluator

import com.soapdogg.lispInterpreter.datamodels.AtomNode
import com.soapdogg.lispInterpreter.datamodels.NodeV2
import com.soapdogg.lispInterpreter.datamodels.ProgramStackItem
import com.soapdogg.lispInterpreter.datamodels.Stacks
import java.util.*

class PostEvaluationStackUpdater(
    private val topProgramStackItemUpdater: TopProgramStackItemUpdater
) {

    fun updateStacksAfterEvaluation(
        evaluatedNode: NodeV2,
        variableMap: Map<String, NodeV2>,
        evalStack: Stack<NodeV2>,
        programStack: Stack<ProgramStackItem>
    ): Stacks {
        val nodeToPush = if (evaluatedNode is AtomNode) {
            variableMap.getOrDefault(evaluatedNode.value, evaluatedNode)
        } else {
            evaluatedNode
        }
        evalStack.push(nodeToPush)
        val updatedProgramStack = topProgramStackItemUpdater.updateTopProgramStackItemToNextChild(
            programStack
        )
        return Stacks(
            evalStack,
            updatedProgramStack
        )
    }
}