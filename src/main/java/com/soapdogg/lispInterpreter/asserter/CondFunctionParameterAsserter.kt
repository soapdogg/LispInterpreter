package com.soapdogg.lispInterpreter.asserter

import com.soapdogg.lispInterpreter.comparator.NodeValueComparator
import com.soapdogg.lispInterpreter.constants.FunctionLengthConstants
import com.soapdogg.lispInterpreter.constants.FunctionNameConstants
import com.soapdogg.lispInterpreter.datamodels.AtomNode
import com.soapdogg.lispInterpreter.datamodels.ExpressionNode
import com.soapdogg.lispInterpreter.datamodels.Node
import com.soapdogg.lispInterpreter.exceptions.NotAListException
import com.soapdogg.lispInterpreter.valueretriver.ListValueRetriever

class CondFunctionParameterAsserter(
    private val nodeValueComparator: NodeValueComparator,
    private val listValueRetriever: ListValueRetriever,
    private val functionLengthAsserter: FunctionLengthAsserter
) {

    fun assertCondFunctionParameters(
        params: Node,
        variableNameToValueMap: Map<String, Node>
    ) {
        if (params is AtomNode) {
            if (nodeValueComparator.equalsNil(params.value)) {
                return
            } else {
                throw NotAListException("Error! COND parameter: $params is not a list!")
            }
        }
        val expressionNodeParams = params as ExpressionNode
        val expressionNodeAddress = listValueRetriever.retrieveListValue(
            expressionNodeParams.address,
            FunctionNameConstants.COND,
            variableNameToValueMap
        )
        functionLengthAsserter.assertLengthIsAsExpected(
            FunctionNameConstants.COND,
            FunctionLengthConstants.TWO,
            expressionNodeAddress.data
        )
        assertCondFunctionParameters(
            expressionNodeParams.data,
            variableNameToValueMap
        )
    }
}