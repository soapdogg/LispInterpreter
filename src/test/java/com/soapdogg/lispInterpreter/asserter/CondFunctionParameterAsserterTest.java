package com.soapdogg.lispInterpreter.asserter;

import com.soapdogg.lispInterpreter.comparator.NodeValueComparator;
import com.soapdogg.lispInterpreter.constants.FunctionLengthConstants;
import com.soapdogg.lispInterpreter.constants.FunctionNameConstants;
import com.soapdogg.lispInterpreter.constants.ReservedValuesConstants;
import com.soapdogg.lispInterpreter.exceptions.NotAListException;
import com.soapdogg.lispInterpreter.datamodels.AtomNode;
import com.soapdogg.lispInterpreter.datamodels.ExpressionNode;
import com.soapdogg.lispInterpreter.datamodels.Node;
import com.soapdogg.lispInterpreter.valueretriver.ListValueRetriever;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.Map;

class CondFunctionParameterAsserterTest {

    private Node params;
    private Map<String, Node> variableNameToValueMap;

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

        Mockito.verifyNoInteractions(listValueRetriever);
        Mockito.verifyNoInteractions(functionLengthAsserter);
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

        Mockito.verifyNoInteractions(listValueRetriever);
        Mockito.verifyNoInteractions(functionLengthAsserter);
    }

    @Test
    void inputIsListTest() throws Exception {
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

        Node expressionNodeAddressData = Mockito.mock(Node.class);
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
