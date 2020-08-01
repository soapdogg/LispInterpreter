package edu.osu.cse6341.lispInterpreter.function;

import edu.osu.cse6341.lispInterpreter.asserter.FunctionLengthAsserter;
import edu.osu.cse6341.lispInterpreter.constants.FunctionLengthConstants;
import edu.osu.cse6341.lispInterpreter.constants.FunctionNameConstants;
import edu.osu.cse6341.lispInterpreter.functions.QuoteFunction;
import edu.osu.cse6341.lispInterpreter.nodes.ExpressionNode;
import edu.osu.cse6341.lispInterpreter.nodes.LispNode;
import edu.osu.cse6341.lispInterpreter.valueretriver.ListValueRetriever;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class QuoteFunctionTest {

    private LispNode params;

    private FunctionLengthAsserter functionLengthAsserter;
    private ListValueRetriever listValueRetriever;

    private QuoteFunction quoteFunction;

    @BeforeEach
    void setup() {
        params = Mockito.mock(ExpressionNode.class);

        functionLengthAsserter = Mockito.mock(FunctionLengthAsserter.class);
        listValueRetriever = Mockito.mock(ListValueRetriever.class);

        quoteFunction = QuoteFunction.newInstance(
            functionLengthAsserter,
            listValueRetriever
        );
    }

    @Test
    void quoteFunctionTest() throws Exception {
        ExpressionNode expressionNodeParams = Mockito.mock(ExpressionNode.class);
        Mockito.when(
            listValueRetriever.retrieveListValue(
                params,
                FunctionNameConstants.QUOTE
            )
        ).thenReturn(expressionNodeParams);

        LispNode address = Mockito.mock(LispNode.class);
        Mockito.when(expressionNodeParams.getAddress()).thenReturn(address);

        LispNode actual = quoteFunction.evaluateLispFunction(params);

        Assertions.assertEquals(address, actual);
        Mockito.verify(
            functionLengthAsserter
        ).assertLengthIsAsExpected(
            FunctionNameConstants.QUOTE,
            FunctionLengthConstants.TWO,
            params
        );
    }
}
