package com.soapdogg.lispInterpreter.singleton

import com.soapdogg.lispInterpreter.evaluator.*

enum class EvaluatorSingleton {
    INSTANCE;

    private val topProgramStackItemCreator = TopProgramStackItemCreator(
        GeneratorSingleton.INSTANCE.programStackItemGenerator
    )

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
        topProgramStackItemCreator
    )

    private val condProgramStackItemEvaluator = CondProgramStackItemEvaluator(
        topProgramStackItemUpdater,
        condChildStackItemBuilder
    )

    private val quoteFunctionEvaluator = QuoteFunctionEvaluator(
        stackUpdater
    )

    private val nodeEvaluatorIterative = NodeEvaluatorIterative(
        topProgramStackItemCreator,
        DeterminerSingleton.INSTANCE.functionLengthDeterminer,
        FunctionSingleton.INSTANCE.functionMap,
        stackUpdater,
        condProgramStackItemEvaluator,
        quoteFunctionEvaluator,
        builtInFunctionEvaluator
    )
    val programEvaluator: ProgramEvaluator = ProgramEvaluator(
        AsserterSingleton.INSTANCE.atomRootNodeAsserter,
        nodeEvaluatorIterative
    )
}