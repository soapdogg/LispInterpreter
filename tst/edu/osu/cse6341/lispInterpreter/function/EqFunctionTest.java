package edu.osu.cse6341.lispInterpreter.function;

import edu.osu.cse6341.lispInterpreter.asserter.FunctionLengthAsserter;
import edu.osu.cse6341.lispInterpreter.constants.FunctionLengthConstants;
import edu.osu.cse6341.lispInterpreter.constants.FunctionNameConstants;
import edu.osu.cse6341.lispInterpreter.evaluator.NodeEvaluator;
import edu.osu.cse6341.lispInterpreter.functions.EqFunction;
import edu.osu.cse6341.lispInterpreter.generator.NodeGenerator;
import edu.osu.cse6341.lispInterpreter.nodes.AtomNode;
import edu.osu.cse6341.lispInterpreter.nodes.ExpressionNode;
import edu.osu.cse6341.lispInterpreter.nodes.LispNode;
import edu.osu.cse6341.lispInterpreter.valueretriver.AtomicValueRetriever;
import edu.osu.cse6341.lispInterpreter.valueretriver.ListValueRetriever;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class EqFunctionTest {

    private LispNode params;

    private FunctionLengthAsserter functionLengthAsserter;
    private NodeEvaluator nodeEvaluator;
    private AtomicValueRetriever atomicValueRetriever;
    private ListValueRetriever listValueRetriever;
    private NodeGenerator nodeGenerator;

    private EqFunction eqFunction;

    @BeforeEach
    void setup() {
        params = Mockito.mock(LispNode.class);

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
        LispNode evaluatedAddress = Mockito.mock(LispNode.class);
        Mockito.when(
            nodeEvaluator.evaluate(
                params,
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
                FunctionNameConstants.EQ
            )
        ).thenReturn(expressionNodeParams);

        LispNode data = Mockito.mock(LispNode.class);
        Mockito.when(expressionNodeParams.getData()).thenReturn(data);

        LispNode evaluatedData = Mockito.mock(LispNode.class);
        Mockito.when(
            nodeEvaluator.evaluate(
                data,
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

        LispNode actual = eqFunction.evaluateLispFunction(params);

        Assertions.assertEquals(expected, actual);
        Mockito.verify(functionLengthAsserter).assertLengthIsAsExpected(
            FunctionNameConstants.EQ,
            FunctionLengthConstants.THREE,
            params
        );
    }
}
