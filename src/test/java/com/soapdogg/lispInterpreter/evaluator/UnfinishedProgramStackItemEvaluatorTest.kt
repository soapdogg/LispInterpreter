package com.soapdogg.lispInterpreter.evaluator

import com.soapdogg.lispInterpreter.datamodels.ExpressionListNode
import com.soapdogg.lispInterpreter.datamodels.MyStack
import com.soapdogg.lispInterpreter.datamodels.NodeV2
import com.soapdogg.lispInterpreter.datamodels.ProgramStackItem
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import java.util.*

class UnfinishedProgramStackItemEvaluatorTest {

    private val stackUpdateDeterminer = Mockito.mock(StackUpdateDeterminer::class.java)

    private val unfinishedProgramStackItemEvaluator = UnfinishedProgramStackItemEvaluator(
        stackUpdateDeterminer
    )

    @Test
    fun evaluateUnfinishedProgramStackItemTest() {
        val top = Mockito.mock(ProgramStackItem::class.java)
        val evalStack = Stack<NodeV2>()
        val programStack = MyStack<ProgramStackItem>()

        val functionExpressionNode = Mockito.mock(ExpressionListNode::class.java)
        Mockito.`when`(top.functionExpressionNode).thenReturn(functionExpressionNode)

        val currentParameterIndex = 0
        Mockito.`when`(top.currentParameterIndex).thenReturn(currentParameterIndex)

        val child0 = Mockito.mock(NodeV2::class.java)
        Mockito.`when`(functionExpressionNode.children).thenReturn(listOf(child0))

        val variableMap = emptyMap<String, NodeV2>()
        Mockito.`when`(top.variableMap).thenReturn(variableMap)

        unfinishedProgramStackItemEvaluator.evaluateUnfinishedProgramStackItem(
            top,
            evalStack,
            programStack
        )

        Mockito.verify(stackUpdateDeterminer).determineHowToUpdateStacks(
            child0,
            variableMap,
            evalStack,
            programStack
        )
    }
}