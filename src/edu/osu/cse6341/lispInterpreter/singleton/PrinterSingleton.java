package edu.osu.cse6341.lispInterpreter.singleton;

import edu.osu.cse6341.lispInterpreter.printer.*;
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
        atomNodePrinter = AtomNodePrinter.newInstance();
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
