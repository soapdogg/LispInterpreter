package com.soapdogg.lispInterpreter.evaluator

import com.soapdogg.lispInterpreter.datamodels.MyStack
import com.soapdogg.lispInterpreter.datamodels.ProgramStackItem
import com.soapdogg.lispInterpreter.generator.ProgramStackItemGenerator
import java.util.*

class TopProgramStackItemUpdater (
    private val programStackItemGenerator: ProgramStackItemGenerator
) {

    fun updateTopProgramStackItemToNextChild(
        programStack: MyStack<ProgramStackItem>
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