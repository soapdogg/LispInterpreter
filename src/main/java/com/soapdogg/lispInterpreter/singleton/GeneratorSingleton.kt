package com.soapdogg.lispInterpreter.singleton

import com.soapdogg.lispInterpreter.functions.DefunFunction
import com.soapdogg.lispInterpreter.generator.*

enum class GeneratorSingleton {
    INSTANCE;

    val nodeGenerator: NodeGenerator = NodeGenerator()
    val userDefinedFunctionFormalParameterGenerator: UserDefinedFunctionFormalParameterGenerator = UserDefinedFunctionFormalParameterGenerator(
        ValueRetrieverSingleton.INSTANCE.listValueRetriever,
        ValueRetrieverSingleton.INSTANCE.atomicValueRetriever
    )
    val defunFunction: DefunFunction
    val tokenGenerator: TokenGenerator

    init {
        defunFunction = DefunFunction(
            AsserterSingleton.INSTANCE.functionLengthAsserter,
            AsserterSingleton.INSTANCE.userDefinedFunctionNameAsserter,
            userDefinedFunctionFormalParameterGenerator,
            AsserterSingleton.INSTANCE.userDefinedFormalParametersAsserter,
            ConverterSingleton.INSTANCE.nodeConverter
        )

        tokenGenerator = TokenGenerator()
    }
}