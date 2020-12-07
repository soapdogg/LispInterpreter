package com.soapdogg.lispInterpreter.constants

object FunctionsConstants {

    val functionLengthMap: Map<String, Int> = mapOf(
        Pair(FunctionNameConstants.ATOM, FunctionLengthConstants.TWO),
        Pair(FunctionNameConstants.CAR, FunctionLengthConstants.TWO),
        Pair(FunctionNameConstants.CDR, FunctionLengthConstants.TWO),
        Pair(FunctionNameConstants.CONS, FunctionLengthConstants.THREE),
        Pair(FunctionNameConstants.EQ, FunctionLengthConstants.THREE),
        Pair(FunctionNameConstants.GREATER, FunctionLengthConstants.THREE),
        Pair(FunctionNameConstants.INT, FunctionLengthConstants.TWO),
        Pair(FunctionNameConstants.LESS, FunctionLengthConstants.THREE),
        Pair(FunctionNameConstants.MINUS, FunctionLengthConstants.THREE),
        Pair(FunctionNameConstants.NULL, FunctionLengthConstants.TWO),
        Pair(FunctionNameConstants.PLUS, FunctionLengthConstants.THREE),
        Pair(FunctionNameConstants.QUOTE, FunctionLengthConstants.TWO),
        Pair(FunctionNameConstants.TIMES, FunctionLengthConstants.THREE)
    )
}