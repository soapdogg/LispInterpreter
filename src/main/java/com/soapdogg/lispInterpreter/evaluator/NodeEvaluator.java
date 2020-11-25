package com.soapdogg.lispInterpreter.evaluator;

import com.soapdogg.lispInterpreter.datamodels.AtomNode;
import com.soapdogg.lispInterpreter.datamodels.ExpressionNode;
import com.soapdogg.lispInterpreter.datamodels.Node;
import com.soapdogg.lispInterpreter.datamodels.UserDefinedFunction;
import com.soapdogg.lispInterpreter.determiner.ExpressionNodeDeterminer;
import com.soapdogg.lispInterpreter.determiner.UserDefinedFunctionNameDeterminer;
import com.soapdogg.lispInterpreter.functions.LispFunction;
import com.soapdogg.lispInterpreter.asserter.FunctionLengthAsserter;
import com.soapdogg.lispInterpreter.constants.FunctionsConstants;
import lombok.AllArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor(staticName = "newInstance")
public class NodeEvaluator {

    private final AtomNodeEvaluator atomNodeEvaluator;
    private final ExpressionNodeDeterminer expressionNodeDeterminer;
    private final UserDefinedFunctionNameDeterminer userDefinedFunctionNameDeterminer;
    private final FunctionLengthAsserter functionLengthAsserter;

    public Node evaluate(
        final Node node,
        final List<UserDefinedFunction> userDefinedFunctions,
        final Map<String, Node> variableNameToValueMap,
        final boolean areLiteralsAllowed
    ) throws Exception {
        if (node instanceof AtomNode) return atomNodeEvaluator.evaluate(
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
        final ExpressionNode expressionNode,
        final List<UserDefinedFunction> userDefinedFunctions,
        final Map<String, Node> variableNameToValueMap,
        final boolean areLiteralsAllowed
    ) throws Exception {
        Node address = expressionNode.getAddress();

        if (!expressionNodeDeterminer.isExpressionNode(address)) {
            String addressValue = ((AtomNode)address).getValue();
            boolean isFunctionName = userDefinedFunctionNameDeterminer.determineIfUserDefinedFunctionName(
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
                Map<String, Node> newVariables = new HashMap<>(variableNameToValueMap);
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

                return evaluate(
                    userDefinedFunction.getBody(),
                    userDefinedFunctions,
                    newVariables,
                    true
                );
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
