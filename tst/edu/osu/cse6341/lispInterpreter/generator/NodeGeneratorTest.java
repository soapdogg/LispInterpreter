package edu.osu.cse6341.lispInterpreter.generator;

import edu.osu.cse6341.lispInterpreter.constants.ReservedValuesConstants;
import edu.osu.cse6341.lispInterpreter.program.nodes.AtomNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.ExpressionNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.LispNode;
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

        Assertions.assertEquals(ReservedValuesConstants.T, actual.getNodeValue());
    }

    @Test
    void generateNilAtomNodeTest() {
        AtomNode actual = nodeGenerator.generateAtomNode(false);

        Assertions.assertEquals(ReservedValuesConstants.NIL, actual.getNodeValue());
    }

    @Test
    void generateAtomNodeFromIntTest() {
        int value = 10;
        AtomNode actual = nodeGenerator.generateAtomNode(value);

        Assertions.assertEquals(Integer.toString(value), actual.getNodeValue());
    }

    @Test
    void generateAtomNodeFromStringTest() {
        String value = "value";
        AtomNode actual = nodeGenerator.generateAtomNode(value);

        Assertions.assertEquals(value, actual.getNodeValue());
    }

    @Test
    void generateNonEmptyExpressionNodeTest() {
        LispNode address = Mockito.mock(LispNode.class);
        LispNode data = Mockito.mock(LispNode.class);

        ExpressionNode actual = nodeGenerator.generateExpressionNode(
            address,
            data
        );

        Assertions.assertEquals(address, actual.getAddress());
        Assertions.assertEquals(data, actual.getData());
        Assertions.assertTrue(actual.isNodeList());
    }
}
