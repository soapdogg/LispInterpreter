package com.soapdogg.lispInterpreter.functions

import com.soapdogg.lispInterpreter.asserter.CondFunctionParameterAsserter
import com.soapdogg.lispInterpreter.datamodels.Node
import com.soapdogg.lispInterpreter.datamodels.UserDefinedFunction
import com.soapdogg.lispInterpreter.evaluator.CondFunctionEvaluator

class CondFunction(
    private val condFunctionParameterAsserter: CondFunctionParameterAsserter,
    private val condFunctionEvaluator: CondFunctionEvaluator
) : LispFunction {

    override fun evaluateLispFunction(
        params: Node,
        userDefinedFunctions: List<UserDefinedFunction>,
        variableNameToValueMap: Map<String, Node>
    ): Node {
        condFunctionParameterAsserter.assertCondFunctionParameters(
            params,
            variableNameToValueMap
        )
        return condFunctionEvaluator.evaluateCondFunction(
            params,
            userDefinedFunctions,
            variableNameToValueMap
        )
    }
}