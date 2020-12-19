package com.soapdogg.lispInterpreter.singleton

import com.soapdogg.lispInterpreter.evaluator.*

enum class EvaluatorSingleton {
    INSTANCE;

    private val topProgramStackItemUpdater = TopProgramStackItemUpdater(
        GeneratorSingleton.INSTANCE.programStackItemGenerator
    )

    private val stackUpdater = PostEvaluationStackUpdater(
        topProgramStackItemUpdater
    )

    private val builtInFunctionEvaluator = BuiltInFunctionEvaluator(
        FunctionSingleton.INSTANCE.functionMap,
        stackUpdater
    )

    private val condChildStackItemBuilder = CondChildStackItemBuilder(
        GeneratorSingleton.INSTANCE.nodeGenerator,
        GeneratorSingleton.INSTANCE.programStackItemGenerator
    )

    private val condProgramStackItemEvaluator = CondProgramStackItemEvaluator(
        topProgramStackItemUpdater,
        condChildStackItemBuilder
    )

    private val nodeEvaluatorIterative = NodeEvaluatorIterative(
        GeneratorSingleton.INSTANCE.programStackItemGenerator,
        DeterminerSingleton.INSTANCE.functionLengthDeterminer,
        FunctionSingleton.INSTANCE.functionMap,
        topProgramStackItemUpdater,
        stackUpdater,
        condProgramStackItemEvaluator,
        builtInFunctionEvaluator
    )
    val programEvaluator: ProgramEvaluator = ProgramEvaluator(
        AsserterSingleton.INSTANCE.atomRootNodeAsserter,
        nodeEvaluatorIterative
    )
}