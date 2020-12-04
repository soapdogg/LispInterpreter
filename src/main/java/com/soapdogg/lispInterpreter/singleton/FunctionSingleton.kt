package com.soapdogg.lispInterpreter.singleton

import com.soapdogg.lispInterpreter.functions.*

enum class FunctionSingleton {
    INSTANCE;

    val atomFunction: AtomFunction = AtomFunction(
        AsserterSingleton.INSTANCE.functionLengthAsserter,
        EvaluatorSingleton.INSTANCE.nodeEvaluator,
        GeneratorSingleton.INSTANCE.nodeGenerator,
        ConverterSingleton.INSTANCE.nodeConverter
    )
    val carFunction: CarFunction = CarFunction(
        AsserterSingleton.INSTANCE.functionLengthAsserter,
        ValueRetrieverSingleton.INSTANCE.listValueRetriever,
        EvaluatorSingleton.INSTANCE.nodeEvaluator,
        ConverterSingleton.INSTANCE.nodeConverter
    )
    val cdrFunction: CdrFunction = CdrFunction(
        AsserterSingleton.INSTANCE.functionLengthAsserter,
        ValueRetrieverSingleton.INSTANCE.listValueRetriever,
        EvaluatorSingleton.INSTANCE.nodeEvaluator,
        ConverterSingleton.INSTANCE.nodeConverter
    )
    val condFunction: CondFunction = CondFunction(
        ConverterSingleton.INSTANCE.nodeConverter,
        AsserterSingleton.INSTANCE.condFunctionParameterAsserter,
        EvaluatorSingleton.INSTANCE.condFunctionEvaluator
    )
    val consFunction: ConsFunction = ConsFunction(
        AsserterSingleton.INSTANCE.functionLengthAsserter,
        ValueRetrieverSingleton.INSTANCE.listValueRetriever,
        EvaluatorSingleton.INSTANCE.nodeEvaluator,
        GeneratorSingleton.INSTANCE.nodeGenerator,
        ConverterSingleton.INSTANCE.nodeConverter
    )
    val eqFunction: EqFunction = EqFunction(
        AsserterSingleton.INSTANCE.functionLengthAsserter,
        EvaluatorSingleton.INSTANCE.nodeEvaluator,
        ValueRetrieverSingleton.INSTANCE.atomicValueRetriever,
        ValueRetrieverSingleton.INSTANCE.listValueRetriever,
        GeneratorSingleton.INSTANCE.nodeGenerator,
        ConverterSingleton.INSTANCE.nodeConverter
    )
    val greaterFunction: GreaterFunction = GreaterFunction(
        ConverterSingleton.INSTANCE.nodeConverter,
        AsserterSingleton.INSTANCE.functionLengthAsserter,
        EvaluatorSingleton.INSTANCE.nodeEvaluator,
        ValueRetrieverSingleton.INSTANCE.numericValueRetriever,
        ValueRetrieverSingleton.INSTANCE.listValueRetriever,
        GeneratorSingleton.INSTANCE.nodeGenerator
    )
    val intFunction: IntFunction = IntFunction(
        AsserterSingleton.INSTANCE.functionLengthAsserter,
        EvaluatorSingleton.INSTANCE.nodeEvaluator,
        ValueRetrieverSingleton.INSTANCE.atomicValueRetriever,
        DeterminerSingleton.INSTANCE.numericStringDeterminer,
        GeneratorSingleton.INSTANCE.nodeGenerator,
        ConverterSingleton.INSTANCE.nodeConverter
    )
    val lessFunction: LessFunction = LessFunction(
        AsserterSingleton.INSTANCE.functionLengthAsserter,
        EvaluatorSingleton.INSTANCE.nodeEvaluator,
        ValueRetrieverSingleton.INSTANCE.numericValueRetriever,
        ValueRetrieverSingleton.INSTANCE.listValueRetriever,
        GeneratorSingleton.INSTANCE.nodeGenerator,
        ConverterSingleton.INSTANCE.nodeConverter
    )
    val minusFunction: MinusFunction = MinusFunction(
        AsserterSingleton.INSTANCE.functionLengthAsserter,
        EvaluatorSingleton.INSTANCE.nodeEvaluator,
        ValueRetrieverSingleton.INSTANCE.numericValueRetriever,
        ValueRetrieverSingleton.INSTANCE.listValueRetriever,
        GeneratorSingleton.INSTANCE.nodeGenerator,
        ConverterSingleton.INSTANCE.nodeConverter
    )
    val nullFunction: NullFunction = NullFunction(
        AsserterSingleton.INSTANCE.functionLengthAsserter,
        EvaluatorSingleton.INSTANCE.nodeEvaluator,
        ValueRetrieverSingleton.INSTANCE.atomicValueRetriever,
        ComparatorSingleton.INSTANCE.nodeValueComparator,
        GeneratorSingleton.INSTANCE.nodeGenerator,
        ConverterSingleton.INSTANCE.nodeConverter
    )
    val plusFunction: PlusFunction = PlusFunction(
        AsserterSingleton.INSTANCE.functionLengthAsserter,
        EvaluatorSingleton.INSTANCE.nodeEvaluator,
        ValueRetrieverSingleton.INSTANCE.numericValueRetriever,
        ValueRetrieverSingleton.INSTANCE.listValueRetriever,
        GeneratorSingleton.INSTANCE.nodeGenerator,
        ConverterSingleton.INSTANCE.nodeConverter
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
        GeneratorSingleton.INSTANCE.nodeGenerator,
        ConverterSingleton.INSTANCE.nodeConverter
    )

}