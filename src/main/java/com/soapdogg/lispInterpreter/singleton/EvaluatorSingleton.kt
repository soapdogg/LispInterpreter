package com.soapdogg.lispInterpreter.singleton

import com.soapdogg.lispInterpreter.evaluator.AtomNodeEvaluator
import com.soapdogg.lispInterpreter.evaluator.CondFunctionEvaluator
import com.soapdogg.lispInterpreter.evaluator.NodeEvaluator
import com.soapdogg.lispInterpreter.evaluator.ProgramEvaluator

enum class EvaluatorSingleton {
    INSTANCE;

    val atomNodeEvaluator: AtomNodeEvaluator = AtomNodeEvaluator()
    val nodeEvaluator: NodeEvaluator
    val condFunctionEvaluator: CondFunctionEvaluator
    val programEvaluator: ProgramEvaluator

    init {
        nodeEvaluator = NodeEvaluator(
            atomNodeEvaluator,
            DeterminerSingleton.INSTANCE.expressionNodeDeterminer,
            DeterminerSingleton.INSTANCE.userDefinedFunctionNameDeterminer,
            AsserterSingleton.INSTANCE.functionLengthAsserter
        )
        condFunctionEvaluator = CondFunctionEvaluator(
            ValueRetrieverSingleton.INSTANCE.listValueRetriever,
            nodeEvaluator,
            ComparatorSingleton.INSTANCE.nodeValueComparator
        )
        programEvaluator = ProgramEvaluator(
            DeterminerSingleton.INSTANCE.expressionNodeDeterminer,
            AsserterSingleton.INSTANCE.atomRootNodeAsserter,
            nodeEvaluator
        )
    }
}