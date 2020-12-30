package com.soapdogg.lispInterpreter.evaluator

import com.soapdogg.lispInterpreter.datamodels.MyStack
import com.soapdogg.lispInterpreter.datamodels.NodeV2
import com.soapdogg.lispInterpreter.datamodels.ProgramStackItem

class UnfinishedProgramStackItemEvaluator(
    private val stackUpdateDeterminer: StackUpdateDeterminer
) {

    fun evaluateUnfinishedProgramStackItem(
        top: ProgramStackItem,
        evalStack: MyStack<NodeV2>,
        programStack: MyStack<ProgramStackItem>
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