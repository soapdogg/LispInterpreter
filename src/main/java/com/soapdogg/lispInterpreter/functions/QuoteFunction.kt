package com.soapdogg.lispInterpreter.functions

import com.soapdogg.lispInterpreter.asserter.FunctionLengthAsserter
import com.soapdogg.lispInterpreter.constants.FunctionLengthConstants
import com.soapdogg.lispInterpreter.constants.FunctionNameConstants
import com.soapdogg.lispInterpreter.datamodels.ExpressionListNode
import com.soapdogg.lispInterpreter.datamodels.NodeV2
import com.soapdogg.lispInterpreter.datamodels.UserDefinedFunction

class QuoteFunction(
    private val functionLengthAsserter: FunctionLengthAsserter
): LispFunctionV2 {

    override fun evaluateLispFunction(
        params: ExpressionListNode,
        userDefinedFunctions: List<UserDefinedFunction>,
        variableNameToValueMap: Map<String, NodeV2>
    ): NodeV2 {
        functionLengthAsserter.assertLengthIsAsExpected(
            FunctionNameConstants.QUOTE,
            FunctionLengthConstants.TWO,
            params
        )
        return params.children[1]
    }
}