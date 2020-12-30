package com.soapdogg.lispInterpreter.evaluator

import com.soapdogg.lispInterpreter.datamodels.Stack
import com.soapdogg.lispInterpreter.datamodels.ProgramStackItem
import com.soapdogg.lispInterpreter.exceptions.NotAListException

class CondFunctionEvaluator(
    private val topProgramStackItemUpdater: TopProgramStackItemUpdater,
    private val condChildStackItemBuilder: CondChildStackItemBuilder
) {

    fun evaluateCondProgramStackItem(
        top: ProgramStackItem,
        programStack: Stack<ProgramStackItem>
    ) {
        programStack.push(top)
        when (top.currentParameterIndex) {
            0 -> {
                topProgramStackItemUpdater.updateTopProgramStackItemToNextChild(
                    programStack
                )
                condChildStackItemBuilder.buildCondChildStackItems(
                    top,
                    programStack
                )
            }
            else -> {
                throw NotAListException("Error! None of the conditions in the COND function evaluated to true.\n")
            }
        }
    }
}