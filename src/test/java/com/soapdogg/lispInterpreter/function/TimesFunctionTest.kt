package com.soapdogg.lispInterpreter.function

import com.soapdogg.lispInterpreter.constants.FunctionNameConstants
import com.soapdogg.lispInterpreter.datamodels.AtomNode
import com.soapdogg.lispInterpreter.datamodels.Stack
import com.soapdogg.lispInterpreter.datamodels.NodeV2
import com.soapdogg.lispInterpreter.generator.NodeGenerator
import com.soapdogg.lispInterpreter.valueretriver.NumericValueRetriever
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class TimesFunctionTest {
    private val numericValueRetriever = Mockito.mock(NumericValueRetriever::class.java)
    private val nodeGenerator = Mockito.mock(NodeGenerator::class.java)

    private val params = Stack<NodeV2>()

    private val timesFunction = TimesFunction(
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
                FunctionNameConstants.TIMES,
                1
            )
        ).thenReturn(firstNumeric)

        val secondNumeric = 2
        Mockito.`when`(
            numericValueRetriever.retrieveNumericValue(
                second,
                FunctionNameConstants.TIMES,
                2
            )
        ).thenReturn(secondNumeric)

        val resultingNode = Mockito.mock(AtomNode::class.java)
        Mockito.`when`(
            nodeGenerator.generateAtomNode(firstNumeric * secondNumeric)
        ).thenReturn(resultingNode)

        val actual = timesFunction.evaluate(
            params
        )

        Assertions.assertEquals(resultingNode, actual)
    }
}