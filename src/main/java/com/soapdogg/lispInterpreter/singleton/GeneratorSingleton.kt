package com.soapdogg.lispInterpreter.singleton

import com.soapdogg.lispInterpreter.generator.UserDefinedFunctionGenerator
import com.soapdogg.lispInterpreter.generator.*

enum class GeneratorSingleton {
    INSTANCE;

    val nodeGenerator: NodeGenerator = NodeGenerator()
    val userDefinedFunctionGenerator: UserDefinedFunctionGenerator
    val tokenGenerator: TokenGenerator

    init {
        userDefinedFunctionGenerator = UserDefinedFunctionGenerator(
            AsserterSingleton.INSTANCE.functionLengthAsserter,
            AsserterSingleton.INSTANCE.userDefinedFunctionNameAsserter,
            AsserterSingleton.INSTANCE.userDefinedFormalParametersAsserter
        )

        tokenGenerator = TokenGenerator()
    }
}