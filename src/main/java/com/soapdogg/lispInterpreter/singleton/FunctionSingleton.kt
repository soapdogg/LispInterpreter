package com.soapdogg.lispInterpreter.singleton

import com.soapdogg.lispInterpreter.constants.FunctionNameConstants
import com.soapdogg.lispInterpreter.function.*

enum class FunctionSingleton {
    INSTANCE;

    val atomFunction: AtomFunction = AtomFunction(
        GeneratorSingleton.INSTANCE.nodeGenerator,
        ValueRetrieverSingleton.INSTANCE.atomNodeValueRetriever
    )
    val carFunction: CarFunction = CarFunction(
        ValueRetrieverSingleton.INSTANCE.listValueRetriever
    )
    val cdrFunction: CdrFunction = CdrFunction(
        ValueRetrieverSingleton.INSTANCE.listValueRetriever,
        GeneratorSingleton.INSTANCE.nodeGenerator
    )
    val consFunction: ConsFunction = ConsFunction(
        ValueRetrieverSingleton.INSTANCE.atomNodeValueRetriever,
        GeneratorSingleton.INSTANCE.nodeGenerator
    )
    val eqFunction: EqFunction = EqFunction(
        ValueRetrieverSingleton.INSTANCE.atomNodeValueRetriever,
        GeneratorSingleton.INSTANCE.nodeGenerator
    )
    val greaterFunction: GreaterFunction = GreaterFunction(
        ValueRetrieverSingleton.INSTANCE.numericValueRetriever,
        GeneratorSingleton.INSTANCE.nodeGenerator
    )
    val intFunction: IntFunction = IntFunction(
        ValueRetrieverSingleton.INSTANCE.atomNodeValueRetriever,
        DeterminerSingleton.INSTANCE.numericStringDeterminer,
        GeneratorSingleton.INSTANCE.nodeGenerator
    )
    val lessFunction: LessFunction = LessFunction(
        ValueRetrieverSingleton.INSTANCE.numericValueRetriever,
        GeneratorSingleton.INSTANCE.nodeGenerator
    )
    val minusFunction: MinusFunction = MinusFunction(
        ValueRetrieverSingleton.INSTANCE.numericValueRetriever,
        GeneratorSingleton.INSTANCE.nodeGenerator
    )
    val nullFunction: NullFunction = NullFunction(
        GeneratorSingleton.INSTANCE.nodeGenerator,
        ValueRetrieverSingleton.INSTANCE.atomNodeValueRetriever
    )
    val plusFunction: PlusFunction = PlusFunction(
        ValueRetrieverSingleton.INSTANCE.numericValueRetriever,
        GeneratorSingleton.INSTANCE.nodeGenerator
    )
    val timesFunction: TimesFunction = TimesFunction(
        ValueRetrieverSingleton.INSTANCE.numericValueRetriever,
        GeneratorSingleton.INSTANCE.nodeGenerator
    )

    val functionMap = mapOf(
        Pair(FunctionNameConstants.ATOM, atomFunction),
        Pair(FunctionNameConstants.CAR, carFunction),
        Pair(FunctionNameConstants.CDR, cdrFunction),
        Pair(FunctionNameConstants.CONS, consFunction),
        Pair(FunctionNameConstants.EQ, eqFunction),
        Pair(FunctionNameConstants.GREATER, greaterFunction),
        Pair(FunctionNameConstants.INT, intFunction),
        Pair(FunctionNameConstants.LESS, lessFunction),
        Pair(FunctionNameConstants.MINUS, minusFunction),
        Pair(FunctionNameConstants.NULL, nullFunction),
        Pair(FunctionNameConstants.PLUS, plusFunction),
        Pair(FunctionNameConstants.TIMES, timesFunction)
    )
}