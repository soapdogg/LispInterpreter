package com.soapdogg.lispInterpreter.functions

import com.soapdogg.lispInterpreter.asserter.FunctionLengthAsserter
import com.soapdogg.lispInterpreter.asserter.UserDefinedFormalParametersAsserter
import com.soapdogg.lispInterpreter.asserter.UserDefinedFunctionNameAsserter
import com.soapdogg.lispInterpreter.constants.FunctionLengthConstants
import com.soapdogg.lispInterpreter.constants.FunctionNameConstants
import com.soapdogg.lispInterpreter.converter.NodeConverter
import com.soapdogg.lispInterpreter.datamodels.AtomNode
import com.soapdogg.lispInterpreter.datamodels.ExpressionListNode
import com.soapdogg.lispInterpreter.datamodels.UserDefinedFunction
import com.soapdogg.lispInterpreter.generator.UserDefinedFunctionFormalParameterGenerator

class DefunFunction(
    private val functionLengthAsserter: FunctionLengthAsserter,
    private val userDefinedFunctionNameAsserter: UserDefinedFunctionNameAsserter,
    private val userDefinedFunctionFormalParameterGenerator: UserDefinedFunctionFormalParameterGenerator,
    private val userDefinedFormalParametersAsserter: UserDefinedFormalParametersAsserter,
    private val nodeConverter: NodeConverter
) {

    fun evaluateLispFunction(
        params: ExpressionListNode
    ): UserDefinedFunction {
        functionLengthAsserter.assertLengthIsAsExpected(
            FunctionNameConstants.DEFUN,
            FunctionLengthConstants.FOUR,
            params
        )
        val functionNameNode = params.children[1] as AtomNode
        val functionName = functionNameNode.value
        userDefinedFunctionNameAsserter.assertFunctionNameIsValid(functionName)

        val formalParameters = userDefinedFunctionFormalParameterGenerator.getFormalParameters(
            nodeConverter.convertNodeV2ToNode(params.children[2]),
            FunctionLengthConstants.ONE,
            mapOf()
        )
        userDefinedFormalParametersAsserter.assertFormalParameters(formalParameters)
        val convertedBody = nodeConverter.convertNodeV2ToNode(params.children[3])
        return UserDefinedFunction(
            formalParameters,
            convertedBody,
            functionName
        )
    }
}