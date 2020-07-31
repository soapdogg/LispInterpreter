package edu.osu.cse6341.lispInterpreter.determiner;

import edu.osu.cse6341.lispInterpreter.program.nodes.AtomNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.ExpressionNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class ExpressionNodeDeterminerTest {

    private ExpressionNodeDeterminer expressionNodeDeterminer;

    @BeforeEach
    void setup() {
        expressionNodeDeterminer = ExpressionNodeDeterminer.newInstance();
    }

    @Test
    void isTrueTest() {
        ExpressionNode expressionNode = Mockito.mock(ExpressionNode.class);

        boolean actual = expressionNodeDeterminer.isExpressionNode(expressionNode);

        Assertions.assertTrue(actual);
    }

    @Test
    void isFalseTest() {
        AtomNode atomNode = Mockito.mock(AtomNode.class);

        boolean actual = expressionNodeDeterminer.isExpressionNode(atomNode);

        Assertions.assertFalse(actual);
    }
}
