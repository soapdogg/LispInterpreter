package com.soapdogg.lispInterpreter.singleton

import com.soapdogg.lispInterpreter.constants.InvalidUserDefinedNameConstants
import com.soapdogg.lispInterpreter.determiner.*

enum class DeterminerSingleton {
    INSTANCE;

    val numericStringDeterminer: NumericStringDeterminer = NumericStringDeterminer()
    val functionLengthDeterminer: FunctionLengthDeterminer = FunctionLengthDeterminer()
    val invalidNameDeterminer: InvalidNameDeterminer
    val userDefinedFunctionNameDeterminer: UserDefinedFunctionNameDeterminer
    val literalTokenValueEndIndexDeterminer: LiteralTokenValueEndIndexDeterminer
    val numericTokenValueEndIndexDeterminer: NumericTokenValueEndIndexDeterminer

    init {
        invalidNameDeterminer = InvalidNameDeterminer(
            InvalidUserDefinedNameConstants.InvalidNames,
            numericStringDeterminer
        )
        userDefinedFunctionNameDeterminer = UserDefinedFunctionNameDeterminer()
        literalTokenValueEndIndexDeterminer = LiteralTokenValueEndIndexDeterminer()
        numericTokenValueEndIndexDeterminer = NumericTokenValueEndIndexDeterminer()
    }
}