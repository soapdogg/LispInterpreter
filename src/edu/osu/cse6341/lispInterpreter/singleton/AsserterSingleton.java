package edu.osu.cse6341.lispInterpreter.singleton;

import edu.osu.cse6341.lispInterpreter.asserter.FunctionLengthAsserter;
import edu.osu.cse6341.lispInterpreter.asserter.TokenKindAsserter;
import lombok.Getter;

@Getter
public enum AsserterSingleton {
    INSTANCE;

    private final FunctionLengthAsserter functionLengthAsserter;
    private final TokenKindAsserter tokenKindAsserter;

    AsserterSingleton() {
        functionLengthAsserter = FunctionLengthAsserter.newInstance();
        tokenKindAsserter = TokenKindAsserter.newInstance();
    }
}
