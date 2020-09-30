package com.soapdogg.lispInterpreter.determiner;

import com.soapdogg.lispInterpreter.datamodels.AtomNode;
import com.soapdogg.lispInterpreter.datamodels.ExpressionNode;
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
