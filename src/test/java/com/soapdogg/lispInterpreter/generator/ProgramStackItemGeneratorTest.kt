package com.soapdogg.lispInterpreter.generator

import com.soapdogg.lispInterpreter.datamodels.AtomNode
import com.soapdogg.lispInterpreter.datamodels.ExpressionListNode
import com.soapdogg.lispInterpreter.datamodels.NodeV2
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class ProgramStackItemGeneratorTest {

    private val functionExpressionNode = Mockito.mock(ExpressionListNode::class.java)
    private val child0 = Mockito.mock(AtomNode::class.java)
    private val functionName = "functionName"
    private val currentParameterIndex = 0
    private val variableMap = emptyMap<String, NodeV2>()

    private val programStackItemGenerator = ProgramStackItemGenerator()

    @Test
    fun generateProgramStackItemTest() {
        Mockito.`when`(functionExpressionNode.children).thenReturn(listOf(child0))
        Mockito.`when`(child0.value).thenReturn(functionName)

        val actual = programStackItemGenerator.generateProgramStackItem(
            functionExpressionNode,
            currentParameterIndex,
            variableMap
        )

        Assertions.assertEquals(functionExpressionNode, actual.functionExpressionNode)
        Assertions.assertEquals(currentParameterIndex, actual.currentParameterIndex)
        Assertions.assertEquals(variableMap, actual.variableMap)
        Assertions.assertEquals(functionName, actual.functionName)
    }
}