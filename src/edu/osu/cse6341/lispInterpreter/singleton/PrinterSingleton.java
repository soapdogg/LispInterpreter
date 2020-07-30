package edu.osu.cse6341.lispInterpreter.singleton;

import edu.osu.cse6341.lispInterpreter.printer.DotNotationPrinter;
import lombok.Getter;

@Getter
public enum PrinterSingleton {
    INSTANCE;

    private final DotNotationPrinter dotNotationPrinter;

    PrinterSingleton() {
        dotNotationPrinter = DotNotationPrinter.newInstance();
    }
}
