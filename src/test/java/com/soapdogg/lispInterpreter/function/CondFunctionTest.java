package com.soapdogg.lispInterpreter.function;

import com.soapdogg.lispInterpreter.asserter.CondFunctionParameterAsserter;
import com.soapdogg.lispInterpreter.datamodels.UserDefinedFunction;
import com.soapdogg.lispInterpreter.evaluator.CondFunctionEvaluator;
import com.soapdogg.lispInterpreter.functions.CondFunction;
import com.soapdogg.lispInterpreter.datamodels.Node;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;
import java.util.Map;

class CondFunctionTest {

    private Node params;
    private List<UserDefinedFunction> userDefinedFunctions;
    private Map<String, Node> variableNameToValueMap;

    private CondFunctionParameterAsserter condFunctionParameterAsserter;
    private CondFunctionEvaluator condFunctionEvaluator;

    private CondFunction condFunction;

    @BeforeEach
    void setup() {
        params = Mockito.mock(Node.class);
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
        Node expected = Mockito.mock(Node.class);
        Mockito.when(
            condFunctionEvaluator.evaluateCondFunction(
                params,
                userDefinedFunctions,
                variableNameToValueMap
            )
        ).thenReturn(expected);

        Node actual = condFunction.evaluateLispFunction(
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
