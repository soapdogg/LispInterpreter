package com.soapdogg.lispInterpreter.singleton;

import com.soapdogg.lispInterpreter.valueretriver.AtomicValueRetriever;
import com.soapdogg.lispInterpreter.valueretriver.ListValueRetriever;
import com.soapdogg.lispInterpreter.valueretriver.NumericValueRetriever;
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
            PrinterSingleton.INSTANCE.getDotNotationPrinter()
        );
        numericValueRetriever = NumericValueRetriever.newInstance(
            atomicValueRetriever,
            DeterminerSingleton.INSTANCE.getNumericStringDeterminer(),
            PrinterSingleton.INSTANCE.getListNotationPrinter()
        );
    }
}
