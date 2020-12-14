package com.soapdogg.lispInterpreter.datamodels

data class StackItem(
    val node: NodeV2,
    val childrenIndex: Int
)
