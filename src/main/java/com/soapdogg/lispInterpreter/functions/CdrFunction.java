package com.soapdogg.lispInterpreter.functions;

import com.soapdogg.lispInterpreter.datamodels.ExpressionNode;
import com.soapdogg.lispInterpreter.datamodels.Node;
import com.soapdogg.lispInterpreter.datamodels.UserDefinedFunction;
import com.soapdogg.lispInterpreter.valueretriver.ListValueRetriever;
import com.soapdogg.lispInterpreter.constants.FunctionLengthConstants;
import com.soapdogg.lispInterpreter.constants.FunctionNameConstants;
import com.soapdogg.lispInterpreter.evaluator.NodeEvaluator;
import com.soapdogg.lispInterpreter.asserter.FunctionLengthAsserter;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Map;

@AllArgsConstructor()
public class CdrFunction implements LispFunction {

    private final FunctionLengthAsserter functionLengthAsserter;
    private final ListValueRetriever listValueRetriever;
    private final NodeEvaluator nodeEvaluator;

    @Override
    public Node evaluateLispFunction(
        final Node params,
        final List<UserDefinedFunction> userDefinedFunctions,
        final Map<String, ? extends Node> variableNameToValueMap
    ) throws Exception {
        functionLengthAsserter.assertLengthIsAsExpected(
            FunctionNameConstants.CDR,
            FunctionLengthConstants.TWO,
            params
        );
        ExpressionNode expressionNodeParams = listValueRetriever.retrieveListValue(
            params,
            FunctionNameConstants.CDR,
            variableNameToValueMap
        );
        Node address = expressionNodeParams.getAddress();
        Node evaluatedAddress = nodeEvaluator.evaluate(
            address,
            userDefinedFunctions,
            variableNameToValueMap,
            false
        );
        ExpressionNode node = listValueRetriever.retrieveListValue(
            evaluatedAddress,
            FunctionNameConstants.CDR,
            variableNameToValueMap
        );
        return node.getData();
    }
}
