package edu.osu.cse6341.lispInterpreter.tokenizer.tokens;

import edu.osu.cse6341.lispInterpreter.IInterpreter;

public class LiteralToken implements IToken{

	private String atomValue;

	public LiteralToken(){}

	public LiteralToken(String atomValue){
		this.atomValue = atomValue;
	}
	
	public TokenKind getTokenKind(){
		return TokenKind.LITERAL_TOKEN;
	}
	
	@Override
	public int hashCode(){
		return (int) getTokenKind().ordinal();
	}
	
	@Override
	public String toString(){
		return atomValue;
	}
}
