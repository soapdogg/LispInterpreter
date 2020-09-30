package com.soapdogg.lispInterpreter.generator;

import com.soapdogg.lispInterpreter.datamodels.ExpressionNode;
import com.soapdogg.lispInterpreter.datamodels.Node;
import com.soapdogg.lispInterpreter.determiner.ExpressionNodeDeterminer;
import com.soapdogg.lispInterpreter.valueretriver.AtomicValueRetriever;
import com.soapdogg.lispInterpreter.valueretriver.ListValueRetriever;
import com.soapdogg.lispInterpreter.constants.FunctionNameConstants;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@AllArgsConstructor(staticName = "newInstance")
public class UserDefinedFunctionFormalParameterGenerator {

    private final ExpressionNodeDeterminer expressionNodeDeterminer;
    private final ListValueRetriever listValueRetriever;
    private final AtomicValueRetriever atomicValueRetriever;

    public List<String> getFormalParameters(
        final Node formalParametersNode,
        final int parameterCounter,
        final Map<String, Node> variableNameToValueMap
    ) throws Exception{
        List<String> formalParameters = new ArrayList<>();
        boolean isExpressionNode = expressionNodeDeterminer.isExpressionNode(formalParametersNode);
        if (!isExpressionNode) {
            return formalParameters;
        }

        ExpressionNode formalParametersExpressionNode = listValueRetriever.retrieveListValue(
            formalParametersNode,
            FunctionNameConstants.DEFUN,
            variableNameToValueMap
        );
        Node formalNode = formalParametersExpressionNode.getAddress();
        String formalId = atomicValueRetriever.retrieveAtomicValue(
            formalNode,
            parameterCounter,
            FunctionNameConstants.DEFUN
        );
        List<String> nextParameters = getFormalParameters(
            formalParametersExpressionNode.getData(),
            parameterCounter + 1,
            variableNameToValueMap
        );
        formalParameters.add(formalId);
        formalParameters.addAll(
            nextParameters
        );
        return formalParameters;
    }
}
