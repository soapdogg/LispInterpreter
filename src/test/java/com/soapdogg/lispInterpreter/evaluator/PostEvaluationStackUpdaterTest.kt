package com.soapdogg.lispInterpreter.evaluator

import com.soapdogg.lispInterpreter.datamodels.NodeV2
import com.soapdogg.lispInterpreter.datamodels.ProgramStackItem
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import java.util.*

class PostEvaluationStackUpdaterTest {

    private val topProgramStackItemUpdater = Mockito.mock(TopProgramStackItemUpdater::class.java)

    private val postEvaluationStackUpdater = PostEvaluationStackUpdater(
        topProgramStackItemUpdater
    )

    @Test
    fun updateStacksAfterEvaluationTest() {
        val evaluatedNode = Mockito.mock(NodeV2::class.java)
        val variableMap = emptyMap<String, NodeV2>()
        val evalStack = Stack<NodeV2>()
        val programStack = Stack<ProgramStackItem>()

        val updatedProgramStack = Stack<ProgramStackItem>()
        Mockito.`when`(topProgramStackItemUpdater.updateTopProgramStackItemToNextChild(programStack)).thenReturn(updatedProgramStack)

        val actual = postEvaluationStackUpdater.updateStacksAfterEvaluation(
            evaluatedNode,
            variableMap,
            evalStack,
            programStack
        )

        Assertions.assertEquals(evalStack, actual.evalStack)
        Assertions.assertEquals(evaluatedNode, evalStack.peek())
        Assertions.assertEquals(updatedProgramStack, actual.programStack)
    }
}