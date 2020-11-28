package com.soapdogg.lispInterpreter.functions

import com.soapdogg.lispInterpreter.asserter.FunctionLengthAsserter
import com.soapdogg.lispInterpreter.constants.FunctionLengthConstants
import com.soapdogg.lispInterpreter.constants.FunctionNameConstants
import com.soapdogg.lispInterpreter.datamodels.Node
import com.soapdogg.lispInterpreter.datamodels.UserDefinedFunction
import com.soapdogg.lispInterpreter.valueretriver.ListValueRetriever

class QuoteFunction(
    private val functionLengthAsserter: FunctionLengthAsserter,
    private val listValueRetriever: ListValueRetriever
) : LispFunction {

    override fun evaluateLispFunction(
        params: Node,
        userDefinedFunctions: List<UserDefinedFunction>,
        variableNameToValueMap: Map<String, Node>
    ): Node {
        functionLengthAsserter.assertLengthIsAsExpected(
            FunctionNameConstants.QUOTE,
            FunctionLengthConstants.TWO,
            params
        )
        val expressionNodeParams = listValueRetriever.retrieveListValue(
            params,
            FunctionNameConstants.QUOTE,
            variableNameToValueMap
        )
        return expressionNodeParams.address
    }
}