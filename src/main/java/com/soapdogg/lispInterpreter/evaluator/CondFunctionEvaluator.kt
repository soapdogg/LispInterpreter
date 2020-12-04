package com.soapdogg.lispInterpreter.evaluator

import com.soapdogg.lispInterpreter.comparator.NodeValueComparator
import com.soapdogg.lispInterpreter.constants.FunctionNameConstants
import com.soapdogg.lispInterpreter.converter.NodeConverter
import com.soapdogg.lispInterpreter.datamodels.AtomNode
import com.soapdogg.lispInterpreter.datamodels.ExpressionNode
import com.soapdogg.lispInterpreter.datamodels.Node
import com.soapdogg.lispInterpreter.datamodels.UserDefinedFunction
import com.soapdogg.lispInterpreter.exceptions.NotAListException
import com.soapdogg.lispInterpreter.valueretriver.ListValueRetriever

class CondFunctionEvaluator(
    private val listValueRetriever: ListValueRetriever,
    private val nodeEvaluator: NodeEvaluator,
    private val nodeValueComparator: NodeValueComparator,
    private val nodeConverter: NodeConverter
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
            nodeConverter.convertNodeToNodeV2(expressionNodeAddress.address),
            userDefinedFunctions,
            variableNameToValueMap,
            true
        )
        return if (booleanResult is AtomNode && !nodeValueComparator.equalsNil(booleanResult.value)) nodeEvaluator.evaluate(
            nodeConverter.convertNodeToNodeV2(expressionNodeAddress.data),
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