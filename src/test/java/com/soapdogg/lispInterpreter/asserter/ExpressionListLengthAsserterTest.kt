package com.soapdogg.lispInterpreter.asserter

import com.soapdogg.lispInterpreter.constants.FunctionLengthConstants
import com.soapdogg.lispInterpreter.constants.FunctionNameConstants
import com.soapdogg.lispInterpreter.datamodels.AtomNode
import com.soapdogg.lispInterpreter.datamodels.ExpressionListNode
import com.soapdogg.lispInterpreter.datamodels.NodeV2
import com.soapdogg.lispInterpreter.datamodels.UserDefinedFunction
import com.soapdogg.lispInterpreter.exceptions.NotAListException
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class ExpressionListLengthAsserterTest {

    private val functionLengthAsserter = Mockito.mock(FunctionLengthAsserter::class.java)

    private val functionName = FunctionNameConstants.LESS
    private val functionLength = FunctionLengthConstants.THREE
    private val functionLengthMap = mapOf(
        Pair(functionName, functionLength)
    )

    private val expressionListLengthAsserter = ExpressionListLengthAsserter(
        functionLengthAsserter,
        functionLengthMap
    )

    @Test
    fun assertFunctionLengthTest() {
        val node = Mockito.mock(ExpressionListNode::class.java)
        val nodes = listOf(node)

        val address = Mockito.mock(AtomNode::class.java)
        Mockito.`when`(node.children).thenReturn(listOf(address))

        val addressValue = functionName
        Mockito.`when`(address.value).thenReturn(addressValue)

        val userDefinedFunctions = mapOf<String, UserDefinedFunction>()

        expressionListLengthAsserter.assertLengthIsAsExpected(
            nodes,
            userDefinedFunctions
        )

        Mockito.verify(functionLengthAsserter).assertLengthIsAsExpected(
            addressValue,
            functionLength,
            node
        )
    }

    @Test
    fun assertUserDefinedFunctionLengthTest() {
        val node = Mockito.mock(ExpressionListNode::class.java)
        val nodes = listOf(node)

        val address = Mockito.mock(AtomNode::class.java)
        Mockito.`when`(node.children).thenReturn(listOf(address))

        val userDefinedFunctionName = "userDefinedFunctionName"
        Mockito.`when`(address.value).thenReturn(userDefinedFunctionName)

        val userDefinedFunction = Mockito.mock(UserDefinedFunction::class.java)
        val formalParameters = listOf<String>()
        Mockito.`when`(userDefinedFunction.formalParameters).thenReturn(formalParameters)

        val userDefinedFunctions = mapOf<String, UserDefinedFunction>(
            Pair(userDefinedFunctionName, userDefinedFunction)
        )

        expressionListLengthAsserter.assertLengthIsAsExpected(
            nodes,
            userDefinedFunctions
        )

        Mockito.verify(functionLengthAsserter).assertLengthIsAsExpected(
            userDefinedFunctionName,
            formalParameters.size + 1,
            node
        )
    }

    @Test
    fun assertCondFunctionChildrenLengthTest() {
        val node = Mockito.mock(ExpressionListNode::class.java)
        val nodes = listOf(node)

        val address = Mockito.mock(AtomNode::class.java)
        Mockito.`when`(address.value).thenReturn(FunctionNameConstants.COND)

        val condChild = Mockito.mock(ExpressionListNode::class.java)
        val condGrandChild = Mockito.mock(NodeV2::class.java)
        Mockito.`when`(condChild.children).thenReturn(listOf(condGrandChild))
        val nilChild = Mockito.mock(AtomNode::class.java)

        Mockito.`when`(
            node.children
        ).thenReturn(
            listOf(
                address,
                condChild,
                nilChild
            )
        )

        val userDefinedFunctions = mapOf<String, UserDefinedFunction>()

        expressionListLengthAsserter.assertLengthIsAsExpected(
            nodes,
            userDefinedFunctions
        )

        Mockito.verify(functionLengthAsserter).assertLengthIsAsExpected(
            FunctionNameConstants.COND,
            FunctionLengthConstants.TWO,
            condChild
        )
    }

    @Test
    fun assertCondFunctionChildNotAListTest() {
        val node = Mockito.mock(ExpressionListNode::class.java)
        val nodes = listOf(node)

        val address = Mockito.mock(AtomNode::class.java)
        Mockito.`when`(address.value).thenReturn(FunctionNameConstants.COND)

        val condChild = Mockito.mock(AtomNode::class.java)
        val nilChild = Mockito.mock(AtomNode::class.java)

        Mockito.`when`(
            node.children
        ).thenReturn(
            listOf(
                address,
                condChild,
                nilChild
            )
        )

        val userDefinedFunctions = mapOf<String, UserDefinedFunction>()

        Assertions.assertThrows(
            NotAListException::class.java
        ) {
            expressionListLengthAsserter.assertLengthIsAsExpected(
                nodes,
                userDefinedFunctions
            )
        }
    }
}