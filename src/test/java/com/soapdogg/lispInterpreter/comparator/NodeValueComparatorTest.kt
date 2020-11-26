package com.soapdogg.lispInterpreter.comparator

import com.soapdogg.lispInterpreter.constants.ReservedValuesConstants
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class NodeValueComparatorTest {
    private var nodeValueComparator: NodeValueComparator = NodeValueComparator()

    @Test
    fun equalsNilFalseTest() {
        val actual = nodeValueComparator.equalsNil(ReservedValuesConstants.T)
        Assertions.assertFalse(actual)
    }

    @Test
    fun equalsNilTrueTest() {
        val actual = nodeValueComparator.equalsNil(ReservedValuesConstants.NIL)
        Assertions.assertTrue(actual)
    }

    @Test
    fun equalsTFalseTest() {
        val actual = nodeValueComparator.equalsT(ReservedValuesConstants.NIL)
        Assertions.assertFalse(actual)
    }

    @Test
    fun equalTTrueTest() {
        val actual = nodeValueComparator.equalsT(ReservedValuesConstants.T)
        Assertions.assertTrue(actual)
    }
}