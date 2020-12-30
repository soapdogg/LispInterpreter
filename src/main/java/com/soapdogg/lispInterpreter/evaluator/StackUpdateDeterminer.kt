package com.soapdogg.lispInterpreter.evaluator

import com.soapdogg.lispInterpreter.datamodels.ExpressionListNode
import com.soapdogg.lispInterpreter.datamodels.MyStack
import com.soapdogg.lispInterpreter.datamodels.NodeV2
import com.soapdogg.lispInterpreter.datamodels.ProgramStackItem
import java.util.*

class StackUpdateDeterminer (
    private val topProgramStackItemCreator: TopProgramStackItemCreator,
    private val postEvaluationStackUpdater: PostEvaluationStackUpdater
){

    fun determineHowToUpdateStacks(
        node: NodeV2,
        variableMap: Map<String, NodeV2>,
        evalStack: Stack<NodeV2>,
        programStack: MyStack<ProgramStackItem>
    ) {
        if (node is ExpressionListNode) {
            if (node.children.size > 1) {
                topProgramStackItemCreator.createTopProgramStackItem(
                    node,
                    variableMap,
                    programStack
                )
            } else {
                postEvaluationStackUpdater.updateStacksAfterEvaluation(
                    node.children[0],
                    variableMap,
                    evalStack,
                    programStack
                )
            }
        }
        else {
            postEvaluationStackUpdater.updateStacksAfterEvaluation(
                node,
                variableMap,
                evalStack,
                programStack
            )
        }
    }
}