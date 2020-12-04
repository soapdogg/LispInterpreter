package com.soapdogg.lispInterpreter.functions

import com.soapdogg.lispInterpreter.asserter.FunctionLengthAsserter
import com.soapdogg.lispInterpreter.constants.FunctionLengthConstants
import com.soapdogg.lispInterpreter.constants.FunctionNameConstants
import com.soapdogg.lispInterpreter.constants.ReservedValuesConstants
import com.soapdogg.lispInterpreter.datamodels.AtomNode
import com.soapdogg.lispInterpreter.datamodels.ExpressionListNode
import com.soapdogg.lispInterpreter.datamodels.NodeV2
import com.soapdogg.lispInterpreter.datamodels.UserDefinedFunction
import com.soapdogg.lispInterpreter.evaluator.NodeEvaluator
import com.soapdogg.lispInterpreter.generator.NodeGenerator

class ConsFunction(
    private val functionLengthAsserter: FunctionLengthAsserter,
    private val nodeEvaluator: NodeEvaluator,
    private val nodeGenerator: NodeGenerator
): LispFunctionV2 {

    override fun evaluateLispFunction(
        params: ExpressionListNode,
        userDefinedFunctions: List<UserDefinedFunction>,
        variableNameToValueMap: Map<String, NodeV2>
    ): NodeV2 {
        functionLengthAsserter.assertLengthIsAsExpected(
            FunctionNameConstants.CONS,
            FunctionLengthConstants.THREE,
            params
        )
        val evaluatedAddress = nodeEvaluator.evaluateV2(
            params.children[1],
            userDefinedFunctions,
            variableNameToValueMap
        )
        val evaluatedData = nodeEvaluator.evaluateV2(
            params.children[2],
            userDefinedFunctions,
            variableNameToValueMap
        )
        if (evaluatedData is ExpressionListNode) {
            return nodeGenerator.generateExpressionListNode(
                listOf(evaluatedAddress) + evaluatedData.children
            )
        }
        return nodeGenerator.generateExpressionListNode(
            listOf(evaluatedAddress, evaluatedData)
        )
    }
}