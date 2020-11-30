package com.soapdogg.lispInterpreter.singleton

import com.soapdogg.lispInterpreter.functions.*

enum class FunctionSingleton {
    INSTANCE;

    val atomFunction: AtomFunction = AtomFunction(
        AsserterSingleton.INSTANCE.functionLengthAsserter,
        EvaluatorSingleton.INSTANCE.nodeEvaluator,
        DeterminerSingleton.INSTANCE.expressionNodeDeterminer,
        GeneratorSingleton.INSTANCE.nodeGenerator
    )
    val carFunction: CarFunction = CarFunction(
        AsserterSingleton.INSTANCE.functionLengthAsserter,
        ValueRetrieverSingleton.INSTANCE.listValueRetriever,
        EvaluatorSingleton.INSTANCE.nodeEvaluator
    )
    val cdrFunction: CdrFunction = CdrFunction(
        AsserterSingleton.INSTANCE.functionLengthAsserter,
        ValueRetrieverSingleton.INSTANCE.listValueRetriever,
        EvaluatorSingleton.INSTANCE.nodeEvaluator
    )
    val condFunction: CondFunction = CondFunction(
        AsserterSingleton.INSTANCE.condFunctionParameterAsserter,
        EvaluatorSingleton.INSTANCE.condFunctionEvaluator
    )
    val consFunction: ConsFunction = ConsFunction(
        AsserterSingleton.INSTANCE.functionLengthAsserter,
        ValueRetrieverSingleton.INSTANCE.listValueRetriever,
        EvaluatorSingleton.INSTANCE.nodeEvaluator,
        GeneratorSingleton.INSTANCE.nodeGenerator
    )
    val eqFunction: EqFunction = EqFunction(
        AsserterSingleton.INSTANCE.functionLengthAsserter,
        EvaluatorSingleton.INSTANCE.nodeEvaluator,
        ValueRetrieverSingleton.INSTANCE.atomicValueRetriever,
        ValueRetrieverSingleton.INSTANCE.listValueRetriever,
        GeneratorSingleton.INSTANCE.nodeGenerator
    )
    val greaterFunction: GreaterFunction = GreaterFunction(
        AsserterSingleton.INSTANCE.functionLengthAsserter,
        EvaluatorSingleton.INSTANCE.nodeEvaluator,
        ValueRetrieverSingleton.INSTANCE.numericValueRetriever,
        ValueRetrieverSingleton.INSTANCE.listValueRetriever,
        GeneratorSingleton.INSTANCE.nodeGenerator
    )
    val intFunction: IntFunction = IntFunction(
        AsserterSingleton.INSTANCE.functionLengthAsserter,
        EvaluatorSingleton.INSTANCE.nodeEvaluator,
        DeterminerSingleton.INSTANCE.expressionNodeDeterminer,
        ValueRetrieverSingleton.INSTANCE.atomicValueRetriever,
        DeterminerSingleton.INSTANCE.numericStringDeterminer,
        GeneratorSingleton.INSTANCE.nodeGenerator
    )
    val lessFunction: LessFunction = LessFunction(
        AsserterSingleton.INSTANCE.functionLengthAsserter,
        EvaluatorSingleton.INSTANCE.nodeEvaluator,
        ValueRetrieverSingleton.INSTANCE.numericValueRetriever,
        ValueRetrieverSingleton.INSTANCE.listValueRetriever,
        GeneratorSingleton.INSTANCE.nodeGenerator
    )
    val minusFunction: MinusFunction = MinusFunction(
        AsserterSingleton.INSTANCE.functionLengthAsserter,
        EvaluatorSingleton.INSTANCE.nodeEvaluator,
        ValueRetrieverSingleton.INSTANCE.numericValueRetriever,
        ValueRetrieverSingleton.INSTANCE.listValueRetriever,
        GeneratorSingleton.INSTANCE.nodeGenerator
    )
    val nullFunction: NullFunction = NullFunction(
        AsserterSingleton.INSTANCE.functionLengthAsserter,
        EvaluatorSingleton.INSTANCE.nodeEvaluator,
        DeterminerSingleton.INSTANCE.expressionNodeDeterminer,
        ValueRetrieverSingleton.INSTANCE.atomicValueRetriever,
        ComparatorSingleton.INSTANCE.nodeValueComparator,
        GeneratorSingleton.INSTANCE.nodeGenerator
    )
    val plusFunction: PlusFunction = PlusFunction(
        AsserterSingleton.INSTANCE.functionLengthAsserter,
        EvaluatorSingleton.INSTANCE.nodeEvaluator,
        ValueRetrieverSingleton.INSTANCE.numericValueRetriever,
        ValueRetrieverSingleton.INSTANCE.listValueRetriever,
        GeneratorSingleton.INSTANCE.nodeGenerator
    )
    val quoteFunction: QuoteFunction = QuoteFunction(
        AsserterSingleton.INSTANCE.functionLengthAsserter,
        ValueRetrieverSingleton.INSTANCE.listValueRetriever
    )
    val timesFunction: TimesFunction = TimesFunction(
        AsserterSingleton.INSTANCE.functionLengthAsserter,
        EvaluatorSingleton.INSTANCE.nodeEvaluator,
        ValueRetrieverSingleton.INSTANCE.numericValueRetriever,
        ValueRetrieverSingleton.INSTANCE.listValueRetriever,
        GeneratorSingleton.INSTANCE.nodeGenerator
    )

}