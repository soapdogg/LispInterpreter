package com.soapdogg.lispInterpreter.function

import com.soapdogg.lispInterpreter.constants.ReservedValuesConstants
import com.soapdogg.lispInterpreter.datamodels.AtomNode
import com.soapdogg.lispInterpreter.datamodels.ExpressionListNode
import com.soapdogg.lispInterpreter.datamodels.Stack
import com.soapdogg.lispInterpreter.datamodels.NodeV2
import com.soapdogg.lispInterpreter.generator.NodeGenerator
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class NullFunctionTest {

    private val nodeGenerator = Mockito.mock(NodeGenerator::class.java)

    private val params = Stack<NodeV2>()

    private val nullFunction = NullFunction(
        nodeGenerator
    )

    @Test
    fun evaluatedAtomIsNilTest() {
        val first = Mockito.mock(AtomNode::class.java)
        params.push(first)

        Mockito.`when`(first.value).thenReturn(ReservedValuesConstants.NIL)

        val resultingNode = Mockito.mock(AtomNode::class.java)
        Mockito.`when`(nodeGenerator.generateAtomNode(true)).thenReturn(resultingNode)

        val actual = nullFunction.evaluate(
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

        val actual = nullFunction.evaluate(
            params
        )

        Assertions.assertEquals(resultingNode, actual)
    }
}