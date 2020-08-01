package edu.osu.cse6341.lispInterpreter.singleton;

import edu.osu.cse6341.lispInterpreter.valueretriver.AtomicValueRetriever;
import edu.osu.cse6341.lispInterpreter.valueretriver.ListValueRetriever;
import edu.osu.cse6341.lispInterpreter.valueretriver.NumericValueRetriever;
import lombok.Getter;

@Getter
public enum ValueRetrieverSingleton {
    INSTANCE;

    private final AtomicValueRetriever atomicValueRetriever;
    private final ListValueRetriever listValueRetriever;
    private final NumericValueRetriever numericValueRetriever;

    ValueRetrieverSingleton() {
        atomicValueRetriever = AtomicValueRetriever.newInstance(
            DeterminerSingleton.INSTANCE.getExpressionNodeDeterminer(),
            PrinterSingleton.INSTANCE.getListNotationPrinter()
        );
        listValueRetriever = ListValueRetriever.newInstance(
            DeterminerSingleton.INSTANCE.getExpressionNodeDeterminer(),
            PrinterSingleton.INSTANCE.getDotNotationPrinter(),
            DeterminerSingleton.INSTANCE.getFunctionLengthDeterminer()
        );
        numericValueRetriever = NumericValueRetriever.newInstance(
            atomicValueRetriever,
            DeterminerSingleton.INSTANCE.getNumericStringDeterminer(),
            PrinterSingleton.INSTANCE.getListNotationPrinter()
        );
    }
}
