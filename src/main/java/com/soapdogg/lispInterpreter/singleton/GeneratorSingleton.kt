package com.soapdogg.lispInterpreter.singleton

import com.soapdogg.lispInterpreter.generator.UserDefinedFunctionGenerator
import com.soapdogg.lispInterpreter.generator.*

enum class GeneratorSingleton {
    INSTANCE;

    val nodeGenerator: NodeGenerator = NodeGenerator()
    val programStackItemGenerator: ProgramStackItemGenerator = ProgramStackItemGenerator()
    val userDefinedFunctionGenerator: UserDefinedFunctionGenerator = UserDefinedFunctionGenerator(
        AsserterSingleton.INSTANCE.functionLengthAsserter,
        AsserterSingleton.INSTANCE.userDefinedFunctionNameAsserter,
        AsserterSingleton.INSTANCE.userDefinedFormalParametersAsserter
    )
    val tokenGenerator: TokenGenerator = TokenGenerator()

}