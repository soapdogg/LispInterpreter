package com.soapdogg.lispInterpreter.valueretriver;

import com.soapdogg.lispInterpreter.constants.FunctionNameConstants;
import com.soapdogg.lispInterpreter.determiner.ExpressionNodeDeterminer;
import com.soapdogg.lispInterpreter.exceptions.NotAtomicException;
import com.soapdogg.lispInterpreter.printer.ListNotationPrinter;
import com.soapdogg.lispInterpreter.datamodels.AtomNode;
import com.soapdogg.lispInterpreter.datamodels.Node;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class AtomicValueRetrieverTest {

    private Node node;
    private int position;
    private String functionName;

    private ExpressionNodeDeterminer expressionNodeDeterminer;
    private AtomicValueRetriever atomicValueRetriever;

    @BeforeEach
    void setup() {
        node = Mockito.mock(AtomNode.class);
        position = 1;
        functionName = FunctionNameConstants.TIMES;

        expressionNodeDeterminer = Mockito.mock(ExpressionNodeDeterminer.class);
        ListNotationPrinter listNotationPrinter = Mockito.mock(ListNotationPrinter.class);
        atomicValueRetriever = AtomicValueRetriever.newInstance(
            expressionNodeDeterminer,
            listNotationPrinter
        );
    }

    @Test
    void nodeIsListTest() {
        Mockito.when(expressionNodeDeterminer.isExpressionNode(node)).thenReturn(true);

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
        Mockito.when(expressionNodeDeterminer.isExpressionNode(node)).thenReturn(false);

        String value = "value";
        Mockito.when(((AtomNode)node).getValue()).thenReturn(value);

        String actual = atomicValueRetriever.retrieveAtomicValue(
            node,
            position,
            functionName
        );

        Assertions.assertEquals(value, actual);
    }
}
