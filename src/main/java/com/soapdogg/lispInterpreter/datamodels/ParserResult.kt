package com.soapdogg.lispInterpreter.datamodels

data class ParserResult(
    val resultingNode: Node,
    val remainingTokens: List<Token>
)