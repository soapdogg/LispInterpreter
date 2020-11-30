package com.soapdogg.lispInterpreter.asserter

import com.soapdogg.lispInterpreter.determiner.InvalidNameDeterminer
import com.soapdogg.lispInterpreter.exceptions.InvalidUserDefinedNameException

class UserDefinedFunctionNameAsserter(
    private val invalidNameDeterminer: InvalidNameDeterminer
) {
    fun assertFunctionNameIsValid(
        functionName: String
    ) {
        if (invalidNameDeterminer.isInvalidName(functionName)) throw InvalidUserDefinedNameException("Error! Invalid function name: $functionName\n")
    }
}