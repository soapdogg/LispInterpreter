package com.soapdogg.lispInterpreter.generator;

import com.soapdogg.lispInterpreter.constants.ReservedValuesConstants;
import com.soapdogg.lispInterpreter.datamodels.AtomNode;
import com.soapdogg.lispInterpreter.datamodels.ExpressionNode;
import com.soapdogg.lispInterpreter.datamodels.Node;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class NodeGeneratorTest {

    private NodeGenerator nodeGenerator;

    @BeforeEach
    void setup() {
        nodeGenerator = NodeGenerator.newInstance();
    }

    @Test
    void generateTAtomNodeTest() {
        AtomNode actual = nodeGenerator.generateAtomNode(true);

        Assertions.assertEquals(ReservedValuesConstants.T, actual.getValue());
    }

    @Test
    void generateNilAtomNodeTest() {
        AtomNode actual = nodeGenerator.generateAtomNode(false);

        Assertions.assertEquals(ReservedValuesConstants.NIL, actual.getValue());
    }

    @Test
    void generateAtomNodeFromIntTest() {
        int value = 10;
        AtomNode actual = nodeGenerator.generateAtomNode(value);

        Assertions.assertEquals(Integer.toString(value), actual.getValue());
    }

    @Test
    void generateAtomNodeFromStringTest() {
        String value = "value";
        AtomNode actual = nodeGenerator.generateAtomNode(value);

        Assertions.assertEquals(value, actual.getValue());
    }

    @Test
    void generateNonEmptyExpressionNodeTest() {
        Node address = Mockito.mock(Node.class);
        Node data = Mockito.mock(Node.class);

        ExpressionNode actual = nodeGenerator.generateExpressionNode(
            address,
            data
        );

        Assertions.assertEquals(address, actual.getAddress());
        Assertions.assertEquals(data, actual.getData());
    }
}
