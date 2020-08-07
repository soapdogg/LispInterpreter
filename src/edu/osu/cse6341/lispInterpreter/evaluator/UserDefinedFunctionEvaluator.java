package edu.osu.cse6341.lispInterpreter.evaluator;

import edu.osu.cse6341.lispInterpreter.asserter.FunctionLengthAsserter;
import edu.osu.cse6341.lispInterpreter.nodes.ExpressionNode;
import edu.osu.cse6341.lispInterpreter.nodes.LispNode;
import edu.osu.cse6341.lispInterpreter.program.Environment;
import edu.osu.cse6341.lispInterpreter.datamodels.UserDefinedFunction;
import lombok.AllArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor(staticName = "newInstance")
public class UserDefinedFunctionEvaluator {

    private final Environment environment;
    private final FunctionLengthAsserter functionLengthAsserter;
    private final NodeEvaluator nodeEvaluator;

    public LispNode evaluate(
        LispNode params,
        List<UserDefinedFunction> userDefinedFunctions,
        UserDefinedFunction userDefinedFunction
    ) throws Exception{
        Map<String, LispNode> oldVariables = environment.getVariables();
        functionLengthAsserter.assertLengthIsAsExpected(
            userDefinedFunction.getFunctionName(),
            userDefinedFunction.getFormalParameters().size() + 1,
            params
        );
        Map<String, LispNode> newVariables = new HashMap<>();
        for (String formal: userDefinedFunction.getFormalParameters()) {
            ExpressionNode temp = (ExpressionNode)params;
            LispNode evaluatedAddress = nodeEvaluator.evaluate(
                temp.getAddress(),
                userDefinedFunctions,
                true
            );
            newVariables.put(formal, evaluatedAddress);
            params = temp.getData();
        }

        environment.unionVariables(newVariables);
        LispNode result = nodeEvaluator.evaluate(
            userDefinedFunction.getBody(),
            userDefinedFunctions,
            true
        );
        environment.setVariables(oldVariables);
        return result;
    }
}
