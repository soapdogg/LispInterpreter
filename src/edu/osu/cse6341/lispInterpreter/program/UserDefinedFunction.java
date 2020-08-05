package edu.osu.cse6341.lispInterpreter.program;

import edu.osu.cse6341.lispInterpreter.nodes.ExpressionNode;
import edu.osu.cse6341.lispInterpreter.nodes.LispNode;
import edu.osu.cse6341.lispInterpreter.singleton.AsserterSingleton;
import edu.osu.cse6341.lispInterpreter.singleton.DeterminerSingleton;
import edu.osu.cse6341.lispInterpreter.singleton.EnvironmentSingleton;
import edu.osu.cse6341.lispInterpreter.singleton.EvaluatorSingleton;
import lombok.AllArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor(staticName = "newInstance")
public class UserDefinedFunction {

    private final List<String> formalParameters;
    private final LispNode body;
    private final String functionName;

    public LispNode evaluate(LispNode params) throws Exception{
        Environment e = EnvironmentSingleton.INSTANCE.getEnvironment();
        Map<String, LispNode> oldVariables = e.getVariables();
        Map<String, LispNode> newVariables = bindVariablesToParameters(params);
        e.unionVariables(newVariables);
        LispNode result = EvaluatorSingleton.INSTANCE.getNodeEvaluator().evaluate(
            body,
            true
        );
        e.setVariables(oldVariables);
        return result;
    }

    private Map<String, LispNode> bindVariablesToParameters(LispNode params) throws Exception{
        AsserterSingleton.INSTANCE.getFunctionLengthAsserter().assertLengthIsAsExpected(
            functionName,
            formalParameters.size() + 1,
            params
        );
        Map<String, LispNode> newVariables = new HashMap<>();
        for (String formal: formalParameters) {
            ExpressionNode temp = (ExpressionNode)params;
            LispNode evaluatedAddress = EvaluatorSingleton.INSTANCE.getNodeEvaluator().evaluate(
                temp.getAddress(),
                true
            );
            newVariables.put(formal, evaluatedAddress);
            params = temp.getData();
        }
        return newVariables;
    }
}
