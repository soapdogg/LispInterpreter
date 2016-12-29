package edu.osu.cse6341.lispInterpreter.tokenizer.tokens;

public class OpenToken implements IToken{

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
