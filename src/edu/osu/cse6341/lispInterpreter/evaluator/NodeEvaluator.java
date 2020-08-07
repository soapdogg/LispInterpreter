package edu.osu.cse6341.lispInterpreter.evaluator;

import edu.osu.cse6341.lispInterpreter.asserter.FunctionLengthAsserter;
import edu.osu.cse6341.lispInterpreter.constants.FunctionsConstants;
import edu.osu.cse6341.lispInterpreter.datamodels.UserDefinedFunction;
import edu.osu.cse6341.lispInterpreter.determiner.ExpressionNodeDeterminer;
import edu.osu.cse6341.lispInterpreter.determiner.UserDefinedFunctionNameDeterminer;
import edu.osu.cse6341.lispInterpreter.functions.LispFunction;
import edu.osu.cse6341.lispInterpreter.datamodels.AtomNode;
import edu.osu.cse6341.lispInterpreter.datamodels.ExpressionNode;
import edu.osu.cse6341.lispInterpreter.datamodels.Node;
import lombok.AllArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor(staticName = "newInstance")
public class NodeEvaluator {

    private final ExpressionNodeDeterminer expressionNodeDeterminer;
    private final UserDefinedFunctionNameDeterminer userDefinedFunctionNameDeterminer;
    private final FunctionLengthAsserter functionLengthAsserter;

    public Node evaluate(
        final Node node,
        final List<UserDefinedFunction> userDefinedFunctions,
        final Map<String, Node> variableNameToValueMap,
        final boolean areLiteralsAllowed
    ) throws Exception {
        if (node instanceof AtomNode) return evaluate(
            (AtomNode) node,
            variableNameToValueMap
        );
        return evaluate(
            (ExpressionNode) node,
            userDefinedFunctions,
            variableNameToValueMap,
            areLiteralsAllowed
        );
    }

    private Node evaluate(
        final AtomNode atomNode,
        final Map<String, Node> variableNameToValueMap
    ) {
        String value = atomNode.getValue();
        if(variableNameToValueMap.containsKey(value)) return variableNameToValueMap.get(value);
        return atomNode;
    }

    private Node evaluate(
        final ExpressionNode expressionNode,
        final List<UserDefinedFunction> userDefinedFunctions,
        final Map<String, Node> variableNameToValueMap,
        final boolean areLiteralsAllowed
    ) throws Exception {
        Node address = expressionNode.getAddress();

        if (!expressionNodeDeterminer.isExpressionNode(address)) {
            String addressValue = ((AtomNode)address).getValue();
            boolean isFunctionName = userDefinedFunctionNameDeterminer.isUserDefinedFunctionName(
                userDefinedFunctions,
                addressValue
            );
            if (isFunctionName) {
                UserDefinedFunction userDefinedFunction = userDefinedFunctions.stream().filter(
                    userDefinedFunction1 -> userDefinedFunction1.getFunctionName().equals(addressValue)
                ).findFirst().get();
                Node params = expressionNode.getData();
                functionLengthAsserter.assertLengthIsAsExpected(
                    userDefinedFunction.getFunctionName(),
                    userDefinedFunction.getFormalParameters().size() + 1,
                    params
                );
                Map<String, Node> newVariables = new HashMap<>();
                newVariables.putAll(variableNameToValueMap);
                for (String formal: userDefinedFunction.getFormalParameters()) {
                    ExpressionNode temp = (ExpressionNode)params;
                    Node evaluatedAddress = evaluate(
                        temp.getAddress(),
                        userDefinedFunctions,
                        variableNameToValueMap,
                        true
                    );
                    newVariables.put(formal, evaluatedAddress);
                    params = temp.getData();
                }

                Node result = evaluate(
                    userDefinedFunction.getBody(),
                    userDefinedFunctions,
                    newVariables,
                    true
                );
                return result;
            }
            if (FunctionsConstants.functionMap.containsKey(addressValue)) {
                LispFunction function = FunctionsConstants.functionMap.get(addressValue);
                return function.evaluateLispFunction(
                    expressionNode.getData(),
                    userDefinedFunctions,
                    variableNameToValueMap
                );
            }
            if (!areLiteralsAllowed) throw new Exception("Error! Invalid CAR value: " + addressValue + '\n');
        }
        return evaluate(
            address,
            userDefinedFunctions,
            variableNameToValueMap,
            true
        );
    }
}
