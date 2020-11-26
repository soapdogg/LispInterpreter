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
        numericStringDeterminer = new NumericStringDeterminer();
        expressionNodeDeterminer = new ExpressionNodeDeterminer();
        functionLengthDeterminer = new FunctionLengthDeterminer();

        invalidNameDeterminer = new InvalidNameDeterminer(
            InvalidUserDefinedNameConstants.InvalidNames,
            numericStringDeterminer
        );
        userDefinedFunctionNameDeterminer = new UserDefinedFunctionNameDeterminer();
        literalTokenValueEndIndexDeterminer = new LiteralTokenValueEndIndexDeterminer();
        numericTokenValueEndIndexDeterminer = new NumericTokenValueEndIndexDeterminer();
    }
}
