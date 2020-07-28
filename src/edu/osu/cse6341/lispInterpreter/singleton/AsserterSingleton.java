package edu.osu.cse6341.lispInterpreter.singleton;

import edu.osu.cse6341.lispInterpreter.program.asserter.FunctionLengthAsserter;
import edu.osu.cse6341.lispInterpreter.program.asserter.TokenKindAsserter;

public enum AsserterSingleton {
    INSTANCE;

    private final FunctionLengthAsserter functionLengthAsserter;
    private final TokenKindAsserter tokenKindAsserter;

    AsserterSingleton() {
        functionLengthAsserter = FunctionLengthAsserter.newInstance();
        tokenKindAsserter = TokenKindAsserter.newInstance();
    }

    public FunctionLengthAsserter getFunctionLengthAsserter() {
        return functionLengthAsserter;
    }

    public TokenKindAsserter getTokenKindAsserter() {
        return tokenKindAsserter;
    }
}
