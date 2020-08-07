package edu.osu.cse6341.lispInterpreter.generator;

import edu.osu.cse6341.lispInterpreter.datamodels.UserDefinedFunction;
import edu.osu.cse6341.lispInterpreter.functions.DefunFunction;
import edu.osu.cse6341.lispInterpreter.datamodels.ExpressionNode;
import edu.osu.cse6341.lispInterpreter.datamodels.Node;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor(staticName = "newInstance")
public class UserDefinedFunctionGenerator {

    private final DefunFunction defunFunction;

    public List<UserDefinedFunction> generateUserDefinedFunctions(
        List<Node> defunNodes
    ) throws Exception {
        List<UserDefinedFunction> userDefinedFunctions = new ArrayList<>();
        for(Node defunNode : defunNodes) {
            ExpressionNode expressionDefunNode = (ExpressionNode)defunNode;
            UserDefinedFunction userDefinedFunction = defunFunction.evaluateLispFunction(
                expressionDefunNode.getData()
            );
            userDefinedFunctions.add(userDefinedFunction);
        }
        return userDefinedFunctions;
    }
}
