package edu.osu.cse6341.lispInterpreter.tokenizer.tokens;

import edu.osu.cse6341.lispInterpreter.Interpreter;

public class CloseToken implements IToken{

    @Override
    public void process(Interpreter interpreter) {
        interpreter.incrementClosingCount();
    }

    public TokenKind getTokenKind(){
		return TokenKind.CLOSE_TOKEN;
	}

	@Override
	public String toString(){
		return ")";
	}
}
