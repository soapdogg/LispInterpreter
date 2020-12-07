package com.soapdogg.lispInterpreter.singleton

import com.soapdogg.lispInterpreter.functions.*

enum class FunctionSingleton {
    INSTANCE;

    val atomFunction: AtomFunction = AtomFunction(
        EvaluatorSingleton.INSTANCE.nodeEvaluator,
        GeneratorSingleton.INSTANCE.nodeGenerator
    )
    val carFunction: CarFunction = CarFunction(
        ValueRetrieverSingleton.INSTANCE.listValueRetriever,
        EvaluatorSingleton.INSTANCE.nodeEvaluator
    )
    val cdrFunction: CdrFunction = CdrFunction(
        ValueRetrieverSingleton.INSTANCE.listValueRetriever,
        EvaluatorSingleton.INSTANCE.nodeEvaluator,
        GeneratorSingleton.INSTANCE.nodeGenerator
    )
    val condFunction: CondFunction = CondFunction(
        AsserterSingleton.INSTANCE.condFunctionParameterAsserter,
        EvaluatorSingleton.INSTANCE.nodeEvaluator,
        ComparatorSingleton.INSTANCE.nodeValueComparator
    )
    val consFunction: ConsFunction = ConsFunction(
        EvaluatorSingleton.INSTANCE.nodeEvaluator,
        GeneratorSingleton.INSTANCE.nodeGenerator
    )
    val eqFunction: EqFunction = EqFunction(
        EvaluatorSingleton.INSTANCE.nodeEvaluator,
        PrinterSingleton.INSTANCE.listNotationPrinter,
        GeneratorSingleton.INSTANCE.nodeGenerator
    )
    val greaterFunction: GreaterFunction = GreaterFunction(
        EvaluatorSingleton.INSTANCE.nodeEvaluator,
        ValueRetrieverSingleton.INSTANCE.numericValueRetriever,
        GeneratorSingleton.INSTANCE.nodeGenerator
    )
    val intFunction: IntFunction = IntFunction(
        EvaluatorSingleton.INSTANCE.nodeEvaluator,
        DeterminerSingleton.INSTANCE.numericStringDeterminer,
        GeneratorSingleton.INSTANCE.nodeGenerator
    )
    val lessFunction: LessFunction = LessFunction(
        EvaluatorSingleton.INSTANCE.nodeEvaluator,
        ValueRetrieverSingleton.INSTANCE.numericValueRetriever,
        GeneratorSingleton.INSTANCE.nodeGenerator
    )
    val minusFunction: MinusFunction = MinusFunction(
        EvaluatorSingleton.INSTANCE.nodeEvaluator,
        ValueRetrieverSingleton.INSTANCE.numericValueRetriever,
        GeneratorSingleton.INSTANCE.nodeGenerator
    )
    val nullFunction: NullFunction = NullFunction(
        EvaluatorSingleton.INSTANCE.nodeEvaluator,
        ComparatorSingleton.INSTANCE.nodeValueComparator,
        GeneratorSingleton.INSTANCE.nodeGenerator
    )
    val plusFunction: PlusFunction = PlusFunction(
        EvaluatorSingleton.INSTANCE.nodeEvaluator,
        ValueRetrieverSingleton.INSTANCE.numericValueRetriever,
        GeneratorSingleton.INSTANCE.nodeGenerator
    )
    val quoteFunction: QuoteFunction = QuoteFunction()
    val timesFunction: TimesFunction = TimesFunction(
        EvaluatorSingleton.INSTANCE.nodeEvaluator,
        ValueRetrieverSingleton.INSTANCE.numericValueRetriever,
        GeneratorSingleton.INSTANCE.nodeGenerator
    )

}