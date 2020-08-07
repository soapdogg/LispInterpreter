package edu.osu.cse6341.lispInterpreter.evaluator;

import edu.osu.cse6341.lispInterpreter.constants.FunctionsConstants;
import edu.osu.cse6341.lispInterpreter.datamodels.UserDefinedFunction;
import edu.osu.cse6341.lispInterpreter.determiner.ExpressionNodeDeterminer;
import edu.osu.cse6341.lispInterpreter.functions.LispFunction;
import edu.osu.cse6341.lispInterpreter.program.Environment;
import edu.osu.cse6341.lispInterpreter.nodes.AtomNode;
import edu.osu.cse6341.lispInterpreter.nodes.ExpressionNode;
import edu.osu.cse6341.lispInterpreter.nodes.LispNode;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor(staticName = "newInstance")
public class NodeEvaluator {

    private final ExpressionNodeDeterminer expressionNodeDeterminer;
    private final Environment environment;

    public LispNode evaluate(
        final LispNode lispNode,
        List<UserDefinedFunction> userDefinedFunctions, final boolean areLiteralsAllowed
    ) throws Exception {
        if (lispNode instanceof AtomNode) return evaluate((AtomNode) lispNode);
        return evaluate(
            (ExpressionNode)lispNode,
            userDefinedFunctions,
            areLiteralsAllowed
        );
    }

    private LispNode evaluate(
        final AtomNode atomNode
    ) {
        String value = atomNode.getValue();
        if(environment.isVariableName(value)) return environment.getVariableValue(value);
        return atomNode;
    }

    private LispNode evaluate(
        final ExpressionNode expressionNode,
        final List<UserDefinedFunction> userDefinedFunctions,
        final boolean areLiteralsAllowed
    ) throws Exception {
        LispNode address = expressionNode.getAddress();

        if (!expressionNodeDeterminer.isExpressionNode(address)) {
            String addressValue = ((AtomNode)address).getValue();
            if (environment.isFunctionName(addressValue)) return environment.evaluateFunction(
                addressValue,
                expressionNode.getData(),
                userDefinedFunctions
            );
            if (FunctionsConstants.functionMap.containsKey(addressValue)) {
                LispFunction function = FunctionsConstants.functionMap.get(addressValue);
                return function.evaluateLispFunction(
                    expressionNode.getData(),
                    userDefinedFunctions
                );
            }
            if (!areLiteralsAllowed) throw new Exception("Error! Invalid CAR value: " + addressValue + '\n');
        }
        return evaluate(
            address,
            userDefinedFunctions,
            true
        );
    }
}
