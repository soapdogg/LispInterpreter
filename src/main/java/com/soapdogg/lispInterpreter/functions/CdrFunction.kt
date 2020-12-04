package com.soapdogg.lispInterpreter.functions

import com.soapdogg.lispInterpreter.asserter.FunctionLengthAsserter
import com.soapdogg.lispInterpreter.constants.FunctionLengthConstants
import com.soapdogg.lispInterpreter.constants.FunctionNameConstants
import com.soapdogg.lispInterpreter.converter.NodeConverter
import com.soapdogg.lispInterpreter.datamodels.Node
import com.soapdogg.lispInterpreter.datamodels.UserDefinedFunction
import com.soapdogg.lispInterpreter.evaluator.NodeEvaluator
import com.soapdogg.lispInterpreter.valueretriver.ListValueRetriever

class CdrFunction (
    private val functionLengthAsserter: FunctionLengthAsserter,
    private val listValueRetriever: ListValueRetriever,
    private val nodeEvaluator: NodeEvaluator,
    private val nodeConverter: NodeConverter
): LispFunction {

    override fun evaluateLispFunction(
        params: Node,
        userDefinedFunctions: List<UserDefinedFunction>,
        variableNameToValueMap: Map<String, Node>
    ): Node {
        functionLengthAsserter.assertLengthIsAsExpected(
            FunctionNameConstants.CDR,
            FunctionLengthConstants.TWO,
            params
        )
        val expressionNodeParams = listValueRetriever.retrieveListValue(
            params,
            FunctionNameConstants.CDR,
            variableNameToValueMap
        )
        val address = expressionNodeParams.address
        val evaluatedAddress = nodeEvaluator.evaluate(
            nodeConverter.convertNodeToNodeV2(address),
            userDefinedFunctions,
            variableNameToValueMap,
            false
        )
        val node = listValueRetriever.retrieveListValue(
            evaluatedAddress,
            FunctionNameConstants.CDR,
            variableNameToValueMap
        )
        return node.data
    }
}