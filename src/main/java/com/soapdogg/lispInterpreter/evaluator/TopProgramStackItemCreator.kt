package com.soapdogg.lispInterpreter.evaluator

import com.soapdogg.lispInterpreter.datamodels.ExpressionListNode
import com.soapdogg.lispInterpreter.datamodels.Stack
import com.soapdogg.lispInterpreter.datamodels.NodeV2
import com.soapdogg.lispInterpreter.datamodels.ProgramStackItem
import com.soapdogg.lispInterpreter.generator.ProgramStackItemGenerator

class TopProgramStackItemCreator(
    private val programStackItemGenerator: ProgramStackItemGenerator
) {

    fun createTopProgramStackItem(
        expressionListNode: ExpressionListNode,
        variableMap: Map<String, NodeV2>,
        programStack: Stack<ProgramStackItem>
    ) {
        val top = programStackItemGenerator.generateProgramStackItemFromScratch(
            expressionListNode,
            variableMap
        )
        programStack.push(
            top
        )
    }
}