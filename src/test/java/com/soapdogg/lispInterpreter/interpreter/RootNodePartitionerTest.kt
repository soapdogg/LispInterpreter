package com.soapdogg.lispInterpreter.interpreter

import com.soapdogg.lispInterpreter.constants.FunctionNameConstants
import com.soapdogg.lispInterpreter.datamodels.AtomNode
import com.soapdogg.lispInterpreter.datamodels.ExpressionNode
import com.soapdogg.lispInterpreter.datamodels.Node
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class RootNodePartitionerTest {

    private val rootNodePartitioner = RootNodePartitioner()

     @Test
    fun atomRootNodeTest() {
        val rootNode = Mockito.mock(AtomNode::class.java)
        val rootNodes: List<Node> = listOf(rootNode)
        val (defunNodes, evaluatableNodes) = rootNodePartitioner.partitionRootNodes(
            rootNodes
        )
        Assertions.assertTrue(defunNodes.isEmpty())
        Assertions.assertEquals(1, evaluatableNodes.size)
        Assertions.assertEquals(rootNode, evaluatableNodes[0])
    }

    @Test
    fun expressionRootNodeWithExpressionAddressTest() {
        val rootNode = Mockito.mock(ExpressionNode::class.java)
        val address: Node = Mockito.mock(ExpressionNode::class.java)
        Mockito.`when`(rootNode.address).thenReturn(address)
        val rootNodes: List<Node> = listOf(rootNode)
        val (defunNodes, evaluatableNodes) = rootNodePartitioner.partitionRootNodes(
            rootNodes
        )
        Assertions.assertTrue(defunNodes.isEmpty())
        Assertions.assertEquals(1, evaluatableNodes.size)
        Assertions.assertEquals(rootNode, evaluatableNodes[0])
    }

    @Test
    fun expressionRootNodeWithAtomAddressNotDefunTest() {
        val rootNode = Mockito.mock(ExpressionNode::class.java)
        val address = Mockito.mock(AtomNode::class.java)
        Mockito.`when`(rootNode.address).thenReturn(address)
        val value = "value"
        Mockito.`when`(address.value).thenReturn(value)
        val rootNodes: List<Node> = listOf(rootNode)
        val (defunNodes, evaluatableNodes) = rootNodePartitioner.partitionRootNodes(
            rootNodes
        )
        Assertions.assertTrue(defunNodes.isEmpty())
        Assertions.assertEquals(1, evaluatableNodes.size)
        Assertions.assertEquals(rootNode, evaluatableNodes[0])
    }

    @Test
    fun expressionRootNodeWithAtomAddressDefunTest() {
        val rootNode = Mockito.mock(ExpressionNode::class.java)
        val address = Mockito.mock(AtomNode::class.java)
        Mockito.`when`(rootNode.address).thenReturn(address)
        val value = FunctionNameConstants.DEFUN
        Mockito.`when`(address.value).thenReturn(value)
        val rootNodes: List<Node> = listOf(rootNode)
        val (defunNodes, evaluatableNodes) = rootNodePartitioner.partitionRootNodes(
            rootNodes
        )
        Assertions.assertTrue(evaluatableNodes.isEmpty())
        Assertions.assertEquals(1, defunNodes.size)
        Assertions.assertEquals(rootNode, defunNodes[0])
    }
}