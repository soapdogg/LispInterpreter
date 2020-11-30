package com.soapdogg.lispInterpreter.evaluator

import com.soapdogg.lispInterpreter.comparator.NodeValueComparator
import com.soapdogg.lispInterpreter.constants.FunctionNameConstants
import com.soapdogg.lispInterpreter.datamodels.AtomNode
import com.soapdogg.lispInterpreter.datamodels.ExpressionNode
import com.soapdogg.lispInterpreter.datamodels.Node
import com.soapdogg.lispInterpreter.datamodels.UserDefinedFunction
import com.soapdogg.lispInterpreter.exceptions.NotAListException
import com.soapdogg.lispInterpreter.valueretriver.ListValueRetriever
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class CondFunctionEvaluatorTest {
    private val userDefinedFunctions: List<UserDefinedFunction> = emptyList()
    private val variableNameToValueMap: Map<String, Node> = emptyMap()
    private val listValueRetriever = Mockito.mock(ListValueRetriever::class.java)
    private val nodeEvaluator = Mockito.mock(NodeEvaluator::class.java)
    private val nodeValueComparator = Mockito.mock(NodeValueComparator::class.java)
    private val condFunctionEvaluator = CondFunctionEvaluator(
        listValueRetriever,
        nodeEvaluator,
        nodeValueComparator
    )

    @Test
    fun inputIsAtomNodeTest() {
        val params: Node = Mockito.mock(AtomNode::class.java)
        Assertions.assertThrows(
            NotAListException::class.java
        ) {
            condFunctionEvaluator.evaluateCondFunction(
                params,
                userDefinedFunctions,
                variableNameToValueMap
            )
        }
        Mockito.verifyNoInteractions(listValueRetriever)
        Mockito.verifyNoInteractions(nodeEvaluator)
        Mockito.verifyNoInteractions(nodeValueComparator)
    }

    @Test
    fun inputIsListAndBooleanResultIsNotNilTest() {
        val params: Node = Mockito.mock(ExpressionNode::class.java)
        val address = Mockito.mock(Node::class.java)
        Mockito.`when`((params as ExpressionNode).address).thenReturn(address)
        val expressionNodeAddress = Mockito.mock(ExpressionNode::class.java)
        Mockito.`when`(
            listValueRetriever.retrieveListValue(
                address,
                FunctionNameConstants.COND,
                variableNameToValueMap
            )
        ).thenReturn(expressionNodeAddress)
        val expressionNodeAddressAddress = Mockito.mock(Node::class.java)
        Mockito.`when`(expressionNodeAddress.address).thenReturn(expressionNodeAddressAddress)
        val booleanResult: Node = Mockito.mock(AtomNode::class.java)
        Mockito.`when`(
            nodeEvaluator.evaluate(
                expressionNodeAddressAddress,
                userDefinedFunctions,
                variableNameToValueMap,
                true
            )
        ).thenReturn(booleanResult)
        Mockito.`when`(
            nodeValueComparator.equalsNil(
                (booleanResult as AtomNode).value
            )
        ).thenReturn(false)
        val expressionNodeAddressData = Mockito.mock(Node::class.java)
        Mockito.`when`(expressionNodeAddress.data).thenReturn(expressionNodeAddressData)
        val expected = Mockito.mock(Node::class.java)
        Mockito.`when`(
            nodeEvaluator.evaluate(
                expressionNodeAddressData,
                userDefinedFunctions,
                variableNameToValueMap,
                true
            )
        ).thenReturn(expected)
        val actual = condFunctionEvaluator.evaluateCondFunction(
            params,
            userDefinedFunctions,
            variableNameToValueMap
        )
        Assertions.assertEquals(expected, actual)
    }

    @Test
    fun inputIsListAndBooleanResultIsNotAtomNodeTest() {
        val params: Node = Mockito.mock(ExpressionNode::class.java)
        val address = Mockito.mock(Node::class.java)
        Mockito.`when`((params as ExpressionNode).address).thenReturn(address)
        val expressionNodeAddress = Mockito.mock(ExpressionNode::class.java)
        Mockito.`when`(
            listValueRetriever.retrieveListValue(
                address,
                FunctionNameConstants.COND,
                variableNameToValueMap
            )
        ).thenReturn(expressionNodeAddress)
        val expressionNodeAddressAddress = Mockito.mock(Node::class.java)
        Mockito.`when`(expressionNodeAddress.address).thenReturn(expressionNodeAddressAddress)
        val booleanResult: Node = Mockito.mock(ExpressionNode::class.java)
        Mockito.`when`(
            nodeEvaluator.evaluate(
                expressionNodeAddressAddress,
                userDefinedFunctions,
                variableNameToValueMap,
                true
            )
        ).thenReturn(booleanResult)
        val data: Node = Mockito.mock(AtomNode::class.java)
        Mockito.`when`(params.data).thenReturn(data)
        Assertions.assertThrows(
            NotAListException::class.java
        ) {
            condFunctionEvaluator.evaluateCondFunction(
                params,
                userDefinedFunctions,
                variableNameToValueMap
            )
        }
    }

    @Test
    fun inputIsListAndBooleanResultIsNilTest() {
        val params: Node = Mockito.mock(ExpressionNode::class.java)
        val address = Mockito.mock(Node::class.java)
        Mockito.`when`((params as ExpressionNode).address).thenReturn(address)
        val expressionNodeAddress = Mockito.mock(ExpressionNode::class.java)
        Mockito.`when`(
            listValueRetriever.retrieveListValue(
                address,
                FunctionNameConstants.COND,
                variableNameToValueMap
            )
        ).thenReturn(expressionNodeAddress)
        val expressionNodeAddressAddress = Mockito.mock(Node::class.java)
        Mockito.`when`(expressionNodeAddress.address).thenReturn(expressionNodeAddressAddress)
        val booleanResult: Node = Mockito.mock(AtomNode::class.java)
        Mockito.`when`(
            nodeEvaluator.evaluate(
                expressionNodeAddressAddress,
                userDefinedFunctions,
                variableNameToValueMap,
                true
            )
        ).thenReturn(booleanResult)
        Mockito.`when`(
            nodeValueComparator.equalsNil(
                (booleanResult as AtomNode).value
            )
        ).thenReturn(true)
        val data: Node = Mockito.mock(AtomNode::class.java)
        Mockito.`when`(params.data).thenReturn(data)
        Assertions.assertThrows(
            NotAListException::class.java
        ) {
            condFunctionEvaluator.evaluateCondFunction(
                params,
                userDefinedFunctions,
                variableNameToValueMap
            )
        }
    }
}