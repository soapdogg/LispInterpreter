package com.soapdogg.lispInterpreter.singleton

import com.soapdogg.lispInterpreter.evaluator.AtomNodeEvaluator
import com.soapdogg.lispInterpreter.evaluator.NodeEvaluator
import com.soapdogg.lispInterpreter.evaluator.ProgramEvaluator

enum class EvaluatorSingleton {
    INSTANCE;

    val atomNodeEvaluator: AtomNodeEvaluator = AtomNodeEvaluator()
    val nodeEvaluator: NodeEvaluator = NodeEvaluator(
        atomNodeEvaluator,
        DeterminerSingleton.INSTANCE.userDefinedFunctionNameDeterminer,
        AsserterSingleton.INSTANCE.functionLengthAsserter,
        DeterminerSingleton.INSTANCE.numericStringDeterminer,
        ComparatorSingleton.INSTANCE.nodeValueComparator,
        GeneratorSingleton.INSTANCE.nodeGenerator
    )
    val programEvaluator: ProgramEvaluator = ProgramEvaluator(
        AsserterSingleton.INSTANCE.atomRootNodeAsserter,
        nodeEvaluator
    )
}