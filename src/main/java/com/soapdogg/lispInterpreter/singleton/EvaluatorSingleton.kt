package com.soapdogg.lispInterpreter.singleton

import com.soapdogg.lispInterpreter.evaluator.AtomNodeEvaluator
import com.soapdogg.lispInterpreter.evaluator.NodeEvaluator
import com.soapdogg.lispInterpreter.evaluator.ProgramEvaluator

enum class EvaluatorSingleton {
    INSTANCE;

    val atomNodeEvaluator: AtomNodeEvaluator = AtomNodeEvaluator()
    val nodeEvaluator: NodeEvaluator = NodeEvaluator(
        atomNodeEvaluator,
        AsserterSingleton.INSTANCE.functionLengthAsserter,
        DeterminerSingleton.INSTANCE.numericStringDeterminer,
        ValueRetrieverSingleton.INSTANCE.listValueRetriever,
        PrinterSingleton.INSTANCE.listNotationPrinter,
        ComparatorSingleton.INSTANCE.nodeValueComparator,
        ValueRetrieverSingleton.INSTANCE.numericValueRetriever,
        GeneratorSingleton.INSTANCE.nodeGenerator,
        AsserterSingleton.INSTANCE.condFunctionParameterAsserter
    )
    val programEvaluator: ProgramEvaluator = ProgramEvaluator(
        AsserterSingleton.INSTANCE.atomRootNodeAsserter,
        nodeEvaluator
    )
}