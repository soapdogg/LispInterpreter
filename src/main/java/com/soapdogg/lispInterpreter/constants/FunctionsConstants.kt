package com.soapdogg.lispInterpreter.constants

import com.soapdogg.lispInterpreter.functions.LispFunctionV2
import com.soapdogg.lispInterpreter.singleton.FunctionSingleton

object FunctionsConstants {

    @JvmField
    val functionV2Map: MutableMap<String, LispFunctionV2>?

    val functionLengthMap: Map<String, Int>

    init {

        functionV2Map = HashMap()
        functionV2Map[FunctionNameConstants.COND] = FunctionSingleton.INSTANCE.condFunction

        functionLengthMap = mapOf(
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
}