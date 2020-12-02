package com.soapdogg.lispInterpreter.singleton

import com.soapdogg.lispInterpreter.functions.DefunFunction
import com.soapdogg.lispInterpreter.generator.*

enum class GeneratorSingleton {
    INSTANCE;

    val nodeGenerator: NodeGenerator = NodeGenerator()
    val userDefinedFunctionGenerator: UserDefinedFunctionGenerator
    val userDefinedFunctionFormalParameterGenerator: UserDefinedFunctionFormalParameterGenerator = UserDefinedFunctionFormalParameterGenerator(
        ValueRetrieverSingleton.INSTANCE.listValueRetriever,
        ValueRetrieverSingleton.INSTANCE.atomicValueRetriever
    )
    val tokenGenerator: TokenGenerator

    init {
        val defunFunction = DefunFunction(
            AsserterSingleton.INSTANCE.functionLengthAsserter,
            ValueRetrieverSingleton.INSTANCE.atomicValueRetriever,
            ValueRetrieverSingleton.INSTANCE.listValueRetriever,
            AsserterSingleton.INSTANCE.userDefinedFunctionNameAsserter,
            userDefinedFunctionFormalParameterGenerator,
            AsserterSingleton.INSTANCE.userDefinedFormalParametersAsserter
        )
        userDefinedFunctionGenerator = UserDefinedFunctionGenerator(
            defunFunction
        )
        tokenGenerator = TokenGenerator()
    }
}