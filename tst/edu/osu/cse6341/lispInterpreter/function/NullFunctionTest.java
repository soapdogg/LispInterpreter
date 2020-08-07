package edu.osu.cse6341.lispInterpreter.function;

import edu.osu.cse6341.lispInterpreter.asserter.FunctionLengthAsserter;
import edu.osu.cse6341.lispInterpreter.comparator.NodeValueComparator;
import edu.osu.cse6341.lispInterpreter.constants.FunctionLengthConstants;
import edu.osu.cse6341.lispInterpreter.constants.FunctionNameConstants;
import edu.osu.cse6341.lispInterpreter.constants.ReservedValuesConstants;
import edu.osu.cse6341.lispInterpreter.datamodels.UserDefinedFunction;
import edu.osu.cse6341.lispInterpreter.determiner.ExpressionNodeDeterminer;
import edu.osu.cse6341.lispInterpreter.evaluator.NodeEvaluator;
import edu.osu.cse6341.lispInterpreter.functions.NullFunction;
import edu.osu.cse6341.lispInterpreter.generator.NodeGenerator;
import edu.osu.cse6341.lispInterpreter.nodes.AtomNode;
import edu.osu.cse6341.lispInterpreter.nodes.LispNode;
import edu.osu.cse6341.lispInterpreter.valueretriver.AtomicValueRetriever;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;

class NullFunctionTest {

    private LispNode params;
    private List<UserDefinedFunction> userDefinedFunctions;

    private FunctionLengthAsserter functionLengthAsserter;
    private NodeEvaluator nodeEvaluator;
    private ExpressionNodeDeterminer expressionNodeDeterminer;
    private AtomicValueRetriever atomicValueRetriever;
    private NodeValueComparator nodeValueComparator;
    private NodeGenerator nodeGenerator;

    private NullFunction nullFunction;

    @BeforeEach
    void setup() {
        params = Mockito.mock(LispNode.class);

        userDefinedFunctions = Collections.emptyList();

        functionLengthAsserter = Mockito.mock(FunctionLengthAsserter.class);
        nodeEvaluator = Mockito.mock(NodeEvaluator.class);
        expressionNodeDeterminer = Mockito.mock(ExpressionNodeDeterminer.class);
        atomicValueRetriever = Mockito.mock(AtomicValueRetriever.class);
        nodeValueComparator = Mockito.mock(NodeValueComparator.class);
        nodeGenerator = Mockito.mock(NodeGenerator.class);

        nullFunction = NullFunction.newInstance(
            functionLengthAsserter,
            nodeEvaluator,
            expressionNodeDeterminer,
            atomicValueRetriever,
            nodeValueComparator,
            nodeGenerator
        );
    }

    @Test
    void nullFunctionTest() throws Exception {
        LispNode evaluatedResult = Mockito.mock(LispNode.class);
        Mockito.when(
            nodeEvaluator.evaluate(
                params,
                userDefinedFunctions,
                true
            )
        ).thenReturn(evaluatedResult);

        Mockito.when(expressionNodeDeterminer.isExpressionNode(evaluatedResult)).thenReturn(false);

        String value = ReservedValuesConstants.NIL;
        Mockito.when(
            atomicValueRetriever.retrieveAtomicValue(
                evaluatedResult,
                1,
                FunctionNameConstants.NULL
            )
        ).thenReturn(value);

        boolean result = true;
        Mockito.when(nodeValueComparator.equalsNil(value)).thenReturn(result);

        AtomNode expected = Mockito.mock(AtomNode.class);
        Mockito.when(nodeGenerator.generateAtomNode(result)).thenReturn(expected);

        LispNode actual = nullFunction.evaluateLispFunction(params, userDefinedFunctions);

        Assertions.assertEquals(expected, actual);
        Mockito.verify(functionLengthAsserter).assertLengthIsAsExpected(
            FunctionNameConstants.NULL,
            FunctionLengthConstants.TWO,
            params
        );
    }

    @Test
    void nullFunctionIsListTest() throws Exception {
        LispNode evaluatedResult = Mockito.mock(LispNode.class);
        Mockito.when(
            nodeEvaluator.evaluate(
                params,
                userDefinedFunctions,
                true
            )
        ).thenReturn(evaluatedResult);

        Mockito.when(expressionNodeDeterminer.isExpressionNode(evaluatedResult)).thenReturn(true);
        AtomNode expected = Mockito.mock(AtomNode.class);
        Mockito.when(nodeGenerator.generateAtomNode(false)).thenReturn(expected);

        LispNode actual = nullFunction.evaluateLispFunction(
            params,
            userDefinedFunctions
        );
        Assertions.assertEquals(expected, actual);
        Mockito.verify(functionLengthAsserter).assertLengthIsAsExpected(
            FunctionNameConstants.NULL,
            FunctionLengthConstants.TWO,
            params
        );

        Mockito.verifyZeroInteractions(atomicValueRetriever);
        Mockito.verifyZeroInteractions(nodeValueComparator);
    }
}
