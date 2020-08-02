package edu.osu.cse6341.lispInterpreter.function;

import edu.osu.cse6341.lispInterpreter.asserter.FunctionLengthAsserter;
import edu.osu.cse6341.lispInterpreter.constants.FunctionLengthConstants;
import edu.osu.cse6341.lispInterpreter.constants.FunctionNameConstants;
import edu.osu.cse6341.lispInterpreter.constants.ReservedValuesConstants;
import edu.osu.cse6341.lispInterpreter.determiner.ExpressionNodeDeterminer;
import edu.osu.cse6341.lispInterpreter.determiner.NumericStringDeterminer;
import edu.osu.cse6341.lispInterpreter.evaluator.NodeEvaluator;
import edu.osu.cse6341.lispInterpreter.functions.IntFunction;
import edu.osu.cse6341.lispInterpreter.generator.NodeGenerator;
import edu.osu.cse6341.lispInterpreter.nodes.AtomNode;
import edu.osu.cse6341.lispInterpreter.nodes.LispNode;
import edu.osu.cse6341.lispInterpreter.valueretriver.AtomicValueRetriever;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class IntFunctionTest {
    private LispNode params;

    private FunctionLengthAsserter functionLengthAsserter;
    private NodeEvaluator nodeEvaluator;
    private ExpressionNodeDeterminer expressionNodeDeterminer;
    private AtomicValueRetriever atomicValueRetriever;
    private NumericStringDeterminer numericStringDeterminer;
    private NodeGenerator nodeGenerator;

    private IntFunction intFunction;

    @BeforeEach
    void setup() {
        params = Mockito.mock(LispNode.class);

        functionLengthAsserter = Mockito.mock(FunctionLengthAsserter.class);
        nodeEvaluator = Mockito.mock(NodeEvaluator.class);
        expressionNodeDeterminer = Mockito.mock(ExpressionNodeDeterminer.class);
        atomicValueRetriever = Mockito.mock(AtomicValueRetriever.class);
        numericStringDeterminer = Mockito.mock(NumericStringDeterminer.class);
        nodeGenerator = Mockito.mock(NodeGenerator.class);

        intFunction = IntFunction.newInstance(
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
        LispNode evaluatedResult = Mockito.mock(LispNode.class);
        Mockito.when(
            nodeEvaluator.evaluate(
                params,
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

        LispNode actual = intFunction.evaluateLispFunction(params);

        Assertions.assertEquals(expected, actual);
        Mockito.verify(functionLengthAsserter).assertLengthIsAsExpected(
            FunctionNameConstants.INT,
            FunctionLengthConstants.TWO,
            params
        );
    }

    @Test
    void intFunctionIsListTest() throws Exception {
        LispNode evaluatedResult = Mockito.mock(LispNode.class);
        Mockito.when(
            nodeEvaluator.evaluate(
                params,
                true
            )
        ).thenReturn(evaluatedResult);

        Mockito.when(expressionNodeDeterminer.isExpressionNode(evaluatedResult)).thenReturn(true);

        AtomNode expected = Mockito.mock(AtomNode.class);
        Mockito.when(nodeGenerator.generateAtomNode(false)).thenReturn(expected);


        LispNode actual = intFunction.evaluateLispFunction(params);
        Assertions.assertEquals(expected, actual);
        Mockito.verify(functionLengthAsserter).assertLengthIsAsExpected(
            FunctionNameConstants.INT,
            FunctionLengthConstants.TWO,
            params
        );

        Mockito.verifyZeroInteractions(atomicValueRetriever);
        Mockito.verifyZeroInteractions(numericStringDeterminer);
    }
}
