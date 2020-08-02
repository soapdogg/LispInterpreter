package edu.osu.cse6341.lispInterpreter.tokenizer.tokens;

public class NumericToken implements IToken{

	private final int atomValue;

	public NumericToken(int atomValue){
		this.atomValue = atomValue;
	}

    public TokenKind getTokenKind(){
		return TokenKind.NUMERIC_TOKEN;
	}
	
	@Override
	public String toString(){
		return String.valueOf(atomValue);
	}
}
