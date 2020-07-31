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
            PrinterSingleton.INSTANCE.getListNotationPrinter()
        );
        listValueRetriever = ListValueRetriever.newInstance(
            ComparatorSingleton.INSTANCE.getNodeValueComparator()
        );
        numericValueRetriever = NumericValueRetriever.newInstance(
            DeterminerSingleton.INSTANCE.getNumericStringDeterminer(),
            PrinterSingleton.INSTANCE.getListNotationPrinter()
        );
    }
}
