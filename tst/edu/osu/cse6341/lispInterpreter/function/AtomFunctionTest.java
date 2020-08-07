package edu.osu.cse6341.lispInterpreter.function;

import edu.osu.cse6341.lispInterpreter.asserter.FunctionLengthAsserter;
import edu.osu.cse6341.lispInterpreter.constants.FunctionLengthConstants;
import edu.osu.cse6341.lispInterpreter.constants.FunctionNameConstants;
import edu.osu.cse6341.lispInterpreter.datamodels.UserDefinedFunction;
import edu.osu.cse6341.lispInterpreter.determiner.ExpressionNodeDeterminer;
import edu.osu.cse6341.lispInterpreter.evaluator.NodeEvaluator;
import edu.osu.cse6341.lispInterpreter.functions.AtomFunction;
import edu.osu.cse6341.lispInterpreter.generator.NodeGenerator;
import edu.osu.cse6341.lispInterpreter.nodes.AtomNode;
import edu.osu.cse6341.lispInterpreter.nodes.LispNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;

class AtomFunctionTest {

    private LispNode params;
    private List<UserDefinedFunction> userDefinedFunctions;

    private FunctionLengthAsserter functionLengthAsserter;
    private NodeEvaluator nodeEvaluator;
    private ExpressionNodeDeterminer expressionNodeDeterminer;
    private NodeGenerator nodeGenerator;

    private AtomFunction atomFunction;

    @BeforeEach
    void setup() {
        params = Mockito.mock(LispNode.class);
        userDefinedFunctions = Collections.emptyList();

        functionLengthAsserter = Mockito.mock(FunctionLengthAsserter.class);
        nodeEvaluator = Mockito.mock(NodeEvaluator.class);
        expressionNodeDeterminer = Mockito.mock(ExpressionNodeDeterminer.class);
        nodeGenerator = Mockito.mock(NodeGenerator.class);

        atomFunction = AtomFunction.newInstance(
            functionLengthAsserter,
            nodeEvaluator,
            expressionNodeDeterminer,
            nodeGenerator
        );
    }

    @Test
    void atomFunctionTest() throws Exception {
        LispNode evaluatedResult = Mockito.mock(LispNode.class);
        Mockito.when(
            nodeEvaluator.evaluate(
                params,
                userDefinedFunctions,
                true
            )
        ).thenReturn(evaluatedResult);

        boolean result = true;
        Mockito.when(expressionNodeDeterminer.isExpressionNode(evaluatedResult)).thenReturn(result);

        AtomNode expected = Mockito.mock(AtomNode.class);
        Mockito.when(nodeGenerator.generateAtomNode(!result)).thenReturn(expected);

        LispNode actual = atomFunction.evaluateLispFunction(
            params,
            userDefinedFunctions
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
        LispNode evaluatedResult = Mockito.mock(LispNode.class);
        Mockito.when(
            nodeEvaluator.evaluate(
                params,
                userDefinedFunctions,
                true
            )
        ).thenReturn(evaluatedResult);

        boolean result = false;
        Mockito.when(expressionNodeDeterminer.isExpressionNode(evaluatedResult)).thenReturn(result);

        AtomNode expected = Mockito.mock(AtomNode.class);
        Mockito.when(nodeGenerator.generateAtomNode(!result)).thenReturn(expected);

        LispNode actual = atomFunction.evaluateLispFunction(
            params,
            userDefinedFunctions
        );

        Assertions.assertEquals(expected, actual);
        Mockito.verify(functionLengthAsserter).assertLengthIsAsExpected(
            FunctionNameConstants.ATOM,
            FunctionLengthConstants.TWO,
            params
        );
    }
}
