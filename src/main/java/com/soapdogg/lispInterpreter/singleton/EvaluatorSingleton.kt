package com.soapdogg.lispInterpreter.singleton

import com.soapdogg.lispInterpreter.evaluator.NodeEvaluatorIterative
import com.soapdogg.lispInterpreter.evaluator.ProgramEvaluator
import com.soapdogg.lispInterpreter.evaluator.TopProgramStackItemUpdater

enum class EvaluatorSingleton {
    INSTANCE;

    private val topProgramStackItemUpdater = TopProgramStackItemUpdater(
        GeneratorSingleton.INSTANCE.programStackItemGenerator
    )

    private val nodeEvaluatorIterative = NodeEvaluatorIterative(
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