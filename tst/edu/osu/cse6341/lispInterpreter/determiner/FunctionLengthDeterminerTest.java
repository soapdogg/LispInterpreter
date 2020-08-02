package edu.osu.cse6341.lispInterpreter.determiner;

import edu.osu.cse6341.lispInterpreter.constants.ReservedValuesConstants;
import edu.osu.cse6341.lispInterpreter.nodes.AtomNode;
import edu.osu.cse6341.lispInterpreter.nodes.ExpressionNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class FunctionLengthDeterminerTest {

    private FunctionLengthDeterminer functionLengthDeterminer;

    @BeforeEach
    void setup() {
        functionLengthDeterminer = FunctionLengthDeterminer.newInstance();
    }

    @Test
    void determineLengthOfNilNodeTest() {
        AtomNode nilNode = Mockito.mock(AtomNode.class);
        Mockito.when(nilNode.getValue()).thenReturn(ReservedValuesConstants.NIL);

        int actual = functionLengthDeterminer.determineFunctionLength(nilNode);

        Assertions.assertEquals(0, actual);
    }

    @Test
    void determineLengthOfNonNilAtomNodeTest() {
        AtomNode atomNode = Mockito.mock(AtomNode.class);
        Mockito.when(atomNode.getValue()).thenReturn(ReservedValuesConstants.T);

        int actual = functionLengthDeterminer.determineFunctionLength(atomNode);

        Assertions.assertEquals(1, actual);
    }

    @Test
    void determineExpressionNodeLengthTest() {
        AtomNode atomNode = Mockito.mock(AtomNode.class);
        Mockito.when(atomNode.getValue()).thenReturn(ReservedValuesConstants.T);

        ExpressionNode expressionNode = Mockito.mock(ExpressionNode.class);
        Mockito.when(expressionNode.getData()).thenReturn(atomNode);

        int actual = functionLengthDeterminer.determineFunctionLength(expressionNode);

        Assertions.assertEquals(2, actual);
    }
}
