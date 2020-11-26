package com.soapdogg.lispInterpreter.determiner

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class InvalidNameDeterminerTest {
    private val invalidName = "invalidName"
    private val invalidNames = setOf(invalidName)
    private val numericStringDeterminer = Mockito.mock(NumericStringDeterminer::class.java)
    private val invalidNameDeterminer = InvalidNameDeterminer(
        invalidNames,
        numericStringDeterminer
    )

    @Test
    fun invalidNamesContainsValueTest() {
        val value = invalidName
        val actual = invalidNameDeterminer.isInvalidName(value)
        Assertions.assertTrue(actual)
        Mockito.verifyNoInteractions(numericStringDeterminer)
    }

    @Test
    fun valueIsNumericTest() {
        val value = "0"
        Mockito.`when`(numericStringDeterminer.isStringNumeric(value)).thenReturn(true)
        val actual = invalidNameDeterminer.isInvalidName(value)
        Assertions.assertTrue(actual)
    }

    @Test
    fun valueIsValidTest() {
        val value = "valid"
        Mockito.`when`(numericStringDeterminer.isStringNumeric(value)).thenReturn(false)
        val actual = invalidNameDeterminer.isInvalidName(value)
        Assertions.assertFalse(actual)
    }
}