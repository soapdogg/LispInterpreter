package edu.osu.cse6341.lispInterpreter.evaluator;

import edu.osu.cse6341.lispInterpreter.constants.FunctionsConstants;
import edu.osu.cse6341.lispInterpreter.determiner.ExpressionNodeDeterminer;
import edu.osu.cse6341.lispInterpreter.functions.LispFunction;
import edu.osu.cse6341.lispInterpreter.program.Environment;
import edu.osu.cse6341.lispInterpreter.nodes.AtomNode;
import edu.osu.cse6341.lispInterpreter.nodes.ExpressionNode;
import edu.osu.cse6341.lispInterpreter.nodes.LispNode;
import edu.osu.cse6341.lispInterpreter.singleton.EnvironmentSingleton;
import edu.osu.cse6341.lispInterpreter.singleton.GeneratorSingleton;
import lombok.AllArgsConstructor;

@AllArgsConstructor(staticName = "newInstance")
public class NodeEvaluator {

    private final ExpressionNodeDeterminer expressionNodeDeterminer;

    public LispNode evaluate(
        final LispNode lispNode,
        final boolean areLiteralsAllowed
    ) throws Exception {
        if (lispNode instanceof AtomNode) return evaluate((AtomNode) lispNode);
        return evaluate((ExpressionNode)lispNode, areLiteralsAllowed);
    }

    private LispNode evaluate(
        final AtomNode atomNode
    ) {
        String value = atomNode.getValue();
        if(EnvironmentSingleton.INSTANCE.getEnvironment().isVariableName(value)) return EnvironmentSingleton.INSTANCE.getEnvironment().getVariableValue(value);
        return atomNode;
    }

    private LispNode evaluate(
        final ExpressionNode expressionNode,
        final boolean areLiteralsAllowed
    ) throws Exception {
        LispNode address = expressionNode.getAddress();
        if(address == null) return GeneratorSingleton.INSTANCE.getNodeGenerator().generateAtomNode(false);

        if (!expressionNodeDeterminer.isExpressionNode(address)) {
            String addressValue = ((AtomNode)address).getValue();
            Environment e = EnvironmentSingleton.INSTANCE.getEnvironment();
            if (e.isVariableName(addressValue)) return e.getVariableValue(addressValue);
            if (e.isFunctionName(addressValue)) return e.evaluateFunction(addressValue, expressionNode.getData());
            if (FunctionsConstants.functionMap.containsKey(addressValue)) return executeBuiltInFunction(
                addressValue,
                expressionNode.getData()
            );
            if (!areLiteralsAllowed) throw new Exception("Error! Invalid CAR value: " + addressValue + '\n');
        }
        return evaluate(address, true);
    }

    private LispNode executeBuiltInFunction(
        String functionName,
        LispNode data
    ) throws Exception{
        LispFunction function = FunctionsConstants.functionMap.get(functionName);
        return function.evaluateLispFunction(data);
    }
}
