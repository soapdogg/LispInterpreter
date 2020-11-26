package com.soapdogg.lispInterpreter.determiner

import com.soapdogg.lispInterpreter.datamodels.UserDefinedFunction

class UserDefinedFunctionNameDeterminer {
    fun determineIfUserDefinedFunctionName(
        userDefinedFunctions: List<UserDefinedFunction>,
        functionName: String
    ): Boolean {
        return userDefinedFunctions.stream().anyMatch {
             it.functionName == functionName
        }
    }
}