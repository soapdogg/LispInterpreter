package com.soapdogg.lispInterpreter.comparator

import com.soapdogg.lispInterpreter.constants.ReservedValuesConstants

class NodeValueComparator {
    fun equalsNil(value: String): Boolean {
        return ReservedValuesConstants.NIL == value
    }

    fun equalsT(value: String): Boolean {
        return ReservedValuesConstants.T == value
    }
}