package com.soapdogg.lispInterpreter.evaluator

import com.soapdogg.lispInterpreter.datamodels.ExpressionListNode
import com.soapdogg.lispInterpreter.datamodels.NodeV2
import com.soapdogg.lispInterpreter.datamodels.ProgramStackItem
import com.soapdogg.lispInterpreter.datamodels.Stacks
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import java.util.*

class QuoteFunctionEvaluatorTest {

    private val postEvaluationStackUpdater = Mockito.mock(PostEvaluationStackUpdater::class.java)

    private val quoteFunctionEvaluator = QuoteFunctionEvaluator(postEvaluationStackUpdater)

    @Test
    fun evaluateQuoteFunctionTest() {
        val top = Mockito.mock(ProgramStackItem::class.java)
        val evalStack = Stack<NodeV2>()
        val programStack = Stack<ProgramStackItem>()

        val quoteExprNode = Mockito.mock(ExpressionListNode::class.java)
        Mockito.`when`(top.functionExpressionNode).thenReturn(quoteExprNode)

        val child0 = Mockito.mock(NodeV2::class.java)
        val quoted = Mockito.mock(NodeV2::class.java)
        val quoteExprNodeChildren = listOf(child0, quoted)
        Mockito.`when`(quoteExprNode.children).thenReturn(quoteExprNodeChildren)

        val stacks = Mockito.mock(Stacks::class.java)
        Mockito.`when`(
            postEvaluationStackUpdater.updateStacksAfterEvaluation(
                quoted,
                evalStack,
                programStack
            )
        ).thenReturn(stacks)

        val actual = quoteFunctionEvaluator.evaluateQuoteFunction(
            top,
            evalStack,
            programStack
        )

        Assertions.assertEquals(stacks, actual)
    }
}