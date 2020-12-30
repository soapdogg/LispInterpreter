package com.soapdogg.lispInterpreter.evaluator

import com.soapdogg.lispInterpreter.datamodels.*
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import java.util.*

class StackUpdaterDeterminerTest {

    private val topProgramStackItemCreator = Mockito.mock(TopProgramStackItemCreator::class.java)
    private val postEvaluationStackUpdater = Mockito.mock(PostEvaluationStackUpdater::class.java)

    private val stackUpdateDeterminer = StackUpdateDeterminer(
        topProgramStackItemCreator,
        postEvaluationStackUpdater
    )

    @Test
    fun nodeIsExpressionListWithMoreThanOneChildTest() {
        val node = Mockito.mock(ExpressionListNode::class.java)
        val variableMap = emptyMap<String, NodeV2>()
        val evalStack = MyStack<NodeV2>()
        val programStack = MyStack<ProgramStackItem>()

        val child0 = Mockito.mock(NodeV2::class.java)
        val child1 = Mockito.mock(NodeV2::class.java)
        val children = listOf(child0, child1)
        Mockito.`when`(node.children).thenReturn(children)

        stackUpdateDeterminer.determineHowToUpdateStacks(
            node,
            variableMap,
            evalStack,
            programStack
        )

        Mockito.verify(topProgramStackItemCreator).createTopProgramStackItem(
            node,
            variableMap,
            programStack
        )

        Mockito.verifyNoInteractions(postEvaluationStackUpdater)
    }

    @Test
    fun nodeIsExpressionListWithOneChildTest() {
        val node = Mockito.mock(ExpressionListNode::class.java)
        val variableMap = emptyMap<String, NodeV2>()
        val evalStack = MyStack<NodeV2>()
        val programStack = MyStack<ProgramStackItem>()

        val child0 = Mockito.mock(NodeV2::class.java)
        val children = listOf(child0)
        Mockito.`when`(node.children).thenReturn(children)

        stackUpdateDeterminer.determineHowToUpdateStacks(
            node,
            variableMap,
            evalStack,
            programStack
        )

        Mockito.verify(postEvaluationStackUpdater).updateStacksAfterEvaluation(
            child0,
            variableMap,
            evalStack,
            programStack
        )

        Mockito.verifyNoInteractions(topProgramStackItemCreator)
    }

    @Test
    fun nodeIsAtomNodeTest() {
        val node = Mockito.mock(AtomNode::class.java)
        val variableMap = emptyMap<String, NodeV2>()
        val evalStack = MyStack<NodeV2>()
        val programStack = MyStack<ProgramStackItem>()

        stackUpdateDeterminer.determineHowToUpdateStacks(
            node,
            variableMap,
            evalStack,
            programStack
        )

        Mockito.verify(postEvaluationStackUpdater).updateStacksAfterEvaluation(
            node,
            variableMap,
            evalStack,
            programStack
        )

        Mockito.verifyNoInteractions(topProgramStackItemCreator)
    }
}