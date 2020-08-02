package edu.osu.cse6341.lispInterpreter.evaluator;

import edu.osu.cse6341.lispInterpreter.comparator.NodeValueComparator;
import edu.osu.cse6341.lispInterpreter.constants.FunctionLengthConstants;
import edu.osu.cse6341.lispInterpreter.constants.FunctionNameConstants;
import edu.osu.cse6341.lispInterpreter.exceptions.NotAListException;
import edu.osu.cse6341.lispInterpreter.nodes.AtomNode;
import edu.osu.cse6341.lispInterpreter.nodes.ExpressionNode;
import edu.osu.cse6341.lispInterpreter.nodes.LispNode;
import edu.osu.cse6341.lispInterpreter.valueretriver.ListValueRetriever;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class CondFunctionEvaluatorTest {

    private LispNode params;

    private ListValueRetriever listValueRetriever;
    private NodeEvaluator nodeEvaluator;
    private NodeValueComparator nodeValueComparator;

    private CondFunctionEvaluator condFunctionEvaluator;

    @BeforeEach
    void setup() {
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
            () -> condFunctionEvaluator.evaluateCondFunction(params)
        );

        Mockito.verifyZeroInteractions(listValueRetriever);
        Mockito.verifyZeroInteractions(nodeEvaluator);
        Mockito.verifyZeroInteractions(nodeValueComparator);
    }

    @Test
    void inputIsListAndBooleanResultIsNotNilTest() throws Exception {
        params = Mockito.mock(ExpressionNode.class);

        LispNode address = Mockito.mock(LispNode.class);
        Mockito.when(((ExpressionNode)params).getAddress()).thenReturn(address);

        ExpressionNode expressionNodeAddress = Mockito.mock(ExpressionNode.class);
        Mockito.when(
            listValueRetriever.retrieveListValue(
                address,
                FunctionNameConstants.COND
            )
        ).thenReturn(expressionNodeAddress);

        LispNode expressionNodeAddressAddress = Mockito.mock(LispNode.class);
        Mockito.when(expressionNodeAddress.getAddress()).thenReturn(expressionNodeAddressAddress);

        LispNode booleanResult = Mockito.mock(AtomNode.class);
        Mockito.when(
            nodeEvaluator.evaluate(
                expressionNodeAddressAddress,
                true
            )
        ).thenReturn(booleanResult);

        Mockito.when(
            nodeValueComparator.equalsNil(
                ((AtomNode)booleanResult).getValue()
            )
        ).thenReturn(false);

        LispNode expressionNodeAddressData = Mockito.mock(LispNode.class);
        Mockito.when(expressionNodeAddress.getData()).thenReturn(expressionNodeAddressData);

        LispNode expected = Mockito.mock(LispNode.class);
        Mockito.when(
            nodeEvaluator.evaluate(
                expressionNodeAddressData,
                true
            )
        ).thenReturn(expected);

        LispNode actual = condFunctionEvaluator.evaluateCondFunction(params);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void inputIsListAndBooleanResultIsNotAtomNodeTest() throws Exception {
        params = Mockito.mock(ExpressionNode.class);

        LispNode address = Mockito.mock(LispNode.class);
        Mockito.when(((ExpressionNode)params).getAddress()).thenReturn(address);

        ExpressionNode expressionNodeAddress = Mockito.mock(ExpressionNode.class);
        Mockito.when(
            listValueRetriever.retrieveListValue(
                address,
                FunctionNameConstants.COND
            )
        ).thenReturn(expressionNodeAddress);

        LispNode expressionNodeAddressAddress = Mockito.mock(LispNode.class);
        Mockito.when(expressionNodeAddress.getAddress()).thenReturn(expressionNodeAddressAddress);

        LispNode booleanResult = Mockito.mock(ExpressionNode.class);
        Mockito.when(
            nodeEvaluator.evaluate(
                expressionNodeAddressAddress,
                true
            )
        ).thenReturn(booleanResult);

        LispNode data = Mockito.mock(AtomNode.class);
        Mockito.when(((ExpressionNode)params).getData()).thenReturn(data);

        Assertions.assertThrows(
            NotAListException.class,
            () -> condFunctionEvaluator.evaluateCondFunction(params)
        );
    }

    @Test
    void inputIsListAndBooleanResultIsNilTest() throws Exception {
        params = Mockito.mock(ExpressionNode.class);

        LispNode address = Mockito.mock(LispNode.class);
        Mockito.when(((ExpressionNode)params).getAddress()).thenReturn(address);

        ExpressionNode expressionNodeAddress = Mockito.mock(ExpressionNode.class);
        Mockito.when(
            listValueRetriever.retrieveListValue(
                address,
                FunctionNameConstants.COND
            )
        ).thenReturn(expressionNodeAddress);

        LispNode expressionNodeAddressAddress = Mockito.mock(LispNode.class);
        Mockito.when(expressionNodeAddress.getAddress()).thenReturn(expressionNodeAddressAddress);

        LispNode booleanResult = Mockito.mock(AtomNode.class);
        Mockito.when(
            nodeEvaluator.evaluate(
                expressionNodeAddressAddress,
                true
            )
        ).thenReturn(booleanResult);

        Mockito.when(
            nodeValueComparator.equalsNil(
                ((AtomNode)booleanResult).getValue()
            )
        ).thenReturn(true);

        LispNode data = Mockito.mock(AtomNode.class);
        Mockito.when(((ExpressionNode)params).getData()).thenReturn(data);

        Assertions.assertThrows(
            NotAListException.class,
            () -> condFunctionEvaluator.evaluateCondFunction(params)
        );
    }
}
