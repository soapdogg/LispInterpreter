package com.soapdogg.lispInterpreter.evaluator

import com.soapdogg.lispInterpreter.datamodels.NodeV2
import com.soapdogg.lispInterpreter.datamodels.ProgramStackItem
import com.soapdogg.lispInterpreter.datamodels.Stacks
import com.soapdogg.lispInterpreter.function.Function
import java.util.*

class BuiltInFunctionEvaluator(
    private val functionMap: Map<String, Function>,
    private val postEvaluationStackUpdater: PostEvaluationStackUpdater
) {

    fun evaluateBuiltInFunction(
        functionName: String,
        functionStack: Stack<NodeV2>,
        top: ProgramStackItem,
        evalStack: Stack<NodeV2>,
        programStack: Stack<ProgramStackItem>
    ): Stacks {
        val function = functionMap.getValue(functionName)
        val evaluatedFunctionResult = function.evaluate(functionStack, top.variableMap)
        return postEvaluationStackUpdater.updateStacksAfterEvaluation(
            evaluatedFunctionResult,
            evalStack,
            programStack
        )
    }
}