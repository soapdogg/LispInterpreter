package com.soapdogg.lispInterpreter.evaluator

import com.soapdogg.lispInterpreter.constants.FunctionNameConstants
import com.soapdogg.lispInterpreter.datamodels.AtomNode
import com.soapdogg.lispInterpreter.datamodels.ExpressionListNode
import com.soapdogg.lispInterpreter.datamodels.NodeV2
import com.soapdogg.lispInterpreter.datamodels.ProgramStackItem
import com.soapdogg.lispInterpreter.generator.NodeGenerator
import com.soapdogg.lispInterpreter.generator.ProgramStackItemGenerator
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import java.util.*

class CondChildStackItemBuilderTest {

    private val nodeGenerator = Mockito.mock(NodeGenerator::class.java)
    private val programStackItemGenerator = Mockito.mock(ProgramStackItemGenerator::class.java)

    private val condChildStackItemBuilder = CondChildStackItemBuilder(
        nodeGenerator,
        programStackItemGenerator
    )

    @Test
    fun buildCondChildStackItemsTest() {
        val condProgramStackItem = Mockito.mock(ProgramStackItem::class.java)
        val programStack = Stack<ProgramStackItem>()

        val variableMap = emptyMap<String, NodeV2>()
        Mockito.`when`(condProgramStackItem.variableMap).thenReturn(variableMap)

        val functionExpressionListNode = Mockito.mock(ExpressionListNode::class.java)
        Mockito.`when`(condProgramStackItem.functionExpressionNode).thenReturn(functionExpressionListNode)

        val child0 = Mockito.mock(NodeV2::class.java)
        val child1 = Mockito.mock(ExpressionListNode::class.java)
        val child2 = Mockito.mock(NodeV2::class.java)
        val condChildren = listOf(
            child0,
            child1,
            child2
        )

        Mockito.`when`(functionExpressionListNode.children).thenReturn(condChildren)

        val condChildAtomNode = Mockito.mock(AtomNode::class.java)
        Mockito.`when`(nodeGenerator.generateAtomNode(FunctionNameConstants.CONDCHILD)).thenReturn(condChildAtomNode)

        val grandchild = Mockito.mock(NodeV2::class.java)
        val child1Children = listOf(grandchild)
        Mockito.`when`(child1.children).thenReturn(child1Children)
        val condChildsChildren = listOf(condChildAtomNode, grandchild)
        val condChildExpressionListNode = Mockito.mock(ExpressionListNode::class.java)
        Mockito.`when`(nodeGenerator.generateExpressionListNode(condChildsChildren)).thenReturn(condChildExpressionListNode)

        val condChildStackItem = Mockito.mock(ProgramStackItem::class.java)
        Mockito.`when`(
            programStackItemGenerator.generateProgramStackItem(
                condChildExpressionListNode,
                1,
                variableMap
            )
        ).thenReturn(condChildStackItem)

        val actual = condChildStackItemBuilder.buildCondChildStackItems(
            condProgramStackItem,
            programStack
        )

        Assertions.assertEquals(1, actual.size)
        Assertions.assertEquals(condChildStackItem, actual.peek())
    }
}