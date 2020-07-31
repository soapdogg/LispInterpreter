package edu.osu.cse6341.lispInterpreter.singleton;

import edu.osu.cse6341.lispInterpreter.printer.DotNotationPrinter;
import edu.osu.cse6341.lispInterpreter.printer.ListNotationPrinter;
import lombok.Getter;

@Getter
public enum PrinterSingleton {
    INSTANCE;

    private final DotNotationPrinter dotNotationPrinter;
    private final ListNotationPrinter listNotationPrinter;

    PrinterSingleton() {
        dotNotationPrinter = DotNotationPrinter.newInstance();
        listNotationPrinter = ListNotationPrinter.newInstance(
            DeterminerSingleton.INSTANCE.getNumericStringDeterminer(),
            ComparatorSingleton.INSTANCE.getNodeValueComparator()
        );
    }
}
