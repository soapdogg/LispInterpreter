package com.soapdogg.lispInterpreter.constants

import com.soapdogg.lispInterpreter.functions.LispFunction
import com.soapdogg.lispInterpreter.functions.LispFunctionV2
import com.soapdogg.lispInterpreter.singleton.FunctionSingleton

object FunctionsConstants {
    @JvmField
    val functionMap: MutableMap<String, LispFunction>?

    @JvmField
    val functionV2Map: MutableMap<String, LispFunctionV2>?

    init {
        functionMap = HashMap()
        functionMap[FunctionNameConstants.CAR] = FunctionSingleton.INSTANCE.carFunction
        functionMap[FunctionNameConstants.CDR] = FunctionSingleton.INSTANCE.cdrFunction
        functionMap[FunctionNameConstants.COND] = FunctionSingleton.INSTANCE.condFunction
        functionMap[FunctionNameConstants.CONS] = FunctionSingleton.INSTANCE.consFunction
        functionMap[FunctionNameConstants.EQ] = FunctionSingleton.INSTANCE.eqFunction
        functionMap[FunctionNameConstants.GREATER] = FunctionSingleton.INSTANCE.greaterFunction
        functionMap[FunctionNameConstants.INT] = FunctionSingleton.INSTANCE.intFunction
        functionMap[FunctionNameConstants.LESS] = FunctionSingleton.INSTANCE.lessFunction
        functionMap[FunctionNameConstants.MINUS] = FunctionSingleton.INSTANCE.minusFunction
        functionMap[FunctionNameConstants.NULL] = FunctionSingleton.INSTANCE.nullFunction
        functionMap[FunctionNameConstants.PLUS] = FunctionSingleton.INSTANCE.plusFunction
        functionMap[FunctionNameConstants.TIMES] = FunctionSingleton.INSTANCE.timesFunction

        functionV2Map = HashMap()
        functionV2Map[FunctionNameConstants.ATOM] = FunctionSingleton.INSTANCE.atomFunction
        functionV2Map[FunctionNameConstants.QUOTE] = FunctionSingleton.INSTANCE.quoteFunction
    }
}