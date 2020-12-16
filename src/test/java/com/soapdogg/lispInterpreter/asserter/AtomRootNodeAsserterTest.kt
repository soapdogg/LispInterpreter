package com.soapdogg.lispInterpreter.asserter

import com.soapdogg.lispInterpreter.constants.ReservedValuesConstants
import com.soapdogg.lispInterpreter.datamodels.AtomNode
import com.soapdogg.lispInterpreter.determiner.NumericStringDeterminer
import com.soapdogg.lispInterpreter.exceptions.NotAtomicException
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class AtomRootNodeAsserterTest {
    private val atomNode: AtomNode = Mockito.mock(AtomNode::class.java)
    private val numericStringDeterminer: NumericStringDeterminer = Mockito.mock(NumericStringDeterminer::class.java)
    private val atomRootNodeAsserter: AtomRootNodeAsserter = AtomRootNodeAsserter(
        numericStringDeterminer
    )


    @Test
    fun valueIsNumericTest() {
        val value = "34"
        Mockito.`when`(atomNode.value).thenReturn(value)
        Mockito.`when`(numericStringDeterminer.isStringNumeric(value)).thenReturn(true)
        Assertions.assertDoesNotThrow { atomRootNodeAsserter.assertAtomRootNode(atomNode) }
    }

    @Test
    fun valueIsTTest() {
        val value = ReservedValuesConstants.T
        Mockito.`when`(atomNode.value).thenReturn(value)
        Mockito.`when`(numericStringDeterminer.isStringNumeric(value)).thenReturn(false)
        Assertions.assertDoesNotThrow { atomRootNodeAsserter.assertAtomRootNode(atomNode) }
    }

    @Test
    fun valueIsNilTest() {
        val value = ReservedValuesConstants.NIL
        Mockito.`when`(atomNode.value).thenReturn(value)
        Mockito.`when`(numericStringDeterminer.isStringNumeric(value)).thenReturn(false)
        Assertions.assertDoesNotThrow { atomRootNodeAsserter.assertAtomRootNode(atomNode) }
    }

    @Test
    fun invalidValueTest() {
        val value = "value"
        Mockito.`when`(atomNode.value).thenReturn(value)
        Mockito.`when`(numericStringDeterminer.isStringNumeric(value)).thenReturn(false)
        Assertions.assertThrows(
            NotAtomicException::class.java
        ) { atomRootNodeAsserter.assertAtomRootNode(atomNode) }
    }
}