package edu.osu.cse6341.lispInterpreter.tokenizer.tokens;

import edu.osu.cse6341.lispInterpreter.Interpreter;

public class OpenToken implements IToken{

    @Override
    public void process(Interpreter interpreter) {
        interpreter.incrementOpenCount();
    }

    public TokenKind getTokenKind(){
		return TokenKind.OPEN_TOKEN;
	}

	@Override
	public int hashCode(){
		return (int) getTokenKind().ordinal();
	}

	@Override
	public String toString(){
		return "(";
	}
}
