package com.soapdogg.lispInterpreter.function

import com.soapdogg.lispInterpreter.constants.FunctionNameConstants
import com.soapdogg.lispInterpreter.datamodels.ExpressionListNode
import com.soapdogg.lispInterpreter.datamodels.MyStack
import com.soapdogg.lispInterpreter.datamodels.NodeV2
import com.soapdogg.lispInterpreter.valueretriver.ListValueRetriever
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import java.util.*

class CarFunctionTest {

    private val listValueRetriever = Mockito.mock(ListValueRetriever::class.java)

    private val params = MyStack<NodeV2>()

    private val carFunction = CarFunction(
        listValueRetriever
    )

    @Test
    fun evaluateCarFunctionTest() {
        val first = Mockito.mock(NodeV2::class.java)
        params.push(first)

        val firstExpressionListNode = Mockito.mock(ExpressionListNode::class.java)
        Mockito.`when`(
            listValueRetriever.retrieveListValue(
                first,
                FunctionNameConstants.CAR
            )
        ).thenReturn(firstExpressionListNode)

        val child0 = Mockito.mock(NodeV2::class.java)
        val children = listOf(child0)
        Mockito.`when`(firstExpressionListNode.children).thenReturn(children)

        val actual = carFunction.evaluate(
            params
        )

        Assertions.assertEquals(child0, actual)
    }
}