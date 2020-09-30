package com.soapdogg.lispInterpreter.function;

import com.soapdogg.lispInterpreter.asserter.FunctionLengthAsserter;
import com.soapdogg.lispInterpreter.constants.FunctionLengthConstants;
import com.soapdogg.lispInterpreter.constants.FunctionNameConstants;
import com.soapdogg.lispInterpreter.datamodels.UserDefinedFunction;
import com.soapdogg.lispInterpreter.evaluator.NodeEvaluator;
import com.soapdogg.lispInterpreter.functions.ConsFunction;
import com.soapdogg.lispInterpreter.generator.NodeGenerator;
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

class ConsFunctionTest {

    private Node params;
    private List<UserDefinedFunction> userDefinedFunctions;
    private Map<String, Node> variableNameToValueMap;

    private FunctionLengthAsserter functionLengthAsserter;
    private ListValueRetriever listValueRetriever;
    private NodeEvaluator nodeEvaluator;
    private NodeGenerator nodeGenerator;

    private ConsFunction consFunction;

    @BeforeEach
    void setup() {
        params = Mockito.mock(Node.class);
        userDefinedFunctions = Collections.emptyList();
        variableNameToValueMap = Collections.emptyMap();

        functionLengthAsserter = Mockito.mock(FunctionLengthAsserter.class);
        listValueRetriever = Mockito.mock(ListValueRetriever.class);
        nodeEvaluator = Mockito.mock(NodeEvaluator.class);
        nodeGenerator = Mockito.mock(NodeGenerator.class);

        consFunction = ConsFunction.newInstance(
            functionLengthAsserter,
            listValueRetriever,
            nodeEvaluator,
            nodeGenerator
        );
    }

    @Test
    void consFunctionTest() throws Exception {
        ExpressionNode expressionNodeParams = Mockito.mock(ExpressionNode.class);
        Mockito.when(
            listValueRetriever.retrieveListValue(
                params,
                FunctionNameConstants.CONS,
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
                true
            )
        ).thenReturn(evaluatedAddress);

        Node data = Mockito.mock(Node.class);
        Mockito.when(expressionNodeParams.getData()).thenReturn(data);

        Node evaluatedData = Mockito.mock(Node.class);
        Mockito.when(
            nodeEvaluator.evaluate(
                data,
                userDefinedFunctions,
                variableNameToValueMap,
                true
            )
        ).thenReturn(evaluatedData);

        ExpressionNode expected = Mockito.mock(ExpressionNode.class);
        Mockito.when(
            nodeGenerator.generateExpressionNode(
                evaluatedAddress,
                evaluatedData
            )
        ).thenReturn(expected);

        Node actual = consFunction.evaluateLispFunction(
            params,
            userDefinedFunctions,
            variableNameToValueMap
        );

        Assertions.assertEquals(expected, actual);

        Mockito.verify(functionLengthAsserter).assertLengthIsAsExpected(
            FunctionNameConstants.CONS,
            FunctionLengthConstants.THREE,
            params
        );

    }
}
