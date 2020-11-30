package com.soapdogg.lispInterpreter.singleton

import com.soapdogg.lispInterpreter.valueretriver.AtomicValueRetriever
import com.soapdogg.lispInterpreter.valueretriver.ListValueRetriever
import com.soapdogg.lispInterpreter.valueretriver.NumericValueRetriever

enum class ValueRetrieverSingleton {
    INSTANCE;

    val atomicValueRetriever: AtomicValueRetriever = AtomicValueRetriever(
        DeterminerSingleton.INSTANCE.expressionNodeDeterminer,
        PrinterSingleton.INSTANCE.listNotationPrinter
    )
    val listValueRetriever: ListValueRetriever = ListValueRetriever(
        DeterminerSingleton.INSTANCE.expressionNodeDeterminer,
        PrinterSingleton.INSTANCE.dotNotationPrinter
    )
    val numericValueRetriever: NumericValueRetriever

    init {
        numericValueRetriever = NumericValueRetriever(
            atomicValueRetriever,
            DeterminerSingleton.INSTANCE.numericStringDeterminer,
            PrinterSingleton.INSTANCE.listNotationPrinter
        )
    }
}