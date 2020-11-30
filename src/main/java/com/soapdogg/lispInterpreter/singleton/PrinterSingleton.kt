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
            DeterminerSingleton.INSTANCE.expressionNodeDeterminer,
            atomNodePrinter
        )
        dotNotationPrinter = DotNotationPrinter(
            DeterminerSingleton.INSTANCE.expressionNodeDeterminer,
            atomNodePrinter,
            dotNotationExpressionNodePrinter
        )
        listNotationExpressionNodePrinter = ListNotationExpressionNodePrinter(
            DeterminerSingleton.INSTANCE.expressionNodeDeterminer,
            ComparatorSingleton.INSTANCE.nodeValueComparator,
            atomNodePrinter
        )
        listNotationPrinter = ListNotationPrinter(
            DeterminerSingleton.INSTANCE.expressionNodeDeterminer,
            atomNodePrinter,
            listNotationExpressionNodePrinter
        )
    }
}