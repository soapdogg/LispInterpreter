package com.soapdogg.lispInterpreter.functions

import com.soapdogg.lispInterpreter.asserter.FunctionLengthAsserter
import com.soapdogg.lispInterpreter.asserter.UserDefinedFormalParametersAsserter
import com.soapdogg.lispInterpreter.asserter.UserDefinedFunctionNameAsserter
import com.soapdogg.lispInterpreter.constants.FunctionLengthConstants
import com.soapdogg.lispInterpreter.constants.FunctionNameConstants
import com.soapdogg.lispInterpreter.datamodels.Node
import com.soapdogg.lispInterpreter.datamodels.UserDefinedFunction
import com.soapdogg.lispInterpreter.generator.UserDefinedFunctionFormalParameterGenerator
import com.soapdogg.lispInterpreter.valueretriver.AtomicValueRetriever
import com.soapdogg.lispInterpreter.valueretriver.ListValueRetriever

class DefunFunction(
    private val functionLengthAsserter: FunctionLengthAsserter,
    private val atomicValueRetriever: AtomicValueRetriever,
    private val listValueRetriever: ListValueRetriever,
    private val userDefinedFunctionNameAsserter: UserDefinedFunctionNameAsserter,
    private val userDefinedFunctionFormalParameterGenerator: UserDefinedFunctionFormalParameterGenerator,
    private val userDefinedFormalParametersAsserter: UserDefinedFormalParametersAsserter
) {

    fun evaluateLispFunction(
        params: Node
    ): UserDefinedFunction {
        functionLengthAsserter.assertLengthIsAsExpected(
            FunctionNameConstants.DEFUN,
            FunctionLengthConstants.FOUR,
            params
        )
        val paramsExpressionNode = listValueRetriever.retrieveListValue(
            params,
            FunctionNameConstants.DEFUN,
            mapOf()
        )
        val functionName = atomicValueRetriever.retrieveAtomicValue(
            paramsExpressionNode.address,
            FunctionLengthConstants.ONE,
            FunctionNameConstants.DEFUN
        )
        userDefinedFunctionNameAsserter.assertFunctionNameIsValid(functionName)
        val paramsData = paramsExpressionNode.data
        val tempNode = listValueRetriever.retrieveListValue(
            paramsData,
            FunctionNameConstants.DEFUN,
            mapOf()
        )
        val formalParameters = userDefinedFunctionFormalParameterGenerator.getFormalParameters(
            tempNode.address,
            FunctionLengthConstants.ONE,
            mapOf()
        )
        userDefinedFormalParametersAsserter.assertFormalParameters(formalParameters)
        return UserDefinedFunction(
            formalParameters,
            tempNode.data,
            functionName
        )
    }
}