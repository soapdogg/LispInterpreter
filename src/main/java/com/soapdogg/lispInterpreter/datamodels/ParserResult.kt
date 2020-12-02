package com.soapdogg.lispInterpreter.datamodels

data class ParserResult(
    val resultingNode: ExpressionListNode,
    val nextIndex: Int
)
