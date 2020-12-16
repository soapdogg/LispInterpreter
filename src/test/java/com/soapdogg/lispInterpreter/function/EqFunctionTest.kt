package com.soapdogg.lispInterpreter.function

import com.soapdogg.lispInterpreter.datamodels.AtomNode
import com.soapdogg.lispInterpreter.datamodels.ExpressionListNode
import com.soapdogg.lispInterpreter.datamodels.NodeV2
import com.soapdogg.lispInterpreter.generator.NodeGenerator
import com.soapdogg.lispInterpreter.valueretriver.AtomNodeValueRetriever
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import java.util.*

class EqFunctionTest {

    private val atomNodeValueRetriever = Mockito.mock(AtomNodeValueRetriever::class.java)
    private val nodeGenerator = Mockito.mock(NodeGenerator::class.java)

    private val params = Stack<NodeV2>()
    private val variableMap = mapOf<String, NodeV2>()

    private val eqFunction = EqFunction(
        atomNodeValueRetriever,
        nodeGenerator
    )

    @Test
    fun atomNodeTest() {
        val first = Mockito.mock(AtomNode::class.java)
        val second = Mockito.mock(AtomNode::class.java)

        params.push(second)
        params.push(first)

        Mockito.`when`(
            atomNodeValueRetriever.retrieveAtomNode(
                first,
                variableMap
            )
        ).thenReturn(first)

        Mockito.`when`(
            atomNodeValueRetriever.retrieveAtomNode(
                second,
                variableMap
            )
        ).thenReturn(second)

        val resultingNode = Mockito.mock(AtomNode::class.java)
        Mockito.`when`(
            nodeGenerator.generateAtomNode(false)
        ).thenReturn(resultingNode)

        val actual = eqFunction.evaluate(
            params,
            variableMap
        )

        Assertions.assertEquals(resultingNode, actual)
    }

    @Test
    fun expressionNodeTest() {
        val first = Mockito.mock(ExpressionListNode::class.java)
        val second = Mockito.mock(ExpressionListNode::class.java)

        params.push(second)
        params.push(first)

        val resultingNode = Mockito.mock(AtomNode::class.java)
        Mockito.`when`(
            nodeGenerator.generateAtomNode(false)
        ).thenReturn(resultingNode)

        val actual = eqFunction.evaluate(
            params,
            variableMap
        )

        Assertions.assertEquals(resultingNode, actual)
    }
}