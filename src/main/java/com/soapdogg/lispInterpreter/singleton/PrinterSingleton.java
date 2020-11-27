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
        dotNotationExpressionNodePrinter = new DotNotationExpressionNodePrinter(
            DeterminerSingleton.INSTANCE.getExpressionNodeDeterminer(),
            atomNodePrinter
        );
        dotNotationPrinter = new DotNotationPrinter(
            DeterminerSingleton.INSTANCE.getExpressionNodeDeterminer(),
            atomNodePrinter,
            dotNotationExpressionNodePrinter
        );
        listNotationExpressionNodePrinter = new ListNotationExpressionNodePrinter(
            DeterminerSingleton.INSTANCE.getExpressionNodeDeterminer(),
            ComparatorSingleton.INSTANCE.getNodeValueComparator(),
            atomNodePrinter
        );
        listNotationPrinter = new ListNotationPrinter(
            DeterminerSingleton.INSTANCE.getExpressionNodeDeterminer(),
            atomNodePrinter,
            listNotationExpressionNodePrinter
        );
    }
}
