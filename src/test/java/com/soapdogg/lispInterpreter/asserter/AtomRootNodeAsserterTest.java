package com.soapdogg.lispInterpreter.asserter;

import com.soapdogg.lispInterpreter.comparator.NodeValueComparator;
import com.soapdogg.lispInterpreter.datamodels.AtomNode;
import com.soapdogg.lispInterpreter.determiner.NumericStringDeterminer;
import com.soapdogg.lispInterpreter.exceptions.NotAtomicException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class AtomRootNodeAsserterTest {

    private String value;
    private AtomNode atomNode;

    private NumericStringDeterminer numericStringDeterminer;
    private NodeValueComparator nodeValueComparator;

    private AtomRootNodeAsserter atomRootNodeAsserter;

    @BeforeEach
    void setup() {
        value = "value";
        atomNode = Mockito.mock(AtomNode.class);
        Mockito.when(atomNode.getValue()).thenReturn(value);

        numericStringDeterminer = Mockito.mock(NumericStringDeterminer.class);

        nodeValueComparator = Mockito.mock(NodeValueComparator.class);

        atomRootNodeAsserter = AtomRootNodeAsserter.newInstance(
            numericStringDeterminer,
            nodeValueComparator
        );
    }

    @Test
    void valueIsNumericTest() {
        Mockito.when(numericStringDeterminer.isStringNumeric(value)).thenReturn(true);
        Mockito.when(nodeValueComparator.equalsT(value)).thenReturn(false);
        Mockito.when(nodeValueComparator.equalsNil(value)).thenReturn(false);

        Assertions.assertDoesNotThrow(
            () -> atomRootNodeAsserter.assertAtomRootNode(atomNode)
        );
    }

    @Test
    void valueIsTTest() {
        Mockito.when(numericStringDeterminer.isStringNumeric(value)).thenReturn(false);
        Mockito.when(nodeValueComparator.equalsT(value)).thenReturn(true);
        Mockito.when(nodeValueComparator.equalsNil(value)).thenReturn(false);

        Assertions.assertDoesNotThrow(
            () -> atomRootNodeAsserter.assertAtomRootNode(atomNode)
        );
    }

    @Test
    void valueIsNilTest() {
        Mockito.when(numericStringDeterminer.isStringNumeric(value)).thenReturn(false);
        Mockito.when(nodeValueComparator.equalsT(value)).thenReturn(false);
        Mockito.when(nodeValueComparator.equalsNil(value)).thenReturn(true);

        Assertions.assertDoesNotThrow(
            () -> atomRootNodeAsserter.assertAtomRootNode(atomNode)
        );
    }

    @Test
    void invalidValueTest() {
        Mockito.when(numericStringDeterminer.isStringNumeric(value)).thenReturn(false);
        Mockito.when(nodeValueComparator.equalsT(value)).thenReturn(false);
        Mockito.when(nodeValueComparator.equalsNil(value)).thenReturn(false);

        Assertions.assertThrows(
            NotAtomicException.class,
            () -> atomRootNodeAsserter.assertAtomRootNode(atomNode)
        );
    }
}
