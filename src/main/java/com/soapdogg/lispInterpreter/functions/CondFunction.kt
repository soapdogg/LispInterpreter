package com.soapdogg.lispInterpreter.functions

import com.soapdogg.lispInterpreter.asserter.CondFunctionParameterAsserter
import com.soapdogg.lispInterpreter.converter.NodeConverter
import com.soapdogg.lispInterpreter.datamodels.ExpressionListNode
import com.soapdogg.lispInterpreter.datamodels.Node
import com.soapdogg.lispInterpreter.datamodels.UserDefinedFunction
import com.soapdogg.lispInterpreter.evaluator.CondFunctionEvaluator

class CondFunction(
    private val nodeConverter: NodeConverter,
    private val condFunctionParameterAsserter: CondFunctionParameterAsserter,
    private val condFunctionEvaluator: CondFunctionEvaluator
) : LispFunction {

    override fun evaluateLispFunction(
        params: Node,
        userDefinedFunctions: List<UserDefinedFunction>,
        variableNameToValueMap: Map<String, Node>
    ): Node {
        val converted = nodeConverter.convertNodeToNodeV2(params)
        if (converted is ExpressionListNode) {
            val condParams = converted.children.subList(1, converted.children.size)
            condFunctionParameterAsserter.assertCondFunctionParameters(
                condParams
            )
        }
        return condFunctionEvaluator.evaluateCondFunction(
            params,
            userDefinedFunctions,
            variableNameToValueMap
        )
    }
}