package com.soapdogg.lispInterpreter.generator

import com.soapdogg.lispInterpreter.datamodels.ExpressionListNode
import com.soapdogg.lispInterpreter.datamodels.NodeV2
import com.soapdogg.lispInterpreter.datamodels.ProgramStackItem

class ProgramStackItemGenerator {

    fun generateProgramStackItem(
        functionExpressionNode: ExpressionListNode,
        currentParameterIndex: Int,
        variableMap: Map<String, NodeV2>
    ): ProgramStackItem {
        return ProgramStackItem(
            functionExpressionNode,
            currentParameterIndex,
            variableMap
        )
    }
}