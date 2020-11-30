package com.soapdogg.lispInterpreter.constants

import com.soapdogg.lispInterpreter.functions.LispFunction
import com.soapdogg.lispInterpreter.singleton.FunctionSingleton

object FunctionsConstants {
    @JvmField
    val functionMap: MutableMap<String, LispFunction>?

    init {
        functionMap = HashMap()
        functionMap[FunctionNameConstants.ATOM] = FunctionSingleton.INSTANCE.atomFunction
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
        functionMap[FunctionNameConstants.QUOTE] = FunctionSingleton.INSTANCE.quoteFunction
        functionMap[FunctionNameConstants.TIMES] = FunctionSingleton.INSTANCE.timesFunction
    }
}