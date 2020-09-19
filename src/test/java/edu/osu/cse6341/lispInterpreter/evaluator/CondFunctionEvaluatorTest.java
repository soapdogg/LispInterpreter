package edu.osu.cse6341.lispInterpreter.evaluator;

import edu.osu.cse6341.lispInterpreter.comparator.NodeValueComparator;
import edu.osu.cse6341.lispInterpreter.constants.FunctionNameConstants;
import edu.osu.cse6341.lispInterpreter.datamodels.UserDefinedFunction;
import edu.osu.cse6341.lispInterpreter.evaluator.CondFunctionEvaluator;
import edu.osu.cse6341.lispInterpreter.evaluator.NodeEvaluator;
import edu.osu.cse6341.lispInterpreter.exceptions.NotAListException;
import edu.osu.cse6341.lispInterpreter.datamodels.AtomNode;
import edu.osu.cse6341.lispInterpreter.datamodels.ExpressionNode;
import edu.osu.cse6341.lispInterpreter.datamodels.Node;
import edu.osu.cse6341.lispInterpreter.valueretriver.ListValueRetriever;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;
import java.util.Map;

class CondFunctionEvaluatorTest {

    private Node params;
    private List<UserDefinedFunction> userDefinedFunctions;
    private Map<String, Node> variableNameToValueMap;

    private ListValueRetriever listValueRetriever;
    private NodeEvaluator nodeEvaluator;
    private NodeValueComparator nodeValueComparator;

    private CondFunctionEvaluator condFunctionEvaluator;

    @BeforeEach
    void setup() {
        userDefinedFunctions = Collections.emptyList();
        variableNameToValueMap = Collections.emptyMap();

        listValueRetriever = Mockito.mock(ListValueRetriever.class);
        nodeEvaluator = Mockito.mock(NodeEvaluator.class);
        nodeValueComparator = Mockito.mock(NodeValueComparator.class);

        condFunctionEvaluator = CondFunctionEvaluator.newInstance(
            listValueRetriever,
            nodeEvaluator,
            nodeValueComparator
        );
    }

    @Test
    void inputIsAtomNodeTest() {
        params = Mockito.mock(AtomNode.class);

        Assertions.assertThrows(
            NotAListException.class,
            () -> condFunctionEvaluator.evaluateCondFunction(
                params,
                userDefinedFunctions,
                variableNameToValueMap
            )
        );

        Mockito.verifyNoInteractions(listValueRetriever);
        Mockito.verifyNoInteractions(nodeEvaluator);
        Mockito.verifyNoInteractions(nodeValueComparator);
    }

    @Test
    void inputIsListAndBooleanResultIsNotNilTest() throws Exception {
        params = Mockito.mock(ExpressionNode.class);

        Node address = Mockito.mock(Node.class);
        Mockito.when(((ExpressionNode)params).getAddress()).thenReturn(address);

        ExpressionNode expressionNodeAddress = Mockito.mock(ExpressionNode.class);
        Mockito.when(
            listValueRetriever.retrieveListValue(
                address,
                FunctionNameConstants.COND,
                variableNameToValueMap
            )
        ).thenReturn(expressionNodeAddress);

        Node expressionNodeAddressAddress = Mockito.mock(Node.class);
        Mockito.when(expressionNodeAddress.getAddress()).thenReturn(expressionNodeAddressAddress);

        Node booleanResult = Mockito.mock(AtomNode.class);
        Mockito.when(
            nodeEvaluator.evaluate(
                expressionNodeAddressAddress,
                userDefinedFunctions,
                variableNameToValueMap,
                true
            )
        ).thenReturn(booleanResult);

        Mockito.when(
            nodeValueComparator.equalsNil(
                ((AtomNode)booleanResult).getValue()
            )
        ).thenReturn(false);

        Node expressionNodeAddressData = Mockito.mock(Node.class);
        Mockito.when(expressionNodeAddress.getData()).thenReturn(expressionNodeAddressData);

        Node expected = Mockito.mock(Node.class);
        Mockito.when(
            nodeEvaluator.evaluate(
                expressionNodeAddressData,
                userDefinedFunctions,
                variableNameToValueMap,
                true
            )
        ).thenReturn(expected);

        Node actual = condFunctionEvaluator.evaluateCondFunction(
            params,
            userDefinedFunctions,
            variableNameToValueMap
        );

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void inputIsListAndBooleanResultIsNotAtomNodeTest() throws Exception {
        params = Mockito.mock(ExpressionNode.class);

        Node address = Mockito.mock(Node.class);
        Mockito.when(((ExpressionNode)params).getAddress()).thenReturn(address);

        ExpressionNode expressionNodeAddress = Mockito.mock(ExpressionNode.class);
        Mockito.when(
            listValueRetriever.retrieveListValue(
                address,
                FunctionNameConstants.COND,
                variableNameToValueMap
            )
        ).thenReturn(expressionNodeAddress);

        Node expressionNodeAddressAddress = Mockito.mock(Node.class);
        Mockito.when(expressionNodeAddress.getAddress()).thenReturn(expressionNodeAddressAddress);

        Node booleanResult = Mockito.mock(ExpressionNode.class);
        Mockito.when(
            nodeEvaluator.evaluate(
                expressionNodeAddressAddress,
                userDefinedFunctions,
                variableNameToValueMap,
                true
            )
        ).thenReturn(booleanResult);

        Node data = Mockito.mock(AtomNode.class);
        Mockito.when(((ExpressionNode)params).getData()).thenReturn(data);

        Assertions.assertThrows(
            NotAListException.class,
            () -> condFunctionEvaluator.evaluateCondFunction(
                params,
                userDefinedFunctions,
                variableNameToValueMap
            )
        );
    }

    @Test
    void inputIsListAndBooleanResultIsNilTest() throws Exception {
        params = Mockito.mock(ExpressionNode.class);

        Node address = Mockito.mock(Node.class);
        Mockito.when(((ExpressionNode)params).getAddress()).thenReturn(address);

        ExpressionNode expressionNodeAddress = Mockito.mock(ExpressionNode.class);
        Mockito.when(
            listValueRetriever.retrieveListValue(
                address,
                FunctionNameConstants.COND,
                variableNameToValueMap
            )
        ).thenReturn(expressionNodeAddress);

        Node expressionNodeAddressAddress = Mockito.mock(Node.class);
        Mockito.when(expressionNodeAddress.getAddress()).thenReturn(expressionNodeAddressAddress);

        Node booleanResult = Mockito.mock(AtomNode.class);
        Mockito.when(
            nodeEvaluator.evaluate(
                expressionNodeAddressAddress,
                userDefinedFunctions,
                variableNameToValueMap,
                true
            )
        ).thenReturn(booleanResult);

        Mockito.when(
            nodeValueComparator.equalsNil(
                ((AtomNode)booleanResult).getValue()
            )
        ).thenReturn(true);

        Node data = Mockito.mock(AtomNode.class);
        Mockito.when(((ExpressionNode)params).getData()).thenReturn(data);

        Assertions.assertThrows(
            NotAListException.class,
            () -> condFunctionEvaluator.evaluateCondFunction(
                params,
                userDefinedFunctions,
                variableNameToValueMap
            )
        );
    }
}
