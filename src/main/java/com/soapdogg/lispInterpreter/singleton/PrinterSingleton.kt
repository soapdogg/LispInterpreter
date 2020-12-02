package com.soapdogg.lispInterpreter.singleton

import com.soapdogg.lispInterpreter.printer.*

enum class PrinterSingleton {
    INSTANCE;

    val atomNodePrinter: AtomNodePrinter = AtomNodePrinter()
    val dotNotationExpressionNodePrinter: DotNotationExpressionNodePrinter
    val dotNotationPrinter: DotNotationPrinter
    val listNotationExpressionNodePrinter: ListNotationExpressionNodePrinter
    val listNotationPrinter: ListNotationPrinter

    init {
        dotNotationExpressionNodePrinter = DotNotationExpressionNodePrinter(
            atomNodePrinter
        )
        dotNotationPrinter = DotNotationPrinter(
            atomNodePrinter,
            dotNotationExpressionNodePrinter
        )
        listNotationExpressionNodePrinter = ListNotationExpressionNodePrinter(
            ComparatorSingleton.INSTANCE.nodeValueComparator,
            atomNodePrinter
        )
        listNotationPrinter = ListNotationPrinter(
            atomNodePrinter,
            listNotationExpressionNodePrinter
        )
    }
}