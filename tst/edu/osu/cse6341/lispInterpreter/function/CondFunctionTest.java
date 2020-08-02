package edu.osu.cse6341.lispInterpreter.function;

import edu.osu.cse6341.lispInterpreter.asserter.CondFunctionParameterAsserter;
import edu.osu.cse6341.lispInterpreter.evaluator.CondFunctionEvaluator;
import edu.osu.cse6341.lispInterpreter.functions.CondFunction;
import edu.osu.cse6341.lispInterpreter.nodes.LispNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class CondFunctionTest {

    private LispNode params;

    private CondFunctionParameterAsserter condFunctionParameterAsserter;
    private CondFunctionEvaluator condFunctionEvaluator;

    private CondFunction condFunction;

    @BeforeEach
    void setup() {
        params = Mockito.mock(LispNode.class);

        condFunctionParameterAsserter = Mockito.mock(CondFunctionParameterAsserter.class);
        condFunctionEvaluator = Mockito.mock(CondFunctionEvaluator.class);

        condFunction = CondFunction.newInstance(
            condFunctionParameterAsserter,
            condFunctionEvaluator
        );
    }

    @Test
    void evaluateLispFunctionTest() throws Exception {
        LispNode expected = Mockito.mock(LispNode.class);
        Mockito.when(condFunctionEvaluator.evaluateCondFunction(params)).thenReturn(expected);

        LispNode actual = condFunction.evaluateLispFunction(
            params
        );

        Assertions.assertEquals(expected, actual);

        Mockito.verify(condFunctionParameterAsserter).assertCondFunctionParameters(
            params
        );
    }
}
