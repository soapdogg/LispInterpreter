package com.soapdogg.lispInterpreter.datamodels

data class ProcessedTokensResult (
    val literalAtoms: List<String>,
    val openCount: Int,
    val closeCount: Int,
    val numericAtomsSum: Int,
    val numericAtomsCount: Int
)