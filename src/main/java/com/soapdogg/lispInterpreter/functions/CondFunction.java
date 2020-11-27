package com.soapdogg.lispInterpreter.functions;

import com.soapdogg.lispInterpreter.datamodels.Node;
import com.soapdogg.lispInterpreter.datamodels.UserDefinedFunction;
import com.soapdogg.lispInterpreter.asserter.CondFunctionParameterAsserter;
import com.soapdogg.lispInterpreter.evaluator.CondFunctionEvaluator;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Map;

@AllArgsConstructor()
public class CondFunction implements LispFunction {

    private final CondFunctionParameterAsserter condFunctionParameterAsserter;
    private final CondFunctionEvaluator condFunctionEvaluator;

    @Override
    public Node evaluateLispFunction(
        final Node params,
        final List<UserDefinedFunction> userDefinedFunctions,
        final Map<String, ? extends Node> variableNameToValueMap
    ) throws Exception {
        condFunctionParameterAsserter.assertCondFunctionParameters(
            params,
            variableNameToValueMap
        );
        return condFunctionEvaluator.evaluateCondFunction(
            params,
            userDefinedFunctions,
            variableNameToValueMap
        );
    }
}
