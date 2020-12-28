package com.soapdogg.lispInterpreter.evaluator

import com.soapdogg.lispInterpreter.datamodels.ExpressionListNode
import com.soapdogg.lispInterpreter.datamodels.NodeV2
import com.soapdogg.lispInterpreter.datamodels.ProgramStackItem
import com.soapdogg.lispInterpreter.generator.ProgramStackItemGenerator
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import java.util.*

class TopProgramStackItemCreatorTest {

    private val programStackItemGenerator = Mockito.mock(ProgramStackItemGenerator::class.java)

    private val topProgramStackItemCreator = TopProgramStackItemCreator(programStackItemGenerator)

    @Test
    fun createTopProgramStackItemTest() {
        val expressionListNode = Mockito.mock(ExpressionListNode::class.java)
        val variableMap = emptyMap<String, NodeV2>()
        val programStack = Stack<ProgramStackItem>()

        val top = Mockito.mock(ProgramStackItem::class.java)
        Mockito.`when`(
            programStackItemGenerator.generateProgramStackItem(
                expressionListNode,
                0,
                variableMap
            )
        ).thenReturn(top)

        topProgramStackItemCreator.createTopProgramStackItem(
            expressionListNode,
            variableMap,
            programStack
        )

        Assertions.assertEquals(1, programStack.size)
        Assertions.assertEquals(top, programStack.peek())
    }
}