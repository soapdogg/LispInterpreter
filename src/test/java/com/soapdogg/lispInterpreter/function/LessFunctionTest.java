package com.soapdogg.lispInterpreter.function;

import com.soapdogg.lispInterpreter.asserter.FunctionLengthAsserter;
import com.soapdogg.lispInterpreter.constants.FunctionLengthConstants;
import com.soapdogg.lispInterpreter.constants.FunctionNameConstants;
import com.soapdogg.lispInterpreter.datamodels.UserDefinedFunction;
import com.soapdogg.lispInterpreter.evaluator.NodeEvaluator;
import com.soapdogg.lispInterpreter.functions.LessFunction;
import com.soapdogg.lispInterpreter.generator.NodeGenerator;
import com.soapdogg.lispInterpreter.datamodels.AtomNode;
import com.soapdogg.lispInterpreter.datamodels.ExpressionNode;
import com.soapdogg.lispInterpreter.datamodels.Node;
import com.soapdogg.lispInterpreter.valueretriver.ListValueRetriever;
import com.soapdogg.lispInterpreter.valueretriver.NumericValueRetriever;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;
import java.util.Map;

class LessFunctionTest {

    private Node params;
    private List<UserDefinedFunction> userDefinedFunctions;
    private Map<String, Node> variableNameToValueMap;

    private FunctionLengthAsserter functionLengthAsserter;
    private NodeEvaluator nodeEvaluator;
    private NumericValueRetriever numericValueRetriever;
    private ListValueRetriever listValueRetriever;
    private NodeGenerator nodeGenerator;

    private LessFunction lessFunction;

    @BeforeEach
    void setup() {
        params = Mockito.mock(Node.class);
        userDefinedFunctions = Collections.emptyList();
        variableNameToValueMap = Collections.emptyMap();

        functionLengthAsserter = Mockito.mock(FunctionLengthAsserter.class);
        nodeEvaluator = Mockito.mock(NodeEvaluator.class);
        numericValueRetriever = Mockito.mock(NumericValueRetriever.class);
        listValueRetriever = Mockito.mock(ListValueRetriever.class);
        nodeGenerator = Mockito.mock(NodeGenerator.class);

        lessFunction = new LessFunction(
            functionLengthAsserter,
            nodeEvaluator,
            numericValueRetriever,
            listValueRetriever,
            nodeGenerator
        );
    }

    @Test
    void lessFunctionTest() throws Exception {
        Node evaluatedAddress = Mockito.mock(Node.class);
        Mockito.when(
            nodeEvaluator.evaluate(
                params,
                userDefinedFunctions,
                variableNameToValueMap,
                true
            )
        ).thenReturn(evaluatedAddress);

        int leftValue = 3;
        Mockito.when(
            numericValueRetriever.retrieveNumericValue(
                evaluatedAddress,
                1,
                FunctionNameConstants.LESS
            )
        ).thenReturn(leftValue);

        ExpressionNode expressionNodeParams = Mockito.mock(ExpressionNode.class);
        Mockito.when(
            listValueRetriever.retrieveListValue(
                params,
                FunctionNameConstants.LESS,
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

        int rightValue = 5;
        Mockito.when(
            numericValueRetriever.retrieveNumericValue(
                evaluatedData,
                2,
                FunctionNameConstants.LESS
            )
        ).thenReturn(rightValue);

        AtomNode expected = Mockito.mock(AtomNode.class);
        Mockito.when(
            nodeGenerator.generateAtomNode(
                leftValue < rightValue
            )
        ).thenReturn(expected);

        Node actual = lessFunction.evaluateLispFunction(
            params,
            userDefinedFunctions,
            variableNameToValueMap
        );

        Assertions.assertEquals(expected, actual);
        Mockito.verify(functionLengthAsserter).assertLengthIsAsExpected(
            FunctionNameConstants.LESS,
            FunctionLengthConstants.THREE,
            params
        );

    }

    @Test
    void lessFunctionFalseTest() throws Exception {
        Node evaluatedAddress = Mockito.mock(Node.class);
        Mockito.when(
            nodeEvaluator.evaluate(
                params,
                userDefinedFunctions,
                variableNameToValueMap,
                true
            )
        ).thenReturn(evaluatedAddress);

        int leftValue = 3;
        Mockito.when(
            numericValueRetriever.retrieveNumericValue(
                evaluatedAddress,
                1,
                FunctionNameConstants.LESS
            )
        ).thenReturn(leftValue);

        ExpressionNode expressionNodeParams = Mockito.mock(ExpressionNode.class);
        Mockito.when(
            listValueRetriever.retrieveListValue(
                params,
                FunctionNameConstants.LESS,
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

        int rightValue = 1;
        Mockito.when(
            numericValueRetriever.retrieveNumericValue(
                evaluatedData,
                2,
                FunctionNameConstants.LESS
            )
        ).thenReturn(rightValue);

        AtomNode expected = Mockito.mock(AtomNode.class);
        Mockito.when(
            nodeGenerator.generateAtomNode(
                leftValue < rightValue
            )
        ).thenReturn(expected);

        Node actual = lessFunction.evaluateLispFunction(
            params,
            userDefinedFunctions,
            variableNameToValueMap
        );

        Assertions.assertEquals(expected, actual);
        Mockito.verify(functionLengthAsserter).assertLengthIsAsExpected(
            FunctionNameConstants.LESS,
            FunctionLengthConstants.THREE,
            params
        );

    }
}
