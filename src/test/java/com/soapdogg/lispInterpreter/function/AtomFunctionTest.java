package com.soapdogg.lispInterpreter.function;

import com.soapdogg.lispInterpreter.asserter.FunctionLengthAsserter;
import com.soapdogg.lispInterpreter.constants.FunctionLengthConstants;
import com.soapdogg.lispInterpreter.constants.FunctionNameConstants;
import com.soapdogg.lispInterpreter.datamodels.UserDefinedFunction;
import com.soapdogg.lispInterpreter.determiner.ExpressionNodeDeterminer;
import com.soapdogg.lispInterpreter.evaluator.NodeEvaluator;
import com.soapdogg.lispInterpreter.functions.AtomFunction;
import com.soapdogg.lispInterpreter.generator.NodeGenerator;
import com.soapdogg.lispInterpreter.datamodels.AtomNode;
import com.soapdogg.lispInterpreter.datamodels.Node;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;
import java.util.Map;

class AtomFunctionTest {

    private Node params;
    private List<UserDefinedFunction> userDefinedFunctions;
    private Map<String, Node> variableNameToValueMap;

    private FunctionLengthAsserter functionLengthAsserter;
    private NodeEvaluator nodeEvaluator;
    private ExpressionNodeDeterminer expressionNodeDeterminer;
    private NodeGenerator nodeGenerator;

    private AtomFunction atomFunction;

    @BeforeEach
    void setup() {
        params = Mockito.mock(Node.class);
        userDefinedFunctions = Collections.emptyList();
        variableNameToValueMap = Collections.emptyMap();

        functionLengthAsserter = Mockito.mock(FunctionLengthAsserter.class);
        nodeEvaluator = Mockito.mock(NodeEvaluator.class);
        expressionNodeDeterminer = Mockito.mock(ExpressionNodeDeterminer.class);
        nodeGenerator = Mockito.mock(NodeGenerator.class);

        atomFunction = new AtomFunction(
            functionLengthAsserter,
            nodeEvaluator,
            expressionNodeDeterminer,
            nodeGenerator
        );
    }

    @Test
    void atomFunctionTest() throws Exception {
        Node evaluatedResult = Mockito.mock(Node.class);
        Mockito.when(
            nodeEvaluator.evaluate(
                params,
                userDefinedFunctions,
                variableNameToValueMap,
                true
            )
        ).thenReturn(evaluatedResult);

        boolean result = true;
        Mockito.when(expressionNodeDeterminer.isExpressionNode(evaluatedResult)).thenReturn(result);

        AtomNode expected = Mockito.mock(AtomNode.class);
        Mockito.when(nodeGenerator.generateAtomNode(!result)).thenReturn(expected);

        Node actual = atomFunction.evaluateLispFunction(
            params,
            userDefinedFunctions,
            variableNameToValueMap
        );

        Assertions.assertEquals(expected, actual);
        Mockito.verify(functionLengthAsserter).assertLengthIsAsExpected(
            FunctionNameConstants.ATOM,
            FunctionLengthConstants.TWO,
            params
        );
    }

    @Test
    void atomFunctionFalseTest() throws Exception {
        Node evaluatedResult = Mockito.mock(Node.class);
        Mockito.when(
            nodeEvaluator.evaluate(
                params,
                userDefinedFunctions,
                variableNameToValueMap,
                true
            )
        ).thenReturn(evaluatedResult);

        boolean result = false;
        Mockito.when(expressionNodeDeterminer.isExpressionNode(evaluatedResult)).thenReturn(result);

        AtomNode expected = Mockito.mock(AtomNode.class);
        Mockito.when(nodeGenerator.generateAtomNode(!result)).thenReturn(expected);

        Node actual = atomFunction.evaluateLispFunction(
            params,
            userDefinedFunctions,
            variableNameToValueMap
        );

        Assertions.assertEquals(expected, actual);
        Mockito.verify(functionLengthAsserter).assertLengthIsAsExpected(
            FunctionNameConstants.ATOM,
            FunctionLengthConstants.TWO,
            params
        );
    }
}
