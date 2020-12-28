package com.soapdogg.lispInterpreter.datamodels

data class ProgramStackItem(
    val functionExpressionNode: ExpressionListNode,
    val currentParameterIndex: Int,
    val variableMap: Map<String, NodeV2>,
    val functionName: String
)
