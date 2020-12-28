package com.soapdogg.lispInterpreter.evaluator

import com.soapdogg.lispInterpreter.datamodels.ProgramStackItem
import com.soapdogg.lispInterpreter.generator.ProgramStackItemGenerator
import java.util.*

class TopProgramStackItemUpdater (
    private val programStackItemGenerator: ProgramStackItemGenerator
) {

    fun updateTopProgramStackItemToNextChild(
        programStack: Stack<ProgramStackItem>
    ) {
        if (programStack.isNotEmpty()) {
            val head = programStack.pop()
            val updatedHead = programStackItemGenerator.generateProgramStackItem(
                head.functionExpressionNode,
                head.currentParameterIndex + 1,
                head.variableMap
            )
            programStack.push(
                updatedHead
            )
        }
    }
}