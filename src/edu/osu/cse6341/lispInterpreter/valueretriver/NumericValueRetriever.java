package edu.osu.cse6341.lispInterpreter.valueretriver;

import edu.osu.cse6341.lispInterpreter.determiner.NumericStringDeterminer;
import edu.osu.cse6341.lispInterpreter.exceptions.NotAtomicException;
import edu.osu.cse6341.lispInterpreter.exceptions.NotNumericException;
import edu.osu.cse6341.lispInterpreter.printer.ListNotationPrinter;
import edu.osu.cse6341.lispInterpreter.nodes.LispNode;
import lombok.AllArgsConstructor;

@AllArgsConstructor(staticName = "newInstance")
public class NumericValueRetriever {

    private final AtomicValueRetriever atomicValueRetriever;
    private final NumericStringDeterminer numericStringDeterminer;
    private final ListNotationPrinter listNotationPrinter;

    public int retrieveNumericValue(
        final LispNode node,
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
