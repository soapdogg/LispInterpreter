package com.soapdogg.lispInterpreter.generator

import com.soapdogg.lispInterpreter.datamodels.ExpressionNode
import com.soapdogg.lispInterpreter.datamodels.Node
import com.soapdogg.lispInterpreter.datamodels.UserDefinedFunction
import com.soapdogg.lispInterpreter.functions.DefunFunction

class UserDefinedFunctionGenerator(
    private val defunFunction: DefunFunction
) {

    fun generateUserDefinedFunctions(
        defunNodes: List<Node>
    ): List<UserDefinedFunction> {
        val defunExpressionNodes = defunNodes.map{it as ExpressionNode}
        return defunExpressionNodes.map{ defunFunction.evaluateLispFunction(it.data) }
    }
}