package com.soapdogg.lispInterpreter.evaluator

import com.soapdogg.lispInterpreter.datamodels.MyStack
import com.soapdogg.lispInterpreter.datamodels.NodeV2
import com.soapdogg.lispInterpreter.datamodels.ProgramStackItem
import com.soapdogg.lispInterpreter.function.Function
import java.util.*

class BuiltInFunctionEvaluator(
    private val functionMap: Map<String, Function>,
    private val postEvaluationStackUpdater: PostEvaluationStackUpdater
) {

    fun evaluateBuiltInFunction(
        functionName: String,
        functionStack: MyStack<NodeV2>,
        top: ProgramStackItem,
        evalStack: Stack<NodeV2>,
        programStack: MyStack<ProgramStackItem>
    ) {
        val function = functionMap.getValue(functionName)
        val evaluatedFunctionResult = function.evaluate(functionStack)
        postEvaluationStackUpdater.updateStacksAfterEvaluation(
            evaluatedFunctionResult,
            top.variableMap,
            evalStack,
            programStack
        )
    }
}