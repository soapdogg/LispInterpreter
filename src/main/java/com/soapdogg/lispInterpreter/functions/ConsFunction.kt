package com.soapdogg.lispInterpreter.functions

import com.soapdogg.lispInterpreter.asserter.FunctionLengthAsserter
import com.soapdogg.lispInterpreter.constants.FunctionLengthConstants
import com.soapdogg.lispInterpreter.constants.FunctionNameConstants
import com.soapdogg.lispInterpreter.datamodels.Node
import com.soapdogg.lispInterpreter.datamodels.UserDefinedFunction
import com.soapdogg.lispInterpreter.evaluator.NodeEvaluator
import com.soapdogg.lispInterpreter.generator.NodeGenerator
import com.soapdogg.lispInterpreter.valueretriver.ListValueRetriever

class ConsFunction(
    private val functionLengthAsserter: FunctionLengthAsserter,
    private val listValueRetriever: ListValueRetriever,
    private val nodeEvaluator: NodeEvaluator,
    private val nodeGenerator: NodeGenerator
) : LispFunction {
    
    override fun evaluateLispFunction(
        params: Node,
        userDefinedFunctions: List<UserDefinedFunction>,
        variableNameToValueMap: Map<String, Node>
    ): Node {
        functionLengthAsserter.assertLengthIsAsExpected(
            FunctionNameConstants.CONS,
            FunctionLengthConstants.THREE,
            params
        )
        val expressionNodeParams = listValueRetriever.retrieveListValue(
            params,
            FunctionNameConstants.CONS,
            variableNameToValueMap
        )
        
        val evaluatedAddress = nodeEvaluator.evaluate(
            expressionNodeParams.address,
            userDefinedFunctions,
            variableNameToValueMap,
            true
        )
        val evaluatedData = nodeEvaluator.evaluate(
            expressionNodeParams.data,
            userDefinedFunctions,
            variableNameToValueMap,
            true
        )
        return nodeGenerator.generateExpressionNode(
            evaluatedAddress,
            evaluatedData
        )
    }
}