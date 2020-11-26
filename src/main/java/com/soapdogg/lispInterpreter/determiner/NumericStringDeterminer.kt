package com.soapdogg.lispInterpreter.determiner

class NumericStringDeterminer {
    fun isStringNumeric(value: String): Boolean {
        return value.matches(Regex("-?[1-9][0-9]*|0"))
    }
}