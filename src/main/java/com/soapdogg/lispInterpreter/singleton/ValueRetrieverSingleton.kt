package com.soapdogg.lispInterpreter.singleton

import com.soapdogg.lispInterpreter.valueretriver.AtomNodeValueRetriever
import com.soapdogg.lispInterpreter.valueretriver.ListValueRetriever
import com.soapdogg.lispInterpreter.valueretriver.NumericValueRetriever

enum class ValueRetrieverSingleton {
    INSTANCE;

    val atomNodeValueRetriever: AtomNodeValueRetriever = AtomNodeValueRetriever()
    val listValueRetriever: ListValueRetriever = ListValueRetriever(
        PrinterSingleton.INSTANCE.dotNotationPrinter
    )
    val numericValueRetriever: NumericValueRetriever = NumericValueRetriever(
        DeterminerSingleton.INSTANCE.numericStringDeterminer,
        PrinterSingleton.INSTANCE.listNotationPrinter
    )
}