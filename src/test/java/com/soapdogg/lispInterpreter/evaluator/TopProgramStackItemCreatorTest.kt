package com.soapdogg.lispInterpreter.evaluator

import com.soapdogg.lispInterpreter.datamodels.ExpressionListNode
import com.soapdogg.lispInterpreter.datamodels.MyStack
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
        val programStack = MyStack<ProgramStackItem>()

        val top = Mockito.mock(ProgramStackItem::class.java)
        Mockito.`when`(
            programStackItemGenerator.generateProgramStackItemFromScratch(
                expressionListNode,
                variableMap
            )
        ).thenReturn(top)

        topProgramStackItemCreator.createTopProgramStackItem(
            expressionListNode,
            variableMap,
            programStack
        )

        Assertions.assertEquals(1, programStack.size())
        Assertions.assertEquals(top, programStack.peek())
    }
}