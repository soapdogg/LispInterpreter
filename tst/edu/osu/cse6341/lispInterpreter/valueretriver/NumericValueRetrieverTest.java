package edu.osu.cse6341.lispInterpreter.valueretriver;

import edu.osu.cse6341.lispInterpreter.constants.FunctionNameConstants;
import edu.osu.cse6341.lispInterpreter.determiner.NumericStringDeterminer;
import edu.osu.cse6341.lispInterpreter.exceptions.NotNumericException;
import edu.osu.cse6341.lispInterpreter.printer.ListNotationPrinter;
import edu.osu.cse6341.lispInterpreter.program.nodes.LispNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class NumericValueRetrieverTest {

    private LispNode node;
    private int position;
    private String functionName;

    private NumericStringDeterminer numericStringDeterminer;
    private ListNotationPrinter listNotationPrinter;
    private NumericValueRetriever numericValueRetriever;

    @BeforeEach
    void setup() {
        node = Mockito.mock(LispNode.class);
        position = 1;
        functionName = FunctionNameConstants.QUOTE;

        numericStringDeterminer = Mockito.mock(NumericStringDeterminer.class);
        listNotationPrinter = Mockito.mock(ListNotationPrinter.class);
        numericValueRetriever = NumericValueRetriever.newInstance(
            numericStringDeterminer,
            listNotationPrinter
        );
    }

    @Test
    void nodeIsNotNumericTest() {
        String value = "value";
        Mockito.when(node.getValue()).thenReturn(value);
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
    void nodeIsNumericTest() throws NotNumericException {
        int value = 34;
        Mockito.when(node.getValue()).thenReturn(Integer.toString(value));
        Mockito.when(numericStringDeterminer.isStringNumeric(Integer.toString(value))).thenReturn(true);



        int actual = numericValueRetriever.retrieveNumericValue(
            node,
            position,
            functionName
        );

        Assertions.assertEquals(value, actual);
    }
}
