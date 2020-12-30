package com.soapdogg.lispInterpreter.evaluator

import com.soapdogg.lispInterpreter.datamodels.MyStack
import com.soapdogg.lispInterpreter.datamodels.NodeV2
import com.soapdogg.lispInterpreter.datamodels.ProgramStackItem
import com.soapdogg.lispInterpreter.datamodels.UserDefinedFunction
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import java.util.*

class UserDefinedFunctionEvaluatorTest {

    private val stackUpdateDeterminer = Mockito.mock(StackUpdateDeterminer::class.java)

    private val userDefinedFunctionEvaluator = UserDefinedFunctionEvaluator(
        stackUpdateDeterminer
    )

    @Test
    fun evaluateUserDefinedFunctionTest() {
        val userDefinedFunction = Mockito.mock(UserDefinedFunction::class.java)
        val variableMap = emptyMap<String, NodeV2>()
        val functionStack = MyStack<NodeV2>()
        val evalStack = Stack<NodeV2>()
        val programStack = Stack<ProgramStackItem>()

        val formalParameter0 = "formalParameter0"
        Mockito.`when`(userDefinedFunction.formalParameters).thenReturn(listOf(formalParameter0))
        val paramValue = Mockito.mock(NodeV2::class.java)

        val mapCopy = mapOf(
            Pair(formalParameter0, paramValue)
        )

        functionStack.push(paramValue)

        val userDefinedFunctionBody = Mockito.mock(NodeV2::class.java)
        Mockito.`when`(userDefinedFunction.body).thenReturn(userDefinedFunctionBody)

        userDefinedFunctionEvaluator.evaluateUserDefinedFunction(
            userDefinedFunction,
            variableMap,
            functionStack,
            evalStack,
            programStack
        )

        Mockito.verify(stackUpdateDeterminer).determineHowToUpdateStacks(
            userDefinedFunctionBody,
            mapCopy,
            evalStack,
            programStack
        )
    }
}