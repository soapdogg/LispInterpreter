package edu.osu.cse6341.lispInterpreter.tokenizer.tokens;

public class CloseToken implements IToken{

    public TokenKind getTokenKind(){
		return TokenKind.CLOSE_TOKEN;
	}

	@Override
	public String toString(){
		return ")";
	}
}
