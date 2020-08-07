package edu.osu.cse6341.lispInterpreter.functions;

import edu.osu.cse6341.lispInterpreter.datamodels.UserDefinedFunction;
import edu.osu.cse6341.lispInterpreter.datamodels.Node;

import java.util.List;
import java.util.Map;

public interface LispFunction {
    Node evaluateLispFunction(
        final Node params,
        List<UserDefinedFunction> userDefinedFunctions,
        Map<String, Node> variableNameToValueMap
    ) throws Exception;
}
