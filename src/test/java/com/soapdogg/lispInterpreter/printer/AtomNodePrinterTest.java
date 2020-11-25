package com.soapdogg.lispInterpreter.printer;

import com.soapdogg.lispInterpreter.datamodels.AtomNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class AtomNodePrinterTest {

    private AtomNodePrinter atomNodePrinter;

    @BeforeEach
    void setup() {
        atomNodePrinter = new AtomNodePrinter();
    }

    @Test
    void atomNodePrinterTest() {
        String value = "value";
        AtomNode atomNode = Mockito.mock(AtomNode.class);
        Mockito.when(atomNode.getValue()).thenReturn(value);

        String actual = atomNodePrinter.printAtomNode(atomNode);

        Assertions.assertEquals(value, actual);
    }
}
