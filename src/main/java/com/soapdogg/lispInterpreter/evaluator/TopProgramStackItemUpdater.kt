package com.soapdogg.lispInterpreter.evaluator

import com.soapdogg.lispInterpreter.datamodels.Stack
import com.soapdogg.lispInterpreter.datamodels.ProgramStackItem
import com.soapdogg.lispInterpreter.generator.ProgramStackItemGenerator

class TopProgramStackItemUpdater (
    private val programStackItemGenerator: ProgramStackItemGenerator
) {

    fun updateTopProgramStackItemToNextChild(
        programStack: Stack<ProgramStackItem>
    ) {
        if (programStack.isNotEmpty()) {
            val head = programStack.pop()
            val updatedHead = programStackItemGenerator.generateProgramStackItemFromExisting(
                head
            )
            programStack.push(
                updatedHead
            )
        }
    }
}