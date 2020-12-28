package com.soapdogg.lispInterpreter.evaluator

import com.soapdogg.lispInterpreter.datamodels.ExpressionListNode
import com.soapdogg.lispInterpreter.datamodels.NodeV2
import com.soapdogg.lispInterpreter.datamodels.ProgramStackItem
import com.soapdogg.lispInterpreter.generator.ProgramStackItemGenerator
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import java.util.*

class TopProgramStackItemUpdaterTest {

    private val programStackItemGenerator = Mockito.mock(ProgramStackItemGenerator::class.java)

    private val topProgramStackItemUpdater = TopProgramStackItemUpdater(
        programStackItemGenerator
    )

    @Test
    fun updateTopProgramStackItemToNextChildTest() {
        val programStackItem = Mockito.mock(ProgramStackItem::class.java)

        val functionExpressionNode = Mockito.mock(ExpressionListNode::class.java)
        Mockito.`when`(programStackItem.functionExpressionNode).thenReturn(functionExpressionNode)

        val currentParameterIndex = 1
        Mockito.`when`(programStackItem.currentParameterIndex).thenReturn(currentParameterIndex)

        val variableMap = emptyMap<String, NodeV2>()
        Mockito.`when`(programStackItem.variableMap).thenReturn(variableMap)

        val programStack = Stack<ProgramStackItem>()
        programStack.push(programStackItem)

        val updatedHead = Mockito.mock(ProgramStackItem::class.java)
        Mockito.`when`(
            programStackItemGenerator.generateProgramStackItem(
                functionExpressionNode,
                currentParameterIndex + 1,
                variableMap
            )
        ).thenReturn(updatedHead)

        topProgramStackItemUpdater.updateTopProgramStackItemToNextChild(
            programStack
        )

        Assertions.assertEquals(1, programStack.size)
        Assertions.assertEquals(updatedHead, programStack.peek())
    }

    @Test
    fun updateTopProgramStackItemToNextChildEmptyStackTest() {
        val programStack = Stack<ProgramStackItem>()
        topProgramStackItemUpdater.updateTopProgramStackItemToNextChild(
            programStack
        )

        Assertions.assertTrue(programStack.empty())
        Mockito.verifyNoInteractions(programStackItemGenerator)
    }
}