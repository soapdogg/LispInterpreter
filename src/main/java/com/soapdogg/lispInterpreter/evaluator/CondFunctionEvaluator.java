package com.soapdogg.lispInterpreter.evaluator;

import com.soapdogg.lispInterpreter.comparator.NodeValueComparator;
import com.soapdogg.lispInterpreter.constants.FunctionNameConstants;
import com.soapdogg.lispInterpreter.datamodels.AtomNode;
import com.soapdogg.lispInterpreter.datamodels.ExpressionNode;
import com.soapdogg.lispInterpreter.datamodels.Node;
import com.soapdogg.lispInterpreter.datamodels.UserDefinedFunction;
import com.soapdogg.lispInterpreter.exceptions.NotAListException;
import com.soapdogg.lispInterpreter.valueretriver.ListValueRetriever;

import java.util.List;
import java.util.Map;


public class CondFunctionEvaluator {

    private final ListValueRetriever listValueRetriever;
    private final NodeEvaluator nodeEvaluator;
    private final NodeValueComparator nodeValueComparator;

    public CondFunctionEvaluator(
        ListValueRetriever listValueRetriever,
        NodeEvaluator nodeEvaluator,
        NodeValueComparator nodeValueComparator
    ) {
        this.listValueRetriever = listValueRetriever;
        this.nodeEvaluator = nodeEvaluator;
        this.nodeValueComparator = nodeValueComparator;
    }

    public Node evaluateCondFunction(
        final Node params,
        final List<UserDefinedFunction> userDefinedFunctions,
        final Map<String, ? extends Node> variableNameToValueMap
    ) throws Exception {
        if (params instanceof AtomNode) {
            throw new NotAListException("Error! None of the conditions in the COND function evaluated to true.\n");
        }
        ExpressionNode expressionNodeParams = (ExpressionNode)params;
        Node address = expressionNodeParams.getAddress();
        ExpressionNode expressionNodeAddress = listValueRetriever.retrieveListValue(
            address,
            FunctionNameConstants.COND,
            variableNameToValueMap
        );
        Node booleanResult = nodeEvaluator.evaluate(
            expressionNodeAddress.getAddress(),
            userDefinedFunctions,
            variableNameToValueMap,
            true
        );
        if((booleanResult instanceof AtomNode) && !nodeValueComparator.equalsNil(((AtomNode)booleanResult).getValue()))
            return nodeEvaluator.evaluate(
                expressionNodeAddress.getData(),
                userDefinedFunctions,
                variableNameToValueMap,
                true
            );
        return evaluateCondFunction(
            expressionNodeParams.getData(),
            userDefinedFunctions,
            variableNameToValueMap
        );
    }
}
