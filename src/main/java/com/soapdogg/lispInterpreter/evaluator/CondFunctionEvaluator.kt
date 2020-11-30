package com.soapdogg.lispInterpreter.evaluator

import com.soapdogg.lispInterpreter.comparator.NodeValueComparator
import com.soapdogg.lispInterpreter.constants.FunctionNameConstants
import com.soapdogg.lispInterpreter.datamodels.AtomNode
import com.soapdogg.lispInterpreter.datamodels.ExpressionNode
import com.soapdogg.lispInterpreter.datamodels.Node
import com.soapdogg.lispInterpreter.datamodels.UserDefinedFunction
import com.soapdogg.lispInterpreter.exceptions.NotAListException
import com.soapdogg.lispInterpreter.valueretriver.ListValueRetriever

class CondFunctionEvaluator(
    private val listValueRetriever: ListValueRetriever,
    private val nodeEvaluator: NodeEvaluator,
    private val nodeValueComparator: NodeValueComparator
) {

    fun evaluateCondFunction(
        params: Node,
        userDefinedFunctions: List<UserDefinedFunction>,
        variableNameToValueMap: Map<String, Node>
    ): Node {
        if (params is AtomNode) {
            throw NotAListException("Error! None of the conditions in the COND function evaluated to true.\n")
        }
        val expressionNodeParams = params as ExpressionNode
        val expressionNodeAddress = listValueRetriever.retrieveListValue(
            expressionNodeParams.address,
            FunctionNameConstants.COND,
            variableNameToValueMap
        )
        val booleanResult = nodeEvaluator.evaluate(
            expressionNodeAddress.address,
            userDefinedFunctions,
            variableNameToValueMap,
            true
        )
        return if (booleanResult is AtomNode && !nodeValueComparator.equalsNil(booleanResult.value)) nodeEvaluator.evaluate(
            expressionNodeAddress.data,
            userDefinedFunctions,
            variableNameToValueMap,
            true
        ) else evaluateCondFunction(
            expressionNodeParams.data,
            userDefinedFunctions,
            variableNameToValueMap
        )
    }
}