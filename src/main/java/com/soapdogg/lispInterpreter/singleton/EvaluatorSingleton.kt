package com.soapdogg.lispInterpreter.singleton

import com.soapdogg.lispInterpreter.evaluator.NodeEvaluatorIterative
import com.soapdogg.lispInterpreter.evaluator.ProgramEvaluator

enum class EvaluatorSingleton {
    INSTANCE;

    val nodeEvaluatorIterative = NodeEvaluatorIterative(
        DeterminerSingleton.INSTANCE.functionLengthDeterminer,
        FunctionSingleton.INSTANCE.functionMap
    )
    val programEvaluator: ProgramEvaluator = ProgramEvaluator(
        AsserterSingleton.INSTANCE.atomRootNodeAsserter,
        nodeEvaluatorIterative
    )
}