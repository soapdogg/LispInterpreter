package edu.osu.cse6341.lispInterpreter.function;

import edu.osu.cse6341.lispInterpreter.asserter.FunctionLengthAsserter;
import edu.osu.cse6341.lispInterpreter.constants.FunctionLengthConstants;
import edu.osu.cse6341.lispInterpreter.constants.FunctionNameConstants;
import edu.osu.cse6341.lispInterpreter.evaluator.NodeEvaluator;
import edu.osu.cse6341.lispInterpreter.functions.CdrFunction;
import edu.osu.cse6341.lispInterpreter.nodes.ExpressionNode;
import edu.osu.cse6341.lispInterpreter.nodes.LispNode;
import edu.osu.cse6341.lispInterpreter.valueretriver.ListValueRetriever;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class CdrFunctionTest {

    private LispNode params;

    private FunctionLengthAsserter functionLengthAsserter;
    private ListValueRetriever listValueRetriever;
    private NodeEvaluator nodeEvaluator;

    private CdrFunction cdrFunction;

    @BeforeEach
    void setup() {
        params = Mockito.mock(ExpressionNode.class);

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
                FunctionNameConstants.CDR
            )
        ).thenReturn(expressionNodeParams);

        LispNode address = Mockito.mock(LispNode.class);
        Mockito.when(expressionNodeParams.getAddress()).thenReturn(address);

        LispNode evaluatedAddress = Mockito.mock(LispNode.class);
        Mockito.when(
            nodeEvaluator.evaluate(
                address,
                false
            )
        ).thenReturn(evaluatedAddress);

        ExpressionNode node = Mockito.mock(ExpressionNode.class);
        Mockito.when(
            listValueRetriever.retrieveListValue(
                evaluatedAddress,
                FunctionNameConstants.CDR
            )
        ).thenReturn(node);

        LispNode expected = Mockito.mock(LispNode.class);
        Mockito.when(node.getData()).thenReturn(expected);

        LispNode actual = cdrFunction.evaluateLispFunction(
            params
        );

        Assertions.assertEquals(expected, actual);
        Mockito.verify(functionLengthAsserter).assertLengthIsAsExpected(
            FunctionNameConstants.CDR,
            FunctionLengthConstants.TWO,
            params
        );
    }
}
