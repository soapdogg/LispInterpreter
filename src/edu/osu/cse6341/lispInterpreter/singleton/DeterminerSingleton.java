package edu.osu.cse6341.lispInterpreter.singleton;

import edu.osu.cse6341.lispInterpreter.determiner.ExpressionNodeDeterminer;
import edu.osu.cse6341.lispInterpreter.determiner.NumericStringDeterminer;
import lombok.Getter;

@Getter
public enum DeterminerSingleton {
    INSTANCE;
    private final NumericStringDeterminer numericStringDeterminer;
    private final ExpressionNodeDeterminer expressionNodeDeterminer;

    DeterminerSingleton() {
        numericStringDeterminer = NumericStringDeterminer.newInstance();
        expressionNodeDeterminer = ExpressionNodeDeterminer.newInstance();
    }
}