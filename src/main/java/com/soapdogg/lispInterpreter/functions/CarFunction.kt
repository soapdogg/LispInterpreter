package com.soapdogg.lispInterpreter.functions

import com.soapdogg.lispInterpreter.asserter.FunctionLengthAsserter
import com.soapdogg.lispInterpreter.constants.FunctionLengthConstants
import com.soapdogg.lispInterpreter.constants.FunctionNameConstants
import com.soapdogg.lispInterpreter.datamodels.Node
import com.soapdogg.lispInterpreter.datamodels.UserDefinedFunction
import com.soapdogg.lispInterpreter.evaluator.NodeEvaluator
import com.soapdogg.lispInterpreter.valueretriver.ListValueRetriever

class CarFunction (
    private val functionLengthAsserter: FunctionLengthAsserter,
    private val listValueRetriever: ListValueRetriever,
    private val nodeEvaluator: NodeEvaluator
): LispFunction {

    override fun evaluateLispFunction(
        params: Node,
        userDefinedFunctions: List<UserDefinedFunction>,
        variableNameToValueMap: Map<String, Node>
    ): Node {
        functionLengthAsserter.assertLengthIsAsExpected(
            FunctionNameConstants.CAR,
            FunctionLengthConstants.TWO,
            params
        )
        val expressionNodeParams = listValueRetriever.retrieveListValue(
            params,
            FunctionNameConstants.CAR,
            variableNameToValueMap
        )
        val address = expressionNodeParams.address
        val evaluatedAddress = nodeEvaluator.evaluate(
            address,
            userDefinedFunctions,
            variableNameToValueMap,
            false
        )
        val node = listValueRetriever.retrieveListValue(
            evaluatedAddress,
            FunctionNameConstants.CAR,
            variableNameToValueMap
        )
        return node.address
    }
}