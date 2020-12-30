package com.soapdogg.lispInterpreter.evaluator

import com.soapdogg.lispInterpreter.datamodels.ExpressionListNode
import com.soapdogg.lispInterpreter.datamodels.MyStack
import com.soapdogg.lispInterpreter.datamodels.NodeV2
import com.soapdogg.lispInterpreter.datamodels.ProgramStackItem
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
        val programStack = MyStack<ProgramStackItem>()

        val quoteExprNode = Mockito.mock(ExpressionListNode::class.java)
        Mockito.`when`(top.functionExpressionNode).thenReturn(quoteExprNode)

        val child0 = Mockito.mock(NodeV2::class.java)
        val quoted = Mockito.mock(NodeV2::class.java)
        val quoteExprNodeChildren = listOf(child0, quoted)
        Mockito.`when`(quoteExprNode.children).thenReturn(quoteExprNodeChildren)

        val variableMap = emptyMap<String, NodeV2>()
        Mockito.`when`(top.variableMap).thenReturn(variableMap)

        quoteFunctionEvaluator.evaluateQuoteFunction(
            top,
            evalStack,
            programStack
        )

        Mockito.verify(postEvaluationStackUpdater).updateStacksAfterEvaluation(
            quoted,
            variableMap,
            evalStack,
            programStack
        )
    }
}