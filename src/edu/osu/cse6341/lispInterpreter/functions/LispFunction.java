package edu.osu.cse6341.lispInterpreter.functions;

import edu.osu.cse6341.lispInterpreter.datamodels.UserDefinedFunction;
import edu.osu.cse6341.lispInterpreter.nodes.LispNode;

import java.util.List;
import java.util.Map;

public interface LispFunction {
    LispNode evaluateLispFunction(
        final LispNode params,
        List<UserDefinedFunction> userDefinedFunctions,
        Map<String, LispNode> variableNameToValueMap
    ) throws Exception;
}
