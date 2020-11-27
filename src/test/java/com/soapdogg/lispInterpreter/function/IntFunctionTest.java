package com.soapdogg.lispInterpreter.function;

import com.soapdogg.lispInterpreter.asserter.FunctionLengthAsserter;
import com.soapdogg.lispInterpreter.constants.FunctionLengthConstants;
import com.soapdogg.lispInterpreter.constants.FunctionNameConstants;
import com.soapdogg.lispInterpreter.constants.ReservedValuesConstants;
import com.soapdogg.lispInterpreter.datamodels.UserDefinedFunction;
import com.soapdogg.lispInterpreter.determiner.ExpressionNodeDeterminer;
import com.soapdogg.lispInterpreter.determiner.NumericStringDeterminer;
import com.soapdogg.lispInterpreter.evaluator.NodeEvaluator;
import com.soapdogg.lispInterpreter.functions.IntFunction;
import com.soapdogg.lispInterpreter.generator.NodeGenerator;
import com.soapdogg.lispInterpreter.datamodels.AtomNode;
import com.soapdogg.lispInterpreter.datamodels.Node;
import com.soapdogg.lispInterpreter.valueretriver.AtomicValueRetriever;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;
import java.util.Map;

class IntFunctionTest {
    private Node params;
    private List<UserDefinedFunction> userDefinedFunctions;
    private Map<String, Node> variableNameToValueMap;

    private FunctionLengthAsserter functionLengthAsserter;
    private NodeEvaluator nodeEvaluator;
    private ExpressionNodeDeterminer expressionNodeDeterminer;
    private AtomicValueRetriever atomicValueRetriever;
    private NumericStringDeterminer numericStringDeterminer;
    private NodeGenerator nodeGenerator;

    private IntFunction intFunction;

    @BeforeEach
    void setup() {
        params = Mockito.mock(Node.class);
        userDefinedFunctions = Collections.emptyList();
        variableNameToValueMap = Collections.emptyMap();

        functionLengthAsserter = Mockito.mock(FunctionLengthAsserter.class);
        nodeEvaluator = Mockito.mock(NodeEvaluator.class);
        expressionNodeDeterminer = Mockito.mock(ExpressionNodeDeterminer.class);
        atomicValueRetriever = Mockito.mock(AtomicValueRetriever.class);
        numericStringDeterminer = Mockito.mock(NumericStringDeterminer.class);
        nodeGenerator = Mockito.mock(NodeGenerator.class);

        intFunction = new IntFunction(
            functionLengthAsserter,
            nodeEvaluator,
            expressionNodeDeterminer,
            atomicValueRetriever,
            numericStringDeterminer,
            nodeGenerator
        );
    }

    @Test
    void intFunctionTest() throws Exception {
        Node evaluatedResult = Mockito.mock(Node.class);
        Mockito.when(
            nodeEvaluator.evaluate(
                params,
                userDefinedFunctions,
                variableNameToValueMap,
                true
            )
        ).thenReturn(evaluatedResult);

        Mockito.when(expressionNodeDeterminer.isExpressionNode(evaluatedResult)).thenReturn(false);

        String value = ReservedValuesConstants.NIL;
        Mockito.when(
            atomicValueRetriever.retrieveAtomicValue(
                evaluatedResult,
                1,
                FunctionNameConstants.INT
            )
        ).thenReturn(value);

        boolean result = true;
        Mockito.when(numericStringDeterminer.isStringNumeric(value)).thenReturn(result);

        AtomNode expected = Mockito.mock(AtomNode.class);
        Mockito.when(nodeGenerator.generateAtomNode(result)).thenReturn(expected);

        Node actual = intFunction.evaluateLispFunction(
            params,
            userDefinedFunctions,
            variableNameToValueMap
        );

        Assertions.assertEquals(expected, actual);
        Mockito.verify(functionLengthAsserter).assertLengthIsAsExpected(
            FunctionNameConstants.INT,
            FunctionLengthConstants.TWO,
            params
        );
    }

    @Test
    void intFunctionIsListTest() throws Exception {
        Node evaluatedResult = Mockito.mock(Node.class);
        Mockito.when(
            nodeEvaluator.evaluate(
                params,
                userDefinedFunctions,
                variableNameToValueMap,
                true
            )
        ).thenReturn(evaluatedResult);

        Mockito.when(expressionNodeDeterminer.isExpressionNode(evaluatedResult)).thenReturn(true);

        AtomNode expected = Mockito.mock(AtomNode.class);
        Mockito.when(nodeGenerator.generateAtomNode(false)).thenReturn(expected);


        Node actual = intFunction.evaluateLispFunction(
            params,
            userDefinedFunctions,
            variableNameToValueMap
        );
        Assertions.assertEquals(expected, actual);
        Mockito.verify(functionLengthAsserter).assertLengthIsAsExpected(
            FunctionNameConstants.INT,
            FunctionLengthConstants.TWO,
            params
        );

        Mockito.verifyNoInteractions(atomicValueRetriever);
        Mockito.verifyNoInteractions(numericStringDeterminer);
    }
}
