package com.soapdogg.lispInterpreter.function;

import com.soapdogg.lispInterpreter.asserter.FunctionLengthAsserter;
import com.soapdogg.lispInterpreter.constants.FunctionLengthConstants;
import com.soapdogg.lispInterpreter.constants.FunctionNameConstants;
import com.soapdogg.lispInterpreter.datamodels.UserDefinedFunction;
import com.soapdogg.lispInterpreter.evaluator.NodeEvaluator;
import com.soapdogg.lispInterpreter.functions.EqFunction;
import com.soapdogg.lispInterpreter.generator.NodeGenerator;
import com.soapdogg.lispInterpreter.datamodels.AtomNode;
import com.soapdogg.lispInterpreter.datamodels.ExpressionNode;
import com.soapdogg.lispInterpreter.datamodels.Node;
import com.soapdogg.lispInterpreter.valueretriver.AtomicValueRetriever;
import com.soapdogg.lispInterpreter.valueretriver.ListValueRetriever;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;
import java.util.Map;

class EqFunctionTest {

    private Node params;
    private List<UserDefinedFunction> userDefinedFunctions;
    private Map<String, Node> variableNameToValueMap;

    private FunctionLengthAsserter functionLengthAsserter;
    private NodeEvaluator nodeEvaluator;
    private AtomicValueRetriever atomicValueRetriever;
    private ListValueRetriever listValueRetriever;
    private NodeGenerator nodeGenerator;

    private EqFunction eqFunction;

    @BeforeEach
    void setup() {
        params = Mockito.mock(Node.class);
        userDefinedFunctions = Collections.emptyList();
        variableNameToValueMap = Collections.emptyMap();

        functionLengthAsserter = Mockito.mock(FunctionLengthAsserter.class);
        nodeEvaluator = Mockito.mock(NodeEvaluator.class);
        atomicValueRetriever = Mockito.mock(AtomicValueRetriever.class);
        listValueRetriever = Mockito.mock(ListValueRetriever.class);
        nodeGenerator = Mockito.mock(NodeGenerator.class);

        eqFunction = EqFunction.newInstance(
            functionLengthAsserter,
            nodeEvaluator,
            atomicValueRetriever,
            listValueRetriever,
            nodeGenerator
        );
    }

    @Test
    void eqFunctionTest() throws Exception {
        Node evaluatedAddress = Mockito.mock(Node.class);
        Mockito.when(
            nodeEvaluator.evaluate(
                params,
                userDefinedFunctions,
                variableNameToValueMap,
                true
            )
        ).thenReturn(evaluatedAddress);

        String leftValue = "leftValue";
        Mockito.when(
            atomicValueRetriever.retrieveAtomicValue(
                evaluatedAddress,
                1,
                FunctionNameConstants.EQ
            )
        ).thenReturn(leftValue);

        ExpressionNode expressionNodeParams = Mockito.mock(ExpressionNode.class);
        Mockito.when(
            listValueRetriever.retrieveListValue(
                params,
                FunctionNameConstants.EQ,
                variableNameToValueMap
            )
        ).thenReturn(expressionNodeParams);

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

        String rightValue = "rightValue";
        Mockito.when(
            atomicValueRetriever.retrieveAtomicValue(
                evaluatedData,
                2,
                FunctionNameConstants.EQ
            )
        ).thenReturn(rightValue);

        AtomNode expected = Mockito.mock(AtomNode.class);
        Mockito.when(
            nodeGenerator.generateAtomNode(
                leftValue.equals(rightValue)
            )
        ).thenReturn(expected);

        Node actual = eqFunction.evaluateLispFunction(
            params,
            userDefinedFunctions,
            variableNameToValueMap
        );

        Assertions.assertEquals(expected, actual);
        Mockito.verify(functionLengthAsserter).assertLengthIsAsExpected(
            FunctionNameConstants.EQ,
            FunctionLengthConstants.THREE,
            params
        );
    }
}
