package com.soapdogg.lispInterpreter.function;

import com.soapdogg.lispInterpreter.asserter.FunctionLengthAsserter;
import com.soapdogg.lispInterpreter.constants.FunctionLengthConstants;
import com.soapdogg.lispInterpreter.constants.FunctionNameConstants;
import com.soapdogg.lispInterpreter.datamodels.UserDefinedFunction;
import com.soapdogg.lispInterpreter.evaluator.NodeEvaluator;
import com.soapdogg.lispInterpreter.functions.CdrFunction;
import com.soapdogg.lispInterpreter.datamodels.ExpressionNode;
import com.soapdogg.lispInterpreter.datamodels.Node;
import com.soapdogg.lispInterpreter.valueretriver.ListValueRetriever;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;
import java.util.Map;

class CdrFunctionTest {

    private Node params;
    private List<UserDefinedFunction> userDefinedFunctions;
    private Map<String, Node> variableNameToValueMap;

    private FunctionLengthAsserter functionLengthAsserter;
    private ListValueRetriever listValueRetriever;
    private NodeEvaluator nodeEvaluator;

    private CdrFunction cdrFunction;

    @BeforeEach
    void setup() {
        params = Mockito.mock(ExpressionNode.class);
        userDefinedFunctions = Collections.emptyList();
        variableNameToValueMap = Collections.emptyMap();

        functionLengthAsserter = Mockito.mock(FunctionLengthAsserter.class);
        listValueRetriever = Mockito.mock(ListValueRetriever.class);
        nodeEvaluator = Mockito.mock(NodeEvaluator.class);

        cdrFunction = CdrFunction.newInstance(
            functionLengthAsserter,
            listValueRetriever,
            nodeEvaluator
        );
    }

    @Test
    void carFunctionTest() throws Exception {
        ExpressionNode expressionNodeParams = Mockito.mock(ExpressionNode.class);
        Mockito.when(
            listValueRetriever.retrieveListValue(
                params,
                FunctionNameConstants.CDR,
                variableNameToValueMap
            )
        ).thenReturn(expressionNodeParams);

        Node address = Mockito.mock(Node.class);
        Mockito.when(expressionNodeParams.getAddress()).thenReturn(address);

        Node evaluatedAddress = Mockito.mock(Node.class);
        Mockito.when(
            nodeEvaluator.evaluate(
                address,
                userDefinedFunctions,
                variableNameToValueMap,
                false
            )
        ).thenReturn(evaluatedAddress);

        ExpressionNode node = Mockito.mock(ExpressionNode.class);
        Mockito.when(
            listValueRetriever.retrieveListValue(
                evaluatedAddress,
                FunctionNameConstants.CDR,
                variableNameToValueMap
            )
        ).thenReturn(node);

        Node expected = Mockito.mock(Node.class);
        Mockito.when(node.getData()).thenReturn(expected);

        Node actual = cdrFunction.evaluateLispFunction(
            params,
            userDefinedFunctions,
            variableNameToValueMap
        );

        Assertions.assertEquals(expected, actual);
        Mockito.verify(functionLengthAsserter).assertLengthIsAsExpected(
            FunctionNameConstants.CDR,
            FunctionLengthConstants.TWO,
            params
        );
    }
}
