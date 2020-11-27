package com.soapdogg.lispInterpreter.constants


object InvalidUserDefinedNameConstants {

    @JvmField
    val InvalidNames = setOf(
        FunctionNameConstants.ATOM,
        FunctionNameConstants.CAR,
        FunctionNameConstants.CDR,
        FunctionNameConstants.COND,
        FunctionNameConstants.CONS,
        FunctionNameConstants.DEFUN,
        FunctionNameConstants.EQ,
        FunctionNameConstants.GREATER,
        FunctionNameConstants.INT,
        FunctionNameConstants.LESS,
        FunctionNameConstants.MINUS,
        FunctionNameConstants.NULL,
        FunctionNameConstants.PLUS,
        FunctionNameConstants.QUOTE,
        FunctionNameConstants.TIMES,
        ReservedValuesConstants.T,
        ReservedValuesConstants.NIL
    )
}