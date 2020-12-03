package com.soapdogg.lispInterpreter.asserter

import com.soapdogg.lispInterpreter.comparator.NodeValueComparator
import com.soapdogg.lispInterpreter.datamodels.Node
import com.soapdogg.lispInterpreter.valueretriver.ListValueRetriever
import org.mockito.Mockito

class CondFunctionParameterAsserterTest {
    private val variableNameToValueMap: Map<String, Node> = mapOf()
    private val nodeValueComparator = Mockito.mock(NodeValueComparator::class.java)
    private val listValueRetriever = Mockito.mock(ListValueRetriever::class.java)
    private val functionLengthAsserter = Mockito.mock(FunctionLengthAsserter::class.java)
    private val condFunctionParameterAsserter = CondFunctionParameterAsserter(
        nodeValueComparator,
        functionLengthAsserter
    )

   /* @Test
    fun inputIsNilTest() {
        val params: Node = Mockito.mock(AtomNode::class.java)
        Mockito.`when`((params as AtomNode).value).thenReturn(ReservedValuesConstants.NIL)
        Mockito.`when`(nodeValueComparator.equalsNil(ReservedValuesConstants.NIL)).thenReturn(true)
        Assertions.assertDoesNotThrow {
            condFunctionParameterAsserter.assertCondFunctionParameters(
                params,
                variableNameToValueMap
            )
        }
        Mockito.verifyNoInteractions(listValueRetriever)
        Mockito.verifyNoInteractions(functionLengthAsserter)
    }*/

   /* @Test
    fun inputIsNonNilAtomTest() {
        val params: Node = Mockito.mock(AtomNode::class.java)
        Mockito.`when`((params as AtomNode).value).thenReturn(ReservedValuesConstants.T)
        Mockito.`when`(nodeValueComparator.equalsNil(ReservedValuesConstants.T)).thenReturn(false)
        Assertions.assertThrows(
            NotAListException::class.java
        ) {
            condFunctionParameterAsserter.assertCondFunctionParameters(
                params,
                variableNameToValueMap
            )
        }
        Mockito.verifyNoInteractions(listValueRetriever)
        Mockito.verifyNoInteractions(functionLengthAsserter)
    }

    @Test
    fun inputIsListTest() {
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
        val expressionNodeAddressData = Mockito.mock(Node::class.java)
        Mockito.`when`(expressionNodeAddress.data).thenReturn(expressionNodeAddressData)
        val data = Mockito.mock(AtomNode::class.java)
        Mockito.`when`(params.data).thenReturn(data)
        Mockito.`when`(data.value).thenReturn(ReservedValuesConstants.NIL)
        Mockito.`when`(nodeValueComparator.equalsNil(ReservedValuesConstants.NIL)).thenReturn(true)
        Assertions.assertDoesNotThrow {
            condFunctionParameterAsserter.assertCondFunctionParameters(
                params,
                variableNameToValueMap
            )
        }
        Mockito.verify(functionLengthAsserter).assertLengthIsAsExpected(
            FunctionNameConstants.COND,
            FunctionLengthConstants.TWO,
            expressionNodeAddressData
        )
    }*/
}