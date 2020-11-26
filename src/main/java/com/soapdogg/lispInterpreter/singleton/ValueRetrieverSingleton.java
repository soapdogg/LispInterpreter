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
        atomicValueRetriever = new AtomicValueRetriever(
            DeterminerSingleton.INSTANCE.getExpressionNodeDeterminer(),
            PrinterSingleton.INSTANCE.getListNotationPrinter()
        );
        listValueRetriever = new ListValueRetriever(
            DeterminerSingleton.INSTANCE.getExpressionNodeDeterminer(),
            PrinterSingleton.INSTANCE.getDotNotationPrinter()
        );
        numericValueRetriever = new NumericValueRetriever(
            atomicValueRetriever,
            DeterminerSingleton.INSTANCE.getNumericStringDeterminer(),
            PrinterSingleton.INSTANCE.getListNotationPrinter()
        );
    }
}
