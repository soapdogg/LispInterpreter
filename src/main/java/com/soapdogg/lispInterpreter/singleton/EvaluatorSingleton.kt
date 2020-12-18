package com.soapdogg.lispInterpreter.singleton

import com.soapdogg.lispInterpreter.evaluator.CondChildStackItemBuilder
import com.soapdogg.lispInterpreter.evaluator.NodeEvaluatorIterative
import com.soapdogg.lispInterpreter.evaluator.ProgramEvaluator
import com.soapdogg.lispInterpreter.evaluator.TopProgramStackItemUpdater

enum class EvaluatorSingleton {
    INSTANCE;

    private val topProgramStackItemUpdater = TopProgramStackItemUpdater(
        GeneratorSingleton.INSTANCE.programStackItemGenerator
    )

    private val condChildStackItemBuilder = CondChildStackItemBuilder(
        GeneratorSingleton.INSTANCE.nodeGenerator,
        GeneratorSingleton.INSTANCE.programStackItemGenerator
    )

    private val nodeEvaluatorIterative = NodeEvaluatorIterative(
        condChildStackItemBuilder,
        GeneratorSingleton.INSTANCE.programStackItemGenerator,
        DeterminerSingleton.INSTANCE.functionLengthDeterminer,
        FunctionSingleton.INSTANCE.functionMap,
        topProgramStackItemUpdater
    )
    val programEvaluator: ProgramEvaluator = ProgramEvaluator(
        AsserterSingleton.INSTANCE.atomRootNodeAsserter,
        nodeEvaluatorIterative
    )
}