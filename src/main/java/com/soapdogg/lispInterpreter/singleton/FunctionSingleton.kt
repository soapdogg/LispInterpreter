package com.soapdogg.lispInterpreter.singleton

import com.soapdogg.lispInterpreter.functions.*

enum class FunctionSingleton {
    INSTANCE;

    val carFunction: CarFunction = CarFunction(
        ValueRetrieverSingleton.INSTANCE.listValueRetriever,
        EvaluatorSingleton.INSTANCE.nodeEvaluator
    )
    val cdrFunction: CdrFunction = CdrFunction(
        ValueRetrieverSingleton.INSTANCE.listValueRetriever,
        EvaluatorSingleton.INSTANCE.nodeEvaluator,
        GeneratorSingleton.INSTANCE.nodeGenerator
    )
    val condFunction: CondFunction = CondFunction(
        AsserterSingleton.INSTANCE.condFunctionParameterAsserter,
        EvaluatorSingleton.INSTANCE.nodeEvaluator,
        ComparatorSingleton.INSTANCE.nodeValueComparator
    )
    val consFunction: ConsFunction = ConsFunction(
        EvaluatorSingleton.INSTANCE.nodeEvaluator,
        GeneratorSingleton.INSTANCE.nodeGenerator
    )
}