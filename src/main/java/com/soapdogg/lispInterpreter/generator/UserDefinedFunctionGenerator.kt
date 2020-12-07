package com.soapdogg.lispInterpreter.generator

import com.soapdogg.lispInterpreter.asserter.FunctionLengthAsserter
import com.soapdogg.lispInterpreter.asserter.UserDefinedFormalParametersAsserter
import com.soapdogg.lispInterpreter.asserter.UserDefinedFunctionNameAsserter
import com.soapdogg.lispInterpreter.constants.FunctionLengthConstants
import com.soapdogg.lispInterpreter.constants.FunctionNameConstants
import com.soapdogg.lispInterpreter.datamodels.AtomNode
import com.soapdogg.lispInterpreter.datamodels.ExpressionListNode
import com.soapdogg.lispInterpreter.datamodels.UserDefinedFunction

class UserDefinedFunctionGenerator(
    private val functionLengthAsserter: FunctionLengthAsserter,
    private val userDefinedFunctionNameAsserter: UserDefinedFunctionNameAsserter,
    private val userDefinedFormalParametersAsserter: UserDefinedFormalParametersAsserter
) {

    fun evaluateLispFunction(
        params: ExpressionListNode
    ): Pair<String, UserDefinedFunction> {
        functionLengthAsserter.assertLengthIsAsExpected(
            FunctionNameConstants.DEFUN,
            FunctionLengthConstants.FOUR,
            params
        )
        val functionNameNode = params.children[1] as AtomNode
        val functionName = functionNameNode.value
        userDefinedFunctionNameAsserter.assertFunctionNameIsValid(functionName)

        val formalParametersNode = params.children[2]
        val formalParameters = if (formalParametersNode is ExpressionListNode && formalParametersNode.children.isNotEmpty()) {
           formalParametersNode.children.map {
               (it as AtomNode).value
           }.subList(0, formalParametersNode.children.size - 1)
        } else {
            listOf()
        }

        userDefinedFormalParametersAsserter.assertFormalParameters(formalParameters)
        val userDefinedFunction = UserDefinedFunction(
            formalParameters,
            params.children[3]
        )
        return Pair(functionName, userDefinedFunction)
    }
}