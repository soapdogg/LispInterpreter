package edu.osu.cse6341.lispInterpreter.tokenizer.tokens;

import edu.osu.cse6341.lispInterpreter.Interpreter;

public class EndOfFileToken implements IToken{

    @Override
    public void process(Interpreter interpreter) {

    }

    public TokenKind getTokenKind(){
		return TokenKind.EOF_TOKEN;
	}
	
	@Override
	public int hashCode(){
		return (int) getTokenKind().ordinal();
	}

	@Override
	public String toString(){
		return "EOF";
	}
}
