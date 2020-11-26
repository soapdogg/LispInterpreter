package com.soapdogg.lispInterpreter.datamodels

data class UserDefinedFunction(
    val formalParameters: List<String>,
    val body: Node,
    val functionName: String
)