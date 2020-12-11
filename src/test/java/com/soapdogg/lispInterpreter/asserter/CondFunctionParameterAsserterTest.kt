package com.soapdogg.lispInterpreter.asserter

import com.soapdogg.lispInterpreter.comparator.NodeValueComparator
import com.soapdogg.lispInterpreter.constants.FunctionLengthConstants
import com.soapdogg.lispInterpreter.constants.FunctionNameConstants
import com.soapdogg.lispInterpreter.constants.ReservedValuesConstants
import com.soapdogg.lispInterpreter.datamodels.AtomNode
import com.soapdogg.lispInterpreter.datamodels.ExpressionListNode
import com.soapdogg.lispInterpreter.exceptions.NotAListException
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class CondFunctionParameterAsserterTest {
    private val nodeValueComparator = Mockito.mock(NodeValueComparator::class.java)
    private val condFunctionParameterAsserter = CondFunctionParameterAsserter(
        nodeValueComparator
    )

    @Test
    fun inputIsNilTest() {
        val params = Mockito.mock(AtomNode::class.java)
        Mockito.`when`(params.value).thenReturn(ReservedValuesConstants.NIL)
        Mockito.`when`(nodeValueComparator.equalsNil(ReservedValuesConstants.NIL)).thenReturn(true)
        Assertions.assertDoesNotThrow {
            condFunctionParameterAsserter.assertCondFunctionParameters(
                listOf(params)
            )
        }
    }

    @Test
    fun inputIsNonNilAtomTest() {
        val params= Mockito.mock(AtomNode::class.java)
        Mockito.`when`(params.value).thenReturn(ReservedValuesConstants.T)
        Mockito.`when`(nodeValueComparator.equalsNil(ReservedValuesConstants.T)).thenReturn(false)
        Assertions.assertThrows(
            NotAListException::class.java
        ) {
            condFunctionParameterAsserter.assertCondFunctionParameters(
                listOf(params)
            )
        }
    }

    @Test
    fun inputIsListTest() {
        val params: ExpressionListNode = Mockito.mock(ExpressionListNode::class.java)

        Assertions.assertDoesNotThrow {
            condFunctionParameterAsserter.assertCondFunctionParameters(
                listOf(params)
            )
        }
    }
}