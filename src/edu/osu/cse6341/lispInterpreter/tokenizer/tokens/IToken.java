package edu.osu.cse6341.lispInterpreter.tokenizer.tokens;

import edu.osu.cse6341.lispInterpreter.Interpreter;

public interface IToken{
    void process(Interpreter interpreter) throws Exception;

	TokenKind getTokenKind();
}
