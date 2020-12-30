package com.soapdogg.lispInterpreter.evaluator

import com.soapdogg.lispInterpreter.datamodels.Stack
import com.soapdogg.lispInterpreter.datamodels.NodeV2
import com.soapdogg.lispInterpreter.datamodels.ProgramStackItem
import com.soapdogg.lispInterpreter.function.Function
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class BuiltInFunctionEvaluatorTest {

    private val functionName = "functionName"
    private val function = Mockito.mock(Function::class.java)
    private val functionMap = mapOf(
        Pair(functionName, function)
    )
    private val postEvaluationStackUpdater = Mockito.mock(PostEvaluationStackUpdater::class.java)

    private val builtInFunctionEvaluator = BuiltInFunctionEvaluator(
        functionMap,
        postEvaluationStackUpdater
    )

    @Test
    fun evaluateBuiltInFunctionTest() {
        val functionStack = Stack<NodeV2>()
        val top = Mockito.mock(ProgramStackItem::class.java)
        val evalStack = Stack<NodeV2>()
        val programStack = Stack<ProgramStackItem>()

        val variableMap = emptyMap<String, NodeV2>()
        Mockito.`when`(top.variableMap).thenReturn(variableMap)

        val evaluatedFunctionResult = Mockito.mock(NodeV2::class.java)
        Mockito.`when`(function.evaluate(functionStack)).thenReturn(evaluatedFunctionResult)

        builtInFunctionEvaluator.evaluateBuiltInFunction(
            functionName,
            functionStack,
            top,
            evalStack,
            programStack
        )

        Mockito.verify(postEvaluationStackUpdater).updateStacksAfterEvaluation(
            evaluatedFunctionResult,
            variableMap,
            evalStack,
            programStack
        )
    }
}