package com.soapdogg.lispInterpreter.evaluator

import com.soapdogg.lispInterpreter.constants.FunctionNameConstants
import com.soapdogg.lispInterpreter.datamodels.ProgramStackItem
import com.soapdogg.lispInterpreter.exceptions.NotAListException
import java.util.*

class CondProgramStackItemEvaluator(
    private val topProgramStackItemUpdater: TopProgramStackItemUpdater,
    private val condChildStackItemBuilder: CondChildStackItemBuilder
) {

    fun evaluateCondProgramStackItem(
        firstChildValue: String,
        top: ProgramStackItem,
        programStack: Stack<ProgramStackItem>
    ): Stack<ProgramStackItem> {
        if (firstChildValue == FunctionNameConstants.COND) {
            programStack.push(top)
            when (top.currentParameterIndex) {
                0 -> {
                    val updatedProgramStack = topProgramStackItemUpdater.updateTopProgramStackItemToNextChild(
                        programStack
                    )
                    return condChildStackItemBuilder.buildCondChildStackItems(
                        top,
                        updatedProgramStack
                    )
                }
                2 -> {
                    programStack.pop()
                    return topProgramStackItemUpdater.updateTopProgramStackItemToNextChild(
                        programStack
                    )
                }
                else -> {
                    throw NotAListException("Error! None of the conditions in the COND function evaluated to true.\n")
                }
            }
        }
        return programStack
    }
}