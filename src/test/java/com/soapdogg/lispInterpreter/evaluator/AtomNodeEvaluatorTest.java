package com.soapdogg.lispInterpreter.evaluator;

import com.soapdogg.lispInterpreter.datamodels.AtomNode;
import com.soapdogg.lispInterpreter.datamodels.Node;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.Map;

class AtomNodeEvaluatorTest {

    private AtomNode atomNode;
    private String variableName;
    private Node variableValue;
    private Map<String, Node> variableNameToValueMap;

    private AtomNodeEvaluator atomNodeEvaluator;

    @BeforeEach
    void setup() {
        atomNode = Mockito.mock(AtomNode.class);

        variableName = "variableName";
        variableValue = Mockito.mock(Node.class);

        variableNameToValueMap = Collections.singletonMap(variableName, variableValue);

        atomNodeEvaluator = new AtomNodeEvaluator();
    }

    @Test
    void atomNodeIsVariableTest() {
        Mockito.when(atomNode.getValue()).thenReturn(variableName);

        Node actual = atomNodeEvaluator.evaluate(
            atomNode,
            variableNameToValueMap
        );

        Assertions.assertEquals(variableValue, actual);
    }

    @Test
    void atomNodeIsNotAVariableTest() {
        String value = "value";
        Mockito.when(atomNode.getValue()).thenReturn(value);

        Node actual = atomNodeEvaluator.evaluate(
            atomNode,
            variableNameToValueMap
        );

        Assertions.assertEquals(atomNode, actual);
    }
}
