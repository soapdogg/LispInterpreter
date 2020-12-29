package com.soapdogg.lispInterpreter.evaluator

import com.soapdogg.lispInterpreter.datamodels.NodeV2
import com.soapdogg.lispInterpreter.datamodels.ProgramStackItem
import java.util.*

class UnfinishedProgramStackItemEvaluator(
    private val stackUpdateDeterminer: StackUpdateDeterminer
) {

    fun evaluateUnfinishedProgramStackItem(
        top: ProgramStackItem,
        evalStack: Stack<NodeV2>,
        programStack: Stack<ProgramStackItem>
    ) {
        val nthChild = top.functionExpressionNode.children[top.currentParameterIndex]
        programStack.push(top)
        stackUpdateDeterminer.determineHowToUpdateStacks(
            nthChild,
            top.variableMap,
            evalStack,
            programStack
        )
    }
}