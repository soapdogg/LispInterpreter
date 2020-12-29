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

    private val postEvaluationStackUpdater = PostEvaluationStackUpdater(
        topProgramStackItemUpdater
    )

    private val builtInFunctionEvaluator = BuiltInFunctionEvaluator(
        FunctionSingleton.INSTANCE.functionMap,
        postEvaluationStackUpdater
    )

    private val condChildStackItemBuilder = CondChildStackItemBuilder(
        GeneratorSingleton.INSTANCE.nodeGenerator,
        topProgramStackItemCreator
    )

    private val condProgramStackItemEvaluator = CondFunctionEvaluator(
        topProgramStackItemUpdater,
        condChildStackItemBuilder
    )

    private val quoteFunctionEvaluator = QuoteFunctionEvaluator(
        postEvaluationStackUpdater
    )

    private val stackUpdateDeterminer = StackUpdateDeterminer(
        topProgramStackItemCreator,
        postEvaluationStackUpdater
    )

    private val condChildFunctionEvaluator = CondChildFunctionEvaluator(
        stackUpdateDeterminer
    )

    private val nodeEvaluatorIterative = NodeEvaluatorIterative(
        topProgramStackItemCreator,
        stackUpdateDeterminer,
        DeterminerSingleton.INSTANCE.functionLengthDeterminer,
        FunctionSingleton.INSTANCE.functionMap,
        postEvaluationStackUpdater,
        condProgramStackItemEvaluator,
        condChildFunctionEvaluator,
        quoteFunctionEvaluator,
        builtInFunctionEvaluator
    )
    val programEvaluator: ProgramEvaluator = ProgramEvaluator(
        AsserterSingleton.INSTANCE.atomRootNodeAsserter,
        nodeEvaluatorIterative
    )
}