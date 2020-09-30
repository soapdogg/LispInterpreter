package com.soapdogg.lispInterpreter.generator;

import com.soapdogg.lispInterpreter.datamodels.ExpressionNode;
import com.soapdogg.lispInterpreter.datamodels.Node;
import com.soapdogg.lispInterpreter.datamodels.UserDefinedFunction;
import com.soapdogg.lispInterpreter.functions.DefunFunction;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor(staticName = "newInstance")
public class UserDefinedFunctionGenerator {

    private final DefunFunction defunFunction;

    public List<UserDefinedFunction> generateUserDefinedFunctions(
        final List<Node> defunNodes
    ) throws Exception {
        List<UserDefinedFunction> userDefinedFunctions = new ArrayList<>();
        for(Node defunNode : defunNodes) {
            ExpressionNode expressionDefunNode = (ExpressionNode) defunNode;
            UserDefinedFunction userDefinedFunction = defunFunction.evaluateLispFunction(
                expressionDefunNode.getData()
            );
            userDefinedFunctions.add(userDefinedFunction);
        }
        return userDefinedFunctions;
    }
}
