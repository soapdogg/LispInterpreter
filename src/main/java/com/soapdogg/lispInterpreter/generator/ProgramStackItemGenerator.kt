package com.soapdogg.lispInterpreter.generator

import com.soapdogg.lispInterpreter.datamodels.AtomNode
import com.soapdogg.lispInterpreter.datamodels.ExpressionListNode
import com.soapdogg.lispInterpreter.datamodels.NodeV2
import com.soapdogg.lispInterpreter.datamodels.ProgramStackItem

class ProgramStackItemGenerator {

    fun generateProgramStackItemFromScratch(
        functionExpressionNode: ExpressionListNode,
        variableMap: Map<String, NodeV2>
    ): ProgramStackItem {
        return ProgramStackItem(
            functionExpressionNode,
            0,
            variableMap,
            (functionExpressionNode.children[0] as AtomNode).value
        )
    }

    fun generateProgramStackItemFromExisting(
        existingProgramStackItem: ProgramStackItem
    ): ProgramStackItem {
        return ProgramStackItem(
            existingProgramStackItem.functionExpressionNode,
            existingProgramStackItem.currentParameterIndex + 1,
            existingProgramStackItem.variableMap,
            existingProgramStackItem.functionName
        )
    }
}