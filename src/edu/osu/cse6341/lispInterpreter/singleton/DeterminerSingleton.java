package edu.osu.cse6341.lispInterpreter.singleton;

import edu.osu.cse6341.lispInterpreter.constants.InvalidUserDefinedNameConstants;
import edu.osu.cse6341.lispInterpreter.determiner.ExpressionNodeDeterminer;
import edu.osu.cse6341.lispInterpreter.determiner.FunctionLengthDeterminer;
import edu.osu.cse6341.lispInterpreter.determiner.InvalidNameDeterminer;
import edu.osu.cse6341.lispInterpreter.determiner.NumericStringDeterminer;
import lombok.Getter;

@Getter
public enum DeterminerSingleton {
    INSTANCE;
    private final NumericStringDeterminer numericStringDeterminer;
    private final ExpressionNodeDeterminer expressionNodeDeterminer;
    private final FunctionLengthDeterminer functionLengthDeterminer;
    private final InvalidNameDeterminer invalidNameDeterminer;

    DeterminerSingleton() {
        numericStringDeterminer = NumericStringDeterminer.newInstance();
        expressionNodeDeterminer = ExpressionNodeDeterminer.newInstance();
        functionLengthDeterminer = FunctionLengthDeterminer.newInstance();

        invalidNameDeterminer = InvalidNameDeterminer.newInstance(
            InvalidUserDefinedNameConstants.InvalidNames,
            numericStringDeterminer
        );
    }
}
