package com.soapdogg.lispInterpreter.singleton

import com.soapdogg.lispInterpreter.asserter.*
import com.soapdogg.lispInterpreter.constants.InvalidUserDefinedNameConstants
import com.soapdogg.lispInterpreter.constants.TokenValueConstants
import com.soapdogg.lispInterpreter.datamodels.TokenKind

enum class AsserterSingleton {
    INSTANCE;

    val functionLengthAsserter: FunctionLengthAsserter = FunctionLengthAsserter(
        DeterminerSingleton.INSTANCE.functionLengthDeterminer
    )
    val tokenKindAsserter: TokenKindAsserter
    val condFunctionParameterAsserter: CondFunctionParameterAsserter
    val userDefinedFormalParametersAsserter: UserDefinedFormalParametersAsserter
    val userDefinedFunctionNameAsserter: UserDefinedFunctionNameAsserter
    val atomRootNodeAsserter: AtomRootNodeAsserter
    val lineFormatAsserter: LineFormatAsserter

    init {
        val startingTokenKindSet = setOf(
            TokenKind.OPEN_TOKEN,
            TokenKind.NUMERIC_TOKEN,
            TokenKind.LITERAL_TOKEN
        )
        tokenKindAsserter = TokenKindAsserter(
            startingTokenKindSet
        )
        condFunctionParameterAsserter = CondFunctionParameterAsserter(
            ComparatorSingleton.INSTANCE.nodeValueComparator,
            ValueRetrieverSingleton.INSTANCE.listValueRetriever,
            functionLengthAsserter
        )
        userDefinedFormalParametersAsserter = UserDefinedFormalParametersAsserter(
            InvalidUserDefinedNameConstants.InvalidNames
        )
        userDefinedFunctionNameAsserter = UserDefinedFunctionNameAsserter(
            DeterminerSingleton.INSTANCE.invalidNameDeterminer
        )
        atomRootNodeAsserter = AtomRootNodeAsserter(
            DeterminerSingleton.INSTANCE.numericStringDeterminer,
            ComparatorSingleton.INSTANCE.nodeValueComparator
        )
        lineFormatAsserter = LineFormatAsserter(
            TokenValueConstants.ERROR_STATE_PATTERN
        )
    }
}