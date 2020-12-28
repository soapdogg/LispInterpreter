package com.soapdogg.lispInterpreter.evaluator

import com.soapdogg.lispInterpreter.datamodels.ProgramStackItem
import com.soapdogg.lispInterpreter.exceptions.NotAListException
import java.util.*

class CondProgramStackItemEvaluator(
    private val topProgramStackItemUpdater: TopProgramStackItemUpdater,
    private val condChildStackItemBuilder: CondChildStackItemBuilder
) {

    fun evaluateCondProgramStackItem(
        top: ProgramStackItem,
        programStack: Stack<ProgramStackItem>
    ): Stack<ProgramStackItem> {
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
            else -> {
                throw NotAListException("Error! None of the conditions in the COND function evaluated to true.\n")
            }
        }
    }
}