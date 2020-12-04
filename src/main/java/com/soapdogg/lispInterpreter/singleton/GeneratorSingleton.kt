package com.soapdogg.lispInterpreter.singleton

import com.soapdogg.lispInterpreter.functions.DefunFunction
import com.soapdogg.lispInterpreter.generator.*

enum class GeneratorSingleton {
    INSTANCE;

    val nodeGenerator: NodeGenerator = NodeGenerator()
    val defunFunction: DefunFunction
    val tokenGenerator: TokenGenerator

    init {
        defunFunction = DefunFunction(
            AsserterSingleton.INSTANCE.functionLengthAsserter,
            AsserterSingleton.INSTANCE.userDefinedFunctionNameAsserter,
            AsserterSingleton.INSTANCE.userDefinedFormalParametersAsserter
        )

        tokenGenerator = TokenGenerator()
    }
}