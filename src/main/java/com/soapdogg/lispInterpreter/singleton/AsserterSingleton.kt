package com.soapdogg.lispInterpreter.singleton

import com.soapdogg.lispInterpreter.asserter.*
import com.soapdogg.lispInterpreter.constants.InvalidUserDefinedNameConstants
import com.soapdogg.lispInterpreter.constants.TokenValueConstants

enum class AsserterSingleton {
    INSTANCE;

    val functionLengthAsserter: FunctionLengthAsserter = FunctionLengthAsserter(
        DeterminerSingleton.INSTANCE.functionLengthDeterminer,
        ConverterSingleton.INSTANCE.nodeConverter
    )
    val condFunctionParameterAsserter: CondFunctionParameterAsserter
    val userDefinedFormalParametersAsserter: UserDefinedFormalParametersAsserter
    val userDefinedFunctionNameAsserter: UserDefinedFunctionNameAsserter
    val atomRootNodeAsserter: AtomRootNodeAsserter
    val lineFormatAsserter: LineFormatAsserter

    init {
        condFunctionParameterAsserter = CondFunctionParameterAsserter(
            ComparatorSingleton.INSTANCE.nodeValueComparator,
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