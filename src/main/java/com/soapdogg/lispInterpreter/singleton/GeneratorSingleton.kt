package com.soapdogg.lispInterpreter.singleton

import com.soapdogg.lispInterpreter.generator.NodeGenerator
import com.soapdogg.lispInterpreter.generator.UserDefinedFunctionGenerator
import com.soapdogg.lispInterpreter.generator.UserDefinedFunctionFormalParameterGenerator
import com.soapdogg.lispInterpreter.generator.TokenGenerator
import com.soapdogg.lispInterpreter.functions.DefunFunction

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