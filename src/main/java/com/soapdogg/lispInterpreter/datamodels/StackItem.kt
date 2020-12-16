package com.soapdogg.lispInterpreter.datamodels

data class StackItem(
    val expressionListNode: ExpressionListNode,
    val currentChildIndex: Int,
    val variableMap: MutableMap<String, NodeV2>
)
