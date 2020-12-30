package com.soapdogg.lispInterpreter.function

import com.soapdogg.lispInterpreter.constants.FunctionNameConstants
import com.soapdogg.lispInterpreter.datamodels.ExpressionListNode
import com.soapdogg.lispInterpreter.datamodels.Stack
import com.soapdogg.lispInterpreter.datamodels.NodeV2
import com.soapdogg.lispInterpreter.generator.NodeGenerator
import com.soapdogg.lispInterpreter.valueretriver.ListValueRetriever
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class CdrFunctionTest {

    private val listValueRetriever = Mockito.mock(ListValueRetriever::class.java)
    private val nodeGenerator = Mockito.mock(NodeGenerator::class.java)

    private val params = Stack<NodeV2>()

    private val cdrFunction = CdrFunction(
        listValueRetriever,
        nodeGenerator
    )

    @Test
    fun listHasOneChildTest() {
        val first = Mockito.mock(NodeV2::class.java)
        params.push(first)

        val firstExpressionListNode = Mockito.mock(ExpressionListNode::class.java)
        Mockito.`when`(
            listValueRetriever.retrieveListValue(
                first,
                FunctionNameConstants.CDR
            )
        ).thenReturn(firstExpressionListNode)

        val child0 = Mockito.mock(NodeV2::class.java)
        val children = listOf(child0)
        Mockito.`when`(firstExpressionListNode.children).thenReturn(children)

        val actual = cdrFunction.evaluate(
            params
        )

        Assertions.assertEquals(child0, actual)
        Mockito.verifyNoInteractions(nodeGenerator)
    }

    @Test
    fun listHasTwoChildrenTest() {
        val first = Mockito.mock(NodeV2::class.java)
        params.push(first)

        val firstExpressionListNode = Mockito.mock(ExpressionListNode::class.java)
        Mockito.`when`(
            listValueRetriever.retrieveListValue(
                first,
                FunctionNameConstants.CDR
            )
        ).thenReturn(firstExpressionListNode)

        val child0 = Mockito.mock(NodeV2::class.java)
        val child1 = Mockito.mock(NodeV2::class.java)
        val children = listOf(child0, child1)
        Mockito.`when`(firstExpressionListNode.children).thenReturn(children)

        val actual = cdrFunction.evaluate(
            params
        )

        Assertions.assertEquals(child1, actual)
        Mockito.verifyNoInteractions(nodeGenerator)
    }

    @Test
    fun listHasMoreThanTwoChildrenTest() {
        val first = Mockito.mock(NodeV2::class.java)
        params.push(first)

        val firstExpressionListNode = Mockito.mock(ExpressionListNode::class.java)
        Mockito.`when`(
            listValueRetriever.retrieveListValue(
                first,
                FunctionNameConstants.CDR
            )
        ).thenReturn(firstExpressionListNode)

        val child0 = Mockito.mock(NodeV2::class.java)
        val child1 = Mockito.mock(NodeV2::class.java)
        val child2 = Mockito.mock(NodeV2::class.java)
        val children = listOf(child0, child1, child2)
        Mockito.`when`(firstExpressionListNode.children).thenReturn(children)

        val expected = Mockito.mock(ExpressionListNode::class.java)
        Mockito.`when`(
            nodeGenerator.generateExpressionListNode(
                listOf(child1, child2)
            )
        ).thenReturn(expected)


        val actual = cdrFunction.evaluate(
            params
        )

        Assertions.assertEquals(expected, actual)
    }
}