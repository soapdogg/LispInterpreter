package edu.osu.cse6341.lispInterpreter.function;

import edu.osu.cse6341.lispInterpreter.asserter.CondFunctionParameterAsserter;
import edu.osu.cse6341.lispInterpreter.datamodels.UserDefinedFunction;
import edu.osu.cse6341.lispInterpreter.evaluator.CondFunctionEvaluator;
import edu.osu.cse6341.lispInterpreter.functions.CondFunction;
import edu.osu.cse6341.lispInterpreter.nodes.LispNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;
import java.util.Map;

class CondFunctionTest {

    private LispNode params;
    private List<UserDefinedFunction> userDefinedFunctions;
    private Map<String, LispNode> variableNameToValueMap;

    private CondFunctionParameterAsserter condFunctionParameterAsserter;
    private CondFunctionEvaluator condFunctionEvaluator;

    private CondFunction condFunction;

    @BeforeEach
    void setup() {
        params = Mockito.mock(LispNode.class);
        userDefinedFunctions = Collections.emptyList();
        variableNameToValueMap = Collections.emptyMap();

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
        Mockito.when(
            condFunctionEvaluator.evaluateCondFunction(
                params,
                userDefinedFunctions,
                variableNameToValueMap
            )
        ).thenReturn(expected);

        LispNode actual = condFunction.evaluateLispFunction(
            params,
            userDefinedFunctions,
            variableNameToValueMap
        );

        Assertions.assertEquals(expected, actual);

        Mockito.verify(condFunctionParameterAsserter).assertCondFunctionParameters(
            params,
            variableNameToValueMap
        );
    }
}
