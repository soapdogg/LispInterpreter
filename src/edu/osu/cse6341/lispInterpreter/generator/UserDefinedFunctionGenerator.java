package edu.osu.cse6341.lispInterpreter.generator;

import edu.osu.cse6341.lispInterpreter.datamodels.UserDefinedFunction;
import edu.osu.cse6341.lispInterpreter.functions.DefunFunction;
import edu.osu.cse6341.lispInterpreter.nodes.ExpressionNode;
import edu.osu.cse6341.lispInterpreter.nodes.LispNode;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor(staticName = "newInstance")
public class UserDefinedFunctionGenerator {

    private final DefunFunction defunFunction;

    public List<UserDefinedFunction> generateUserDefinedFunctions(
        List<LispNode> defunNodes
    ) throws Exception {
        List<UserDefinedFunction> userDefinedFunctions = new ArrayList<>();
        for(LispNode defunNode : defunNodes) {
            ExpressionNode expressionDefunNode = (ExpressionNode)defunNode;
            UserDefinedFunction userDefinedFunction = defunFunction.evaluateLispFunction(expressionDefunNode.getData());
            userDefinedFunctions.add(userDefinedFunction);
        }
        return userDefinedFunctions;
    }
}
