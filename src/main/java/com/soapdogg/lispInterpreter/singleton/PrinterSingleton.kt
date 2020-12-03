package com.soapdogg.lispInterpreter.singleton

import com.soapdogg.lispInterpreter.printer.*

enum class PrinterSingleton {
    INSTANCE;

    val atomNodePrinter: AtomNodePrinter = AtomNodePrinter()
    val dotNotationExpressionNodePrinter: DotNotationExpressionNodePrinter
    val dotNotationPrinter: DotNotationPrinter
    val listNotationPrinter: ListNotationPrinter

    init {
        dotNotationExpressionNodePrinter = DotNotationExpressionNodePrinter(
            atomNodePrinter
        )
        dotNotationPrinter = DotNotationPrinter(
            atomNodePrinter,
            dotNotationExpressionNodePrinter
        )
        listNotationPrinter = ListNotationPrinter(
            atomNodePrinter,
            ConverterSingleton.INSTANCE.nodeConverter
        )
    }
}