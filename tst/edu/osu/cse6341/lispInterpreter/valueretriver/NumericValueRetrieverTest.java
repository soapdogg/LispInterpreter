package edu.osu.cse6341.lispInterpreter.valueretriver;

import edu.osu.cse6341.lispInterpreter.constants.FunctionNameConstants;
import edu.osu.cse6341.lispInterpreter.determiner.NumericStringDeterminer;
import edu.osu.cse6341.lispInterpreter.exceptions.NotAtomicException;
import edu.osu.cse6341.lispInterpreter.exceptions.NotNumericException;
import edu.osu.cse6341.lispInterpreter.printer.ListNotationPrinter;
import edu.osu.cse6341.lispInterpreter.datamodels.Node;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class NumericValueRetrieverTest {

    private Node node;
    private int position;
    private String functionName;

    private AtomicValueRetriever atomicValueRetriever;
    private NumericStringDeterminer numericStringDeterminer;

    private NumericValueRetriever numericValueRetriever;

    @BeforeEach
    void setup() {
        node = Mockito.mock(Node.class);
        position = 1;
        functionName = FunctionNameConstants.QUOTE;

        atomicValueRetriever = Mockito.mock(AtomicValueRetriever.class);
        numericStringDeterminer = Mockito.mock(NumericStringDeterminer.class);
        ListNotationPrinter listNotationPrinter = Mockito.mock(ListNotationPrinter.class);

        numericValueRetriever = NumericValueRetriever.newInstance(
            atomicValueRetriever,
            numericStringDeterminer,
            listNotationPrinter
        );
    }

    @Test
    void nodeIsNotNumericTest() throws NotAtomicException {
        String value = "value";
        Mockito.when(
            atomicValueRetriever.retrieveAtomicValue(
                node,
                position,
                functionName
            )
        ).thenReturn(value);
        Mockito.when(numericStringDeterminer.isStringNumeric(value)).thenReturn(false);

        Assertions.assertThrows(
            NotNumericException.class,
            () -> numericValueRetriever.retrieveNumericValue(
                node,
                position,
                functionName
            )
        );
    }

    @Test
    void nodeIsNumericTest() throws NotNumericException, NotAtomicException {
        int value = 34;
        Mockito.when(
            atomicValueRetriever.retrieveAtomicValue(
                node,
                position,
                functionName
            )
        ).thenReturn(Integer.toString(value));
        Mockito.when(numericStringDeterminer.isStringNumeric(Integer.toString(value))).thenReturn(true);



        int actual = numericValueRetriever.retrieveNumericValue(
            node,
            position,
            functionName
        );

        Assertions.assertEquals(value, actual);
    }
}
