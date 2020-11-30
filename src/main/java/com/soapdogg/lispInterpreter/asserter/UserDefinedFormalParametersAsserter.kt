package com.soapdogg.lispInterpreter.asserter

import com.soapdogg.lispInterpreter.exceptions.InvalidUserDefinedNameException
import java.util.*

class UserDefinedFormalParametersAsserter(
    private val invalidNamesSet: Set<String>
) {

    fun assertFormalParameters(
        formalParameters: List<String>
    ) {
        val formalParametersSet: MutableSet<String> = HashSet(formalParameters)
        if (formalParameters.size != formalParametersSet.size) {
            throw InvalidUserDefinedNameException("Error! Duplicate formal parameter name!\n")
        }
        formalParametersSet.retainAll(invalidNamesSet)
        if (formalParametersSet.isNotEmpty()) {
            throw InvalidUserDefinedNameException("Error! Invalid formal parameter name!\n")
        }
    }
}