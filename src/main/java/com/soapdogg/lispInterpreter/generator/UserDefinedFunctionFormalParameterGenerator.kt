package com.soapdogg.lispInterpreter.generator

import com.soapdogg.lispInterpreter.constants.FunctionNameConstants
import com.soapdogg.lispInterpreter.datamodels.Node
import com.soapdogg.lispInterpreter.determiner.ExpressionNodeDeterminer
import com.soapdogg.lispInterpreter.valueretriver.AtomicValueRetriever
import com.soapdogg.lispInterpreter.valueretriver.ListValueRetriever

class UserDefinedFunctionFormalParameterGenerator (
    private val expressionNodeDeterminer: ExpressionNodeDeterminer,
    private val listValueRetriever: ListValueRetriever,
    private val atomicValueRetriever: AtomicValueRetriever
){

    fun getFormalParameters(
        formalParametersNode: Node,
        parameterCounter: Int,
        variableNameToValueMap: Map<String, Node>
    ): List<String> {
        val isExpressionNode = expressionNodeDeterminer.isExpressionNode(formalParametersNode)
        if (!isExpressionNode) {
            return listOf()
        }
        val formalNode = listValueRetriever.retrieveListValue(
            formalParametersNode,
            FunctionNameConstants.DEFUN,
            variableNameToValueMap
        )
        val formalId = atomicValueRetriever.retrieveAtomicValue(
            formalNode.address,
            parameterCounter,
            FunctionNameConstants.DEFUN
        )
        val data = formalNode.data
        val nextParameters = getFormalParameters(
            data,
            parameterCounter + 1,
            variableNameToValueMap
        )

        return listOf(formalId) + nextParameters
    }
}