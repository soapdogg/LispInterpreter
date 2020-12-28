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

        postEvaluationStackUpdater.updateStacksAfterEvaluation(
            evaluatedNode,
            variableMap,
            evalStack,
            programStack
        )

        Assertions.assertEquals(evaluatedNode, evalStack.peek())
        Mockito.verify(
            topProgramStackItemUpdater
        ).updateTopProgramStackItemToNextChild(programStack)
    }
}