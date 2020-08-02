package edu.osu.cse6341.lispInterpreter.tokenizer.tokens;

public class LiteralToken implements IToken{

	private final String atomValue;

	public LiteralToken(String atomValue){
		this.atomValue = atomValue;
	}

    public TokenKind getTokenKind(){
		return TokenKind.LITERAL_TOKEN;
	}
	
	@Override
	public String toString(){
		return atomValue;
	}
}
