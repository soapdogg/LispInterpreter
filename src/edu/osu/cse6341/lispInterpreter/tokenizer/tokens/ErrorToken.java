package edu.osu.cse6341.lispInterpreter.tokenizer.tokens;


public class ErrorToken implements IToken{

	private final String atomValue;

	public ErrorToken(String atomValue){
		this.atomValue = atomValue;
	}

    public TokenKind getTokenKind(){
		return TokenKind.ERROR_TOKEN;
	}

	@Override
	public String toString(){
		return atomValue;
	}
}
