package com.soapdogg.lispInterpreter.singleton

import com.soapdogg.lispInterpreter.evaluator.AtomNodeEvaluator
import com.soapdogg.lispInterpreter.evaluator.NodeEvaluator
import com.soapdogg.lispInterpreter.evaluator.ProgramEvaluator

enum class EvaluatorSingleton {
    INSTANCE;

    val atomNodeEvaluator: AtomNodeEvaluator = AtomNodeEvaluator()
    val nodeEvaluator: NodeEvaluator
    val programEvaluator: ProgramEvaluator

    init {
        nodeEvaluator = NodeEvaluator(
            atomNodeEvaluator,
            DeterminerSingleton.INSTANCE.userDefinedFunctionNameDeterminer,
            AsserterSingleton.INSTANCE.functionLengthAsserter,
            ConverterSingleton.INSTANCE.nodeConverter
        )
        programEvaluator = ProgramEvaluator(
            AsserterSingleton.INSTANCE.atomRootNodeAsserter,
            nodeEvaluator
        )
    }
}