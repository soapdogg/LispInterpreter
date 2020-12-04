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




        functionV2Map = HashMap()
        functionV2Map[FunctionNameConstants.ATOM] = FunctionSingleton.INSTANCE.atomFunction
        functionV2Map[FunctionNameConstants.CAR] = FunctionSingleton.INSTANCE.carFunction
        functionV2Map[FunctionNameConstants.CDR] = FunctionSingleton.INSTANCE.cdrFunction
        functionV2Map[FunctionNameConstants.COND] = FunctionSingleton.INSTANCE.condFunction
        functionV2Map[FunctionNameConstants.CONS] = FunctionSingleton.INSTANCE.consFunction
        functionV2Map[FunctionNameConstants.EQ] = FunctionSingleton.INSTANCE.eqFunction
        functionV2Map[FunctionNameConstants.GREATER] = FunctionSingleton.INSTANCE.greaterFunction
        functionV2Map[FunctionNameConstants.INT] = FunctionSingleton.INSTANCE.intFunction
        functionV2Map[FunctionNameConstants.LESS] = FunctionSingleton.INSTANCE.lessFunction
        functionV2Map[FunctionNameConstants.MINUS] = FunctionSingleton.INSTANCE.minusFunction
        functionV2Map[FunctionNameConstants.NULL] = FunctionSingleton.INSTANCE.nullFunction
        functionV2Map[FunctionNameConstants.PLUS] = FunctionSingleton.INSTANCE.plusFunction
        functionV2Map[FunctionNameConstants.QUOTE] = FunctionSingleton.INSTANCE.quoteFunction
        functionV2Map[FunctionNameConstants.TIMES] = FunctionSingleton.INSTANCE.timesFunction
    }
}