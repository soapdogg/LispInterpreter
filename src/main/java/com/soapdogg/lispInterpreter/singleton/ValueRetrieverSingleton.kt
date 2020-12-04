package com.soapdogg.lispInterpreter.singleton

import com.soapdogg.lispInterpreter.valueretriver.ListValueRetriever
import com.soapdogg.lispInterpreter.valueretriver.NumericValueRetriever

enum class ValueRetrieverSingleton {
    INSTANCE;

    val listValueRetriever: ListValueRetriever = ListValueRetriever(
        PrinterSingleton.INSTANCE.dotNotationPrinter
    )
    val numericValueRetriever: NumericValueRetriever = NumericValueRetriever(
        DeterminerSingleton.INSTANCE.numericStringDeterminer,
        PrinterSingleton.INSTANCE.listNotationPrinter
    )
}