package com.soapdogg.lispInterpreter.singleton

import com.soapdogg.lispInterpreter.functions.*

enum class FunctionSingleton {
    INSTANCE;

    val condFunction: CondFunction = CondFunction(
        AsserterSingleton.INSTANCE.condFunctionParameterAsserter,
        EvaluatorSingleton.INSTANCE.nodeEvaluator,
        ComparatorSingleton.INSTANCE.nodeValueComparator
    )

}