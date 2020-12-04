package com.soapdogg.lispInterpreter.interpreter

import com.soapdogg.lispInterpreter.constants.FunctionNameConstants
import com.soapdogg.lispInterpreter.datamodels.*
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class RootNodePartitionerTest {

    private val rootNodePartitioner = RootNodePartitioner()

     @Test
    fun atomRootNodeTest() {
         val rootNode = Mockito.mock(AtomNode::class.java)
         val rootNodes= listOf(rootNode)

         val (defunNodes, evaluatableNodes) = rootNodePartitioner.partitionRootNodes(
            rootNodes
         )
         Assertions.assertTrue(defunNodes.isEmpty())
         Assertions.assertEquals(1, evaluatableNodes.size)
         Assertions.assertEquals(rootNode, evaluatableNodes[0])
    }

    @Test
    fun expressionRootNodeWithExpressionAddressTest() {
        val rootNode = Mockito.mock(ExpressionListNode::class.java)

        val address = Mockito.mock(ExpressionListNode::class.java)
        val children = listOf(address)
        Mockito.`when`(rootNode.children).thenReturn(children)

        val rootNodes = listOf(rootNode)
        val (defunNodes, evaluatableNodes) = rootNodePartitioner.partitionRootNodes(
            rootNodes
        )

        Assertions.assertTrue(defunNodes.isEmpty())
        Assertions.assertEquals(1, evaluatableNodes.size)
        Assertions.assertEquals(rootNode, evaluatableNodes[0])
    }

    @Test
    fun expressionRootNodeWithAtomAddressNotDefunTest() {
        val rootNode = Mockito.mock(ExpressionListNode::class.java)

        val address = Mockito.mock(AtomNode::class.java)
        val children = listOf(address)
        Mockito.`when`(rootNode.children).thenReturn(children)

        val value = "value"
        Mockito.`when`(address.value).thenReturn(value)

        val rootNodes = listOf(rootNode)
        val (defunNodes, evaluatableNodes) = rootNodePartitioner.partitionRootNodes(
            rootNodes
        )
        Assertions.assertTrue(defunNodes.isEmpty())
        Assertions.assertEquals(1, evaluatableNodes.size)
        Assertions.assertEquals(rootNode, evaluatableNodes[0])
    }

    @Test
    fun expressionRootNodeWithAtomAddressDefunTest() {
        val rootNode = Mockito.mock(ExpressionListNode::class.java)

        val address = Mockito.mock(AtomNode::class.java)
        val children = listOf(address)
        Mockito.`when`(rootNode.children).thenReturn(children)

        val value = FunctionNameConstants.DEFUN
        Mockito.`when`(address.value).thenReturn(value)

        val rootNodes = listOf(rootNode)
        val (defunNodes, evaluatableNodes) = rootNodePartitioner.partitionRootNodes(
            rootNodes
        )
        Assertions.assertTrue(evaluatableNodes.isEmpty())
        Assertions.assertEquals(1, defunNodes.size)
        Assertions.assertEquals(rootNode, defunNodes[0])
    }
}