package com.soapdogg.lispInterpreter.valueretriver;

import com.soapdogg.lispInterpreter.determiner.NumericStringDeterminer;
import com.soapdogg.lispInterpreter.exceptions.NotAtomicException;
import com.soapdogg.lispInterpreter.exceptions.NotNumericException;
import com.soapdogg.lispInterpreter.printer.ListNotationPrinter;
import com.soapdogg.lispInterpreter.datamodels.Node;
import lombok.AllArgsConstructor;

@AllArgsConstructor(staticName = "newInstance")
public class NumericValueRetriever {

    private final AtomicValueRetriever atomicValueRetriever;
    private final NumericStringDeterminer numericStringDeterminer;
    private final ListNotationPrinter listNotationPrinter;

    public int retrieveNumericValue(
        final Node node,
        final int position,
        final String functionName
    ) throws NotNumericException, NotAtomicException {
        String value = atomicValueRetriever.retrieveAtomicValue(
            node,
            position,
            functionName
        );
        boolean isNumeric = numericStringDeterminer.isStringNumeric(value);
        if(!isNumeric) {
            String listNotation = listNotationPrinter.printInListNotation(
                node
            );
            String sb = "Error! Parameter at position: " + position +
                " of function " +
                functionName +
                " is not numeric!    Actual: " +
                listNotation +
                '\n';
            throw new NotNumericException(sb);
        }
        return Integer.parseInt(value);
    }
}
