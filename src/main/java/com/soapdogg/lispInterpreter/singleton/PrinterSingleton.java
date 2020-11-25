package com.soapdogg.lispInterpreter.singleton;

import com.soapdogg.lispInterpreter.printer.*;
import lombok.Getter;

@Getter
public enum PrinterSingleton {
    INSTANCE;

    private final AtomNodePrinter atomNodePrinter;
    private final DotNotationExpressionNodePrinter dotNotationExpressionNodePrinter;
    private final DotNotationPrinter dotNotationPrinter;
    private final ListNotationExpressionNodePrinter listNotationExpressionNodePrinter;
    private final ListNotationPrinter listNotationPrinter;

    PrinterSingleton() {
        atomNodePrinter = new AtomNodePrinter();
        dotNotationExpressionNodePrinter = DotNotationExpressionNodePrinter.newInstance(
            DeterminerSingleton.INSTANCE.getExpressionNodeDeterminer(),
            atomNodePrinter
        );
        dotNotationPrinter = DotNotationPrinter.newInstance(
            DeterminerSingleton.INSTANCE.getExpressionNodeDeterminer(),
            atomNodePrinter,
            dotNotationExpressionNodePrinter
        );
        listNotationExpressionNodePrinter = ListNotationExpressionNodePrinter.newInstance(
            DeterminerSingleton.INSTANCE.getExpressionNodeDeterminer(),
            ComparatorSingleton.INSTANCE.getNodeValueComparator(),
            atomNodePrinter
        );
        listNotationPrinter = ListNotationPrinter.newInstance(
            DeterminerSingleton.INSTANCE.getExpressionNodeDeterminer(),
            atomNodePrinter,
            listNotationExpressionNodePrinter
        );
    }
}
