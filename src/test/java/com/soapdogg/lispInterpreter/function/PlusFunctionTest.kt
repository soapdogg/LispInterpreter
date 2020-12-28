package com.soapdogg.lispInterpreter.function

import com.soapdogg.lispInterpreter.constants.FunctionNameConstants
import com.soapdogg.lispInterpreter.datamodels.AtomNode
import com.soapdogg.lispInterpreter.datamodels.NodeV2
import com.soapdogg.lispInterpreter.generator.NodeGenerator
import com.soapdogg.lispInterpreter.valueretriver.NumericValueRetriever
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import java.util.*

class PlusFunctionTest {
    private val numericValueRetriever = Mockito.mock(NumericValueRetriever::class.java)
    private val nodeGenerator = Mockito.mock(NodeGenerator::class.java)

    private val params = Stack<NodeV2>()
    private val variableMap = mapOf<String, NodeV2>()

    private val plusFunction = PlusFunction(
        numericValueRetriever,
        nodeGenerator
    )

    @Test
    fun evaluateGreaterFunctionTest() {
        val first = Mockito.mock(NodeV2::class.java)
        val second = Mockito.mock(NodeV2::class.java)

        params.push(second)
        params.push(first)

        val firstNumeric = 10
        Mockito.`when`(
            numericValueRetriever.retrieveNumericValue(
                first,
                FunctionNameConstants.PLUS,
                1
            )
        ).thenReturn(firstNumeric)

        val secondNumeric = 1
        Mockito.`when`(
            numericValueRetriever.retrieveNumericValue(
                second,
                FunctionNameConstants.PLUS,
                2
            )
        ).thenReturn(secondNumeric)

        val resultingNode = Mockito.mock(AtomNode::class.java)
        Mockito.`when`(
            nodeGenerator.generateAtomNode(firstNumeric + secondNumeric)
        ).thenReturn(resultingNode)

        val actual = plusFunction.evaluate(
            params
        )

        Assertions.assertEquals(resultingNode, actual)
    }
}