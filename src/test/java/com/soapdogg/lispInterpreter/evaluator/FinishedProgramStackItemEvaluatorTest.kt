package com.soapdogg.lispInterpreter.evaluator

import com.soapdogg.lispInterpreter.constants.FunctionNameConstants
import com.soapdogg.lispInterpreter.constants.ReservedValuesConstants
import com.soapdogg.lispInterpreter.datamodels.ExpressionListNode
import com.soapdogg.lispInterpreter.datamodels.NodeV2
import com.soapdogg.lispInterpreter.datamodels.ProgramStackItem
import com.soapdogg.lispInterpreter.datamodels.UserDefinedFunction
import com.soapdogg.lispInterpreter.function.Function
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import java.lang.Exception
import java.util.*
import kotlin.collections.HashMap

class FinishedProgramStackItemEvaluatorTest {

    private val postEvaluationStackUpdater = Mockito.mock(PostEvaluationStackUpdater::class.java)
    private val functionMap = HashMap<String, Function>()
    private val builtInFunctionEvaluator = Mockito.mock(BuiltInFunctionEvaluator::class.java)
    private val userDefinedFunctionEvaluator = Mockito.mock(UserDefinedFunctionEvaluator::class.java)

    private val finishedProgramStackItemEvaluator = FinishedProgramStackItemEvaluator(
        postEvaluationStackUpdater,
        functionMap,
        builtInFunctionEvaluator,
        userDefinedFunctionEvaluator
    )

    @Test
    fun functionNameIsNilTest() {
        val top = Mockito.mock(ProgramStackItem::class.java)
        val userDefinedFunctions = emptyMap<String, UserDefinedFunction>()
        val evalStack = Stack<NodeV2>()
        val programStack = Stack<ProgramStackItem>()

        val functionExpressionNode = Mockito.mock(ExpressionListNode::class.java)
        Mockito.`when`(top.functionExpressionNode).thenReturn(functionExpressionNode)

        val child0 = Mockito.mock(NodeV2::class.java)
        val child1 = Mockito.mock(NodeV2::class.java)
        Mockito.`when`(functionExpressionNode.children).thenReturn(listOf(child0, child1))
        evalStack.push(child0)
        evalStack.push(child1)

        val variableMap = emptyMap<String, NodeV2>()
        Mockito.`when`(top.variableMap).thenReturn(variableMap)

        val functionName = ReservedValuesConstants.NIL
        Mockito.`when`(top.functionName).thenReturn(functionName)

        Assertions.assertDoesNotThrow {
            finishedProgramStackItemEvaluator.evaluateFinishedProgramStackItem(
                top,
                userDefinedFunctions,
                evalStack,
                programStack
            )
        }

        Mockito.verify(postEvaluationStackUpdater).updateStacksAfterEvaluation(
            child1,
            variableMap,
            evalStack,
            programStack
        )


        Mockito.verifyNoInteractions(builtInFunctionEvaluator)
        Mockito.verifyNoInteractions(userDefinedFunctionEvaluator)
    }

    @Test
    fun functionNameIsBuiltInFunctionTest() {
        val top = Mockito.mock(ProgramStackItem::class.java)
        val userDefinedFunctions = emptyMap<String, UserDefinedFunction>()
        val evalStack = Stack<NodeV2>()
        val programStack = Stack<ProgramStackItem>()

        val functionExpressionNode = Mockito.mock(ExpressionListNode::class.java)
        Mockito.`when`(top.functionExpressionNode).thenReturn(functionExpressionNode)

        val child0 = Mockito.mock(NodeV2::class.java)
        val child1 = Mockito.mock(NodeV2::class.java)
        Mockito.`when`(functionExpressionNode.children).thenReturn(listOf(child0, child1))
        evalStack.push(child0)
        evalStack.push(child1)

        val variableMap = emptyMap<String, NodeV2>()
        Mockito.`when`(top.variableMap).thenReturn(variableMap)

        val functionName = FunctionNameConstants.LESS
        Mockito.`when`(top.functionName).thenReturn(functionName)

        val function = Mockito.mock(Function::class.java)
        functionMap[functionName] = function

        val functionStack = Stack<NodeV2>()

        Assertions.assertDoesNotThrow {
            finishedProgramStackItemEvaluator.evaluateFinishedProgramStackItem(
                top,
                userDefinedFunctions,
                evalStack,
                programStack
            )
        }

        Mockito.verify(builtInFunctionEvaluator).evaluateBuiltInFunction(
            functionName,
            functionStack,
            top,
            evalStack,
            programStack
        )

        Mockito.verifyNoInteractions(postEvaluationStackUpdater)
        Mockito.verifyNoInteractions(userDefinedFunctionEvaluator)
    }

    @Test
    fun functionNameIsUserDefinedFunctionTest() {
        val top = Mockito.mock(ProgramStackItem::class.java)
        val evalStack = Stack<NodeV2>()
        val programStack = Stack<ProgramStackItem>()

        val functionExpressionNode = Mockito.mock(ExpressionListNode::class.java)
        Mockito.`when`(top.functionExpressionNode).thenReturn(functionExpressionNode)

        val child0 = Mockito.mock(NodeV2::class.java)
        val child1 = Mockito.mock(NodeV2::class.java)
        Mockito.`when`(functionExpressionNode.children).thenReturn(listOf(child0, child1))
        evalStack.push(child0)
        evalStack.push(child1)

        val variableMap = emptyMap<String, NodeV2>()
        Mockito.`when`(top.variableMap).thenReturn(variableMap)

        val functionName = "userDefinedFunction"
        Mockito.`when`(top.functionName).thenReturn(functionName)

        val userDefinedFunction = Mockito.mock(UserDefinedFunction::class.java)

        val userDefinedFunctions = mapOf(
            Pair(functionName, userDefinedFunction)
        )

        val functionStack = Stack<NodeV2>()

        Assertions.assertDoesNotThrow {
            finishedProgramStackItemEvaluator.evaluateFinishedProgramStackItem(
                top,
                userDefinedFunctions,
                evalStack,
                programStack
            )
        }

        Mockito.verify(userDefinedFunctionEvaluator).evaluateUserDefinedFunction(
            userDefinedFunction,
            variableMap,
            functionStack,
            evalStack,
            programStack
        )


        Mockito.verifyNoInteractions(postEvaluationStackUpdater)
        Mockito.verifyNoInteractions(builtInFunctionEvaluator)
    }

    @Test
    fun functionNameIsInvalidCarValueTest() {
        val top = Mockito.mock(ProgramStackItem::class.java)
        val userDefinedFunctions = emptyMap<String, UserDefinedFunction>()
        val evalStack = Stack<NodeV2>()
        val programStack = Stack<ProgramStackItem>()

        val functionExpressionNode = Mockito.mock(ExpressionListNode::class.java)
        Mockito.`when`(top.functionExpressionNode).thenReturn(functionExpressionNode)

        val child0 = Mockito.mock(NodeV2::class.java)
        val child1 = Mockito.mock(NodeV2::class.java)
        Mockito.`when`(functionExpressionNode.children).thenReturn(listOf(child0, child1))
        evalStack.push(child0)
        evalStack.push(child1)

        val variableMap = emptyMap<String, NodeV2>()
        Mockito.`when`(top.variableMap).thenReturn(variableMap)

        val functionName = "invalid"
        Mockito.`when`(top.functionName).thenReturn(functionName)

        Assertions.assertThrows(
            Exception::class.java
        ) {
            finishedProgramStackItemEvaluator.evaluateFinishedProgramStackItem(
                top,
                userDefinedFunctions,
                evalStack,
                programStack
            )
        }

        Mockito.verifyNoInteractions(postEvaluationStackUpdater)
        Mockito.verifyNoInteractions(builtInFunctionEvaluator)
        Mockito.verifyNoInteractions(userDefinedFunctionEvaluator)
    }
}