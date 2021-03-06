package com.soapdogg.lispInterpreter.evaluator

import com.soapdogg.lispInterpreter.datamodels.Stack
import com.soapdogg.lispInterpreter.datamodels.NodeV2
import com.soapdogg.lispInterpreter.datamodels.ProgramStackItem
import com.soapdogg.lispInterpreter.datamodels.UserDefinedFunction
import kotlin.collections.HashMap

class UserDefinedFunctionEvaluator(
    private val stackUpdateDeterminer: StackUpdateDeterminer
) {

    fun evaluateUserDefinedFunction(
        userDefinedFunction: UserDefinedFunction,
        variableMap: Map<String, NodeV2>,
        functionStack: Stack<NodeV2>,
        evalStack: Stack<NodeV2>,
        programStack: Stack<ProgramStackItem>
    ) {
        val mapCopy = HashMap(variableMap)
        userDefinedFunction.formalParameters.forEach {
            val param = functionStack.pop()
            mapCopy[it] = param
        }
        stackUpdateDeterminer.determineHowToUpdateStacks(
            userDefinedFunction.body,
            mapCopy,
            evalStack,
            programStack
        )
    }
}