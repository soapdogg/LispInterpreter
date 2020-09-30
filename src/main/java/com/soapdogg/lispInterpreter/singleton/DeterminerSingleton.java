package com.soapdogg.lispInterpreter.singleton;

import com.soapdogg.lispInterpreter.constants.InvalidUserDefinedNameConstants;
import com.soapdogg.lispInterpreter.determiner.*;
import lombok.Getter;

@Getter
public enum DeterminerSingleton {
    INSTANCE;
    private final NumericStringDeterminer numericStringDeterminer;
    private final ExpressionNodeDeterminer expressionNodeDeterminer;
    private final FunctionLengthDeterminer functionLengthDeterminer;
    private final InvalidNameDeterminer invalidNameDeterminer;
    private final UserDefinedFunctionNameDeterminer userDefinedFunctionNameDeterminer;
    private final LiteralTokenValueEndIndexDeterminer literalTokenValueEndIndexDeterminer;
    private final NumericTokenValueEndIndexDeterminer numericTokenValueEndIndexDeterminer;

    DeterminerSingleton() {
        numericStringDeterminer = NumericStringDeterminer.newInstance();
        expressionNodeDeterminer = ExpressionNodeDeterminer.newInstance();
        functionLengthDeterminer = FunctionLengthDeterminer.newInstance();

        invalidNameDeterminer = InvalidNameDeterminer.newInstance(
            InvalidUserDefinedNameConstants.InvalidNames,
            numericStringDeterminer
        );
        userDefinedFunctionNameDeterminer = UserDefinedFunctionNameDeterminer.newInstance();
        literalTokenValueEndIndexDeterminer = LiteralTokenValueEndIndexDeterminer.newInstance();
        numericTokenValueEndIndexDeterminer = NumericTokenValueEndIndexDeterminer.newInstance();
    }
}
