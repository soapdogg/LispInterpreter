package com.soapdogg.lispInterpreter.function

import com.soapdogg.lispInterpreter.datamodels.AtomNode
import com.soapdogg.lispInterpreter.datamodels.ExpressionListNode
import com.soapdogg.lispInterpreter.datamodels.NodeV2
import com.soapdogg.lispInterpreter.determiner.NumericStringDeterminer
import com.soapdogg.lispInterpreter.generator.NodeGenerator
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import java.util.*

class IntFunctionTest {
    private val numericStringDeterminer = Mockito.mock(NumericStringDeterminer::class.java)
    private val nodeGenerator = Mockito.mock(NodeGenerator::class.java)

    private val params = Stack<NodeV2>()
    private val variableMap = mapOf<String, NodeV2>()

    private val intFunction = IntFunction(
        numericStringDeterminer,
        nodeGenerator
    )

    @Test
    fun evaluatedAtomIsNumericTest() {
        val first = Mockito.mock(AtomNode::class.java)
        params.push(first)

        val numeric = 10
        Mockito.`when`(first.value).thenReturn(numeric.toString())

        val isNumeric = true
        Mockito.`when`(numericStringDeterminer.isStringNumeric(numeric.toString())).thenReturn(isNumeric)

        val resultingNode = Mockito.mock(AtomNode::class.java)
        Mockito.`when`(nodeGenerator.generateAtomNode(isNumeric)).thenReturn(resultingNode)

        val actual = intFunction.evaluate(
            params
        )

        Assertions.assertEquals(resultingNode, actual)
    }

    @Test
    fun firstIsExpressionListTest() {
        val first = Mockito.mock(ExpressionListNode::class.java)
        params.push(first)

        val resultingNode = Mockito.mock(AtomNode::class.java)
        Mockito.`when`(nodeGenerator.generateAtomNode(false)).thenReturn(resultingNode)

        val actual = intFunction.evaluate(
            params
        )

        Assertions.assertEquals(resultingNode, actual)
    }
}