package edu.osu.cse6341.lispInterpreter.asserter;

import edu.osu.cse6341.lispInterpreter.comparator.NodeValueComparator;
import edu.osu.cse6341.lispInterpreter.constants.FunctionLengthConstants;
import edu.osu.cse6341.lispInterpreter.constants.FunctionNameConstants;
import edu.osu.cse6341.lispInterpreter.constants.ReservedValuesConstants;
import edu.osu.cse6341.lispInterpreter.exceptions.NotAListException;
import edu.osu.cse6341.lispInterpreter.nodes.AtomNode;
import edu.osu.cse6341.lispInterpreter.nodes.ExpressionNode;
import edu.osu.cse6341.lispInterpreter.nodes.LispNode;
import edu.osu.cse6341.lispInterpreter.valueretriver.ListValueRetriever;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.Map;

class CondFunctionParameterAsserterTest {

    private LispNode params;
    private Map<String, LispNode> variableNameToValueMap;

    private NodeValueComparator nodeValueComparator;
    private ListValueRetriever listValueRetriever;
    private FunctionLengthAsserter functionLengthAsserter;

    private CondFunctionParameterAsserter condFunctionParameterAsserter;

    @BeforeEach
    void setup() {
        variableNameToValueMap = Collections.emptyMap();

        nodeValueComparator = Mockito.mock(NodeValueComparator.class);
        listValueRetriever = Mockito.mock(ListValueRetriever.class);
        functionLengthAsserter = Mockito.mock(FunctionLengthAsserter.class);

        condFunctionParameterAsserter = CondFunctionParameterAsserter.newInstance(
            nodeValueComparator,
            listValueRetriever,
            functionLengthAsserter
        );
    }

    @Test
    void inputIsNilTest() {
        params = Mockito.mock(AtomNode.class);
        Mockito.when(((AtomNode)params).getValue()).thenReturn(ReservedValuesConstants.NIL);

        Mockito.when(nodeValueComparator.equalsNil(ReservedValuesConstants.NIL)).thenReturn(true);

        Assertions.assertDoesNotThrow(
            () -> condFunctionParameterAsserter.assertCondFunctionParameters(
                params,
                variableNameToValueMap
            )
        );

        Mockito.verifyZeroInteractions(listValueRetriever);
        Mockito.verifyZeroInteractions(functionLengthAsserter);
    }

    @Test
    void inputIsNonNilAtomTest() {
        params = Mockito.mock(AtomNode.class);
        Mockito.when(((AtomNode)params).getValue()).thenReturn(ReservedValuesConstants.T);

        Mockito.when(nodeValueComparator.equalsNil(ReservedValuesConstants.T)).thenReturn(false);

        Assertions.assertThrows(
            NotAListException.class,
            () -> condFunctionParameterAsserter.assertCondFunctionParameters(
                params,
                variableNameToValueMap
            )
        );

        Mockito.verifyZeroInteractions(listValueRetriever);
        Mockito.verifyZeroInteractions(functionLengthAsserter);
    }

    @Test
    void inputIsListTest() throws Exception {
        params = Mockito.mock(ExpressionNode.class);
        LispNode address = Mockito.mock(LispNode.class);
        Mockito.when(((ExpressionNode)params).getAddress()).thenReturn(address);

        ExpressionNode expressionNodeAddress = Mockito.mock(ExpressionNode.class);
        Mockito.when(
            listValueRetriever.retrieveListValue(
                address,
                FunctionNameConstants.COND,
                variableNameToValueMap
            )
        ).thenReturn(expressionNodeAddress);

        LispNode expressionNodeAddressData = Mockito.mock(LispNode.class);
        Mockito.when(expressionNodeAddress.getData()).thenReturn(expressionNodeAddressData);


        AtomNode data = Mockito.mock(AtomNode.class);
        Mockito.when(((ExpressionNode)params).getData()).thenReturn(data);
        Mockito.when(data.getValue()).thenReturn(ReservedValuesConstants.NIL);

        Mockito.when(nodeValueComparator.equalsNil(ReservedValuesConstants.NIL)).thenReturn(true);

        Assertions.assertDoesNotThrow(
            () -> condFunctionParameterAsserter.assertCondFunctionParameters(
                params,
                variableNameToValueMap
            )
        );

        Mockito.verify(functionLengthAsserter).assertLengthIsAsExpected(
            FunctionNameConstants.COND,
            FunctionLengthConstants.TWO,
            expressionNodeAddressData
        );
    }
}
