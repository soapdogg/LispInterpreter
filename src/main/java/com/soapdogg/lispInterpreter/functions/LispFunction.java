package com.soapdogg.lispInterpreter.functions;

import com.soapdogg.lispInterpreter.datamodels.Node;
import com.soapdogg.lispInterpreter.datamodels.UserDefinedFunction;

import java.util.List;
import java.util.Map;

public interface LispFunction {
    Node evaluateLispFunction(
        final Node params,
        List<UserDefinedFunction> userDefinedFunctions,
        Map<String, Node> variableNameToValueMap
    ) throws Exception;
}
