package com.soapdogg.lispInterpreter.interpreter;

import com.soapdogg.lispInterpreter.constants.FunctionNameConstants;
import com.soapdogg.lispInterpreter.datamodels.PartitionedRootNodes;
import com.soapdogg.lispInterpreter.datamodels.AtomNode;
import com.soapdogg.lispInterpreter.datamodels.ExpressionNode;
import com.soapdogg.lispInterpreter.datamodels.Node;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;

class RootNodePartitionerTest {

    private List<Node> rootNodes;

    private RootNodePartitioner rootNodePartitioner;

    @BeforeEach
    void setup() {
        rootNodePartitioner = RootNodePartitioner.newInstance();
    }

    @Test
    void atomRootNodeTest() {
        AtomNode rootNode = Mockito.mock(AtomNode.class);

        rootNodes = Collections.singletonList(rootNode);

        PartitionedRootNodes actual = rootNodePartitioner.partitionRootNodes(
            rootNodes
        );

        Assertions.assertTrue(actual.getDefunNodes().isEmpty());
        Assertions.assertEquals(1, actual.getEvaluatableNodes().size());
        Assertions.assertEquals(rootNode, actual.getEvaluatableNodes().get(0));
    }

    @Test
    void expressionRootNodeWithExpressionAddressTest() {
        ExpressionNode rootNode = Mockito.mock(ExpressionNode.class);

        Node address = Mockito.mock(ExpressionNode.class);
        Mockito.when(rootNode.getAddress()).thenReturn(address);

        rootNodes = Collections.singletonList(rootNode);

        PartitionedRootNodes actual = rootNodePartitioner.partitionRootNodes(
            rootNodes
        );

        Assertions.assertTrue(actual.getDefunNodes().isEmpty());
        Assertions.assertEquals(1, actual.getEvaluatableNodes().size());
        Assertions.assertEquals(rootNode, actual.getEvaluatableNodes().get(0));
    }

    @Test
    void expressionRootNodeWithAtomAddressNotDefunTest() {
        ExpressionNode rootNode = Mockito.mock(ExpressionNode.class);

        AtomNode address = Mockito.mock(AtomNode.class);
        Mockito.when(rootNode.getAddress()).thenReturn(address);

        String value = "value";
        Mockito.when(address.getValue()).thenReturn(value);

        rootNodes = Collections.singletonList(rootNode);

        PartitionedRootNodes actual = rootNodePartitioner.partitionRootNodes(
            rootNodes
        );

        Assertions.assertTrue(actual.getDefunNodes().isEmpty());
        Assertions.assertEquals(1, actual.getEvaluatableNodes().size());
        Assertions.assertEquals(rootNode, actual.getEvaluatableNodes().get(0));
    }

    @Test
    void expressionRootNodeWithAtomAddressDefunTest() {
        ExpressionNode rootNode = Mockito.mock(ExpressionNode.class);

        AtomNode address = Mockito.mock(AtomNode.class);
        Mockito.when(rootNode.getAddress()).thenReturn(address);

        String value = FunctionNameConstants.DEFUN;
        Mockito.when(address.getValue()).thenReturn(value);

        rootNodes = Collections.singletonList(rootNode);

        PartitionedRootNodes actual = rootNodePartitioner.partitionRootNodes(
            rootNodes
        );

        Assertions.assertTrue(actual.getEvaluatableNodes().isEmpty());
        Assertions.assertEquals(1, actual.getDefunNodes().size());
        Assertions.assertEquals(rootNode, actual.getDefunNodes().get(0));
    }
}
