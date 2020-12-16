package com.soapdogg.lispInterpreter.valueretriver

import com.soapdogg.lispInterpreter.datamodels.AtomNode
import com.soapdogg.lispInterpreter.datamodels.NodeV2
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import java.util.*

class AtomNodeValueRetrieverTest {
    private val atomNode = Mockito.mock(AtomNode::class.java)
    private val variableName = "variableName"
    private val variableValue = Mockito.mock(NodeV2::class.java)
    private val variableNameToValueMap = Collections.singletonMap(variableName, variableValue)
    private val atomNodeEvaluator = AtomNodeValueRetriever()

    @Test
    fun atomNodeIsVariableTest() {
        Mockito.`when`(atomNode.value).thenReturn(variableName)
        val actual = atomNodeEvaluator.retrieveAtomNode(
            atomNode,
            variableNameToValueMap
        )
        Assertions.assertEquals(variableValue, actual)
    }

    @Test
    fun atomNodeIsNotAVariableTest() {
        val value = "value"
        Mockito.`when`(atomNode.value).thenReturn(value)
        val actual = atomNodeEvaluator.retrieveAtomNode(
            atomNode,
            variableNameToValueMap
        )
        Assertions.assertEquals(atomNode, actual)
    }
}