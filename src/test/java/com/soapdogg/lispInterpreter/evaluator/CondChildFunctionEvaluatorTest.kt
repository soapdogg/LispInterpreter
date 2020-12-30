package com.soapdogg.lispInterpreter.evaluator

import com.soapdogg.lispInterpreter.constants.FunctionNameConstants
import com.soapdogg.lispInterpreter.constants.ReservedValuesConstants
import com.soapdogg.lispInterpreter.datamodels.*
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import java.util.*

class CondChildFunctionEvaluatorTest {

    private val stackUpdateDeterminer = Mockito.mock(StackUpdateDeterminer::class.java)

    private val condChildFunctionEvaluator = CondChildFunctionEvaluator(
        stackUpdateDeterminer
    )

    @Test
    fun evaluateCondChildsConditionTest() {
        val top = Mockito.mock(ProgramStackItem::class.java)
        val evalStack = Stack<NodeV2>()
        val programStack = MyStack<ProgramStackItem>()

        val functionExpressionNode = Mockito.mock(ExpressionListNode::class.java)
        Mockito.`when`(top.functionExpressionNode).thenReturn(functionExpressionNode)

        val grandChild0 = Mockito.mock(NodeV2::class.java)
        val grandChild1 = Mockito.mock(NodeV2::class.java)
        val grandChild2 = Mockito.mock(NodeV2::class.java)
        val grandChildren = listOf(grandChild0, grandChild1, grandChild2)
        Mockito.`when`(functionExpressionNode.children).thenReturn(grandChildren)

        val currentParameterIndex = 0
        Mockito.`when`(top.currentParameterIndex).thenReturn(currentParameterIndex)

        val variableMap = emptyMap<String, NodeV2>()
        Mockito.`when`(top.variableMap).thenReturn(variableMap)

        condChildFunctionEvaluator.evaluateCondChildFunction(
            top,
            evalStack,
            programStack
        )

        Mockito.verify(stackUpdateDeterminer).determineHowToUpdateStacks(
            grandChild1,
            variableMap,
            evalStack,
            programStack
        )
    }

    @Test
    fun evaluateCondChildsValueTest() {
        val top = Mockito.mock(ProgramStackItem::class.java)
        val evalStack = Stack<NodeV2>()
        val programStack = MyStack<ProgramStackItem>()

        val functionExpressionNode = Mockito.mock(ExpressionListNode::class.java)
        Mockito.`when`(top.functionExpressionNode).thenReturn(functionExpressionNode)

        val grandChild0 = Mockito.mock(NodeV2::class.java)
        val grandChild1 = Mockito.mock(NodeV2::class.java)
        val grandChild2 = Mockito.mock(NodeV2::class.java)
        val grandChildren = listOf(grandChild0, grandChild1, grandChild2)
        Mockito.`when`(functionExpressionNode.children).thenReturn(grandChildren)

        val currentParameterIndex = 1
        Mockito.`when`(top.currentParameterIndex).thenReturn(currentParameterIndex)

        val evaluatedCondChild = Mockito.mock(AtomNode::class.java)
        Mockito.`when`(evaluatedCondChild.value).thenReturn(ReservedValuesConstants.T)
        evalStack.push(evaluatedCondChild)

        val programStackItem1 = Mockito.mock(ProgramStackItem::class.java)
        Mockito.`when`(programStackItem1.functionName).thenReturn(FunctionNameConstants.CONDCHILD)

        val programStackItem0 = Mockito.mock(ProgramStackItem::class.java)
        Mockito.`when`(programStackItem0.functionName).thenReturn(FunctionNameConstants.COND)

        programStack.push(programStackItem0)
        programStack.push(programStackItem1)

        val variableMap = emptyMap<String, NodeV2>()
        Mockito.`when`(top.variableMap).thenReturn(variableMap)

        condChildFunctionEvaluator.evaluateCondChildFunction(
            top,
            evalStack,
            programStack
        )

        Mockito.verify(stackUpdateDeterminer).determineHowToUpdateStacks(
            grandChild2,
            variableMap,
            evalStack,
            programStack
        )
    }
}