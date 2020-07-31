package edu.osu.cse6341.lispInterpreter.evaluator;

import edu.osu.cse6341.lispInterpreter.constants.FunctionsConstants;
import edu.osu.cse6341.lispInterpreter.functions.LispFunction;
import edu.osu.cse6341.lispInterpreter.program.Environment;
import edu.osu.cse6341.lispInterpreter.program.nodes.AtomNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.ExpressionNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.LispNode;
import edu.osu.cse6341.lispInterpreter.singleton.GeneratorSingleton;
import lombok.AllArgsConstructor;

@AllArgsConstructor(staticName = "newInstance")
public class NodeEvaluator {

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
        if(Environment.getEnvironment().isVariableName(value)) return Environment.getEnvironment().getVariableValue(value);
        return atomNode;
    }

    private LispNode evaluate(
        final ExpressionNode expressionNode,
        final boolean areLiteralsAllowed
    ) throws Exception {
        LispNode address = expressionNode.getAddress();
        if(address == null) return GeneratorSingleton.INSTANCE.getNodeGenerator().generateAtomNode(false);

        String addressValue = address.getValue();
        Environment e = Environment.getEnvironment();
        if(e.isVariableName(addressValue)) return e.getVariableValue(addressValue);
        if(e.isFunctionName(addressValue)) return e.evaluateFunction(addressValue, expressionNode.getData());
        if(FunctionsConstants.functionMap.containsKey(addressValue)) return executeBuiltInFunction(
            addressValue,
            expressionNode.getData()
        );
        if(!areLiteralsAllowed) throw new Exception("Error! Invalid CAR value: " + addressValue + '\n');
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
