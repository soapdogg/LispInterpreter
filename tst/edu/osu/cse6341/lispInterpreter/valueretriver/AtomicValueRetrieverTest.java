package edu.osu.cse6341.lispInterpreter.valueretriver;

import edu.osu.cse6341.lispInterpreter.constants.FunctionNameConstants;
import edu.osu.cse6341.lispInterpreter.exceptions.NotAtomicException;
import edu.osu.cse6341.lispInterpreter.printer.ListNotationPrinter;
import edu.osu.cse6341.lispInterpreter.program.nodes.LispNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class AtomicValueRetrieverTest {

    private LispNode node;
    private int position;
    private String functionName;

    private ListNotationPrinter listNotationPrinter;
    private AtomicValueRetriever atomicValueRetriever;

    @BeforeEach
    void setup() {
        node = Mockito.mock(LispNode.class);
        position = 1;
        functionName = FunctionNameConstants.TIMES;

        listNotationPrinter = Mockito.mock(ListNotationPrinter.class);
        atomicValueRetriever = AtomicValueRetriever.newInstance(
            listNotationPrinter
        );
    }

    @Test
    void nodeIsListTest() {
        Mockito.when(node.isNodeList()).thenReturn(true);

        Assertions.assertThrows(
            NotAtomicException.class,
            () -> atomicValueRetriever.retrieveAtomicValue(
                node,
                position,
                functionName
            )
        );
    }

    @Test
    void nodeIsNotListTest() throws Exception {
        Mockito.when(node.isNodeList()).thenReturn(false);

        String value = "value";
        Mockito.when(node.getNodeValue()).thenReturn(value);

        String actual = atomicValueRetriever.retrieveAtomicValue(
            node,
            position,
            functionName
        );

        Assertions.assertEquals(value, actual);
    }
}
