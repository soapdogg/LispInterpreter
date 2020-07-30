package edu.osu.cse6341.lispInterpreter.valueretriver;

import edu.osu.cse6341.lispInterpreter.constants.FunctionNameConstants;
import edu.osu.cse6341.lispInterpreter.exceptions.NotNumericException;
import edu.osu.cse6341.lispInterpreter.program.nodes.LispNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class NumericValueRetrieverTest {

    private LispNode node;
    private int position;
    private String functionName;

    private NumericValueRetriever numericValueRetriever;

    @BeforeEach
    void setup() {
        node = Mockito.mock(LispNode.class);
        position = 1;
        functionName = FunctionNameConstants.QUOTE;

        numericValueRetriever = NumericValueRetriever.newInstance();
    }

    @Test
    void nodeIsNotNumericTest() {
        Mockito.when(node.isNodeNumeric()).thenReturn(false);

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
        Mockito.when(node.isNodeNumeric()).thenReturn(true);

        int value = 34;
        Mockito.when(node.getNodeValue()).thenReturn(Integer.toString(value));

        int actual = numericValueRetriever.retrieveNumericValue(
            node,
            position,
            functionName
        );

        Assertions.assertEquals(value, actual);
    }
}
