package edu.osu.cse6341.lispInterpreter.tokenizer.tokens;

public class NumericToken implements IToken{

	private int atomValue;

	public NumericToken(){}
	
	public NumericToken(int atomValue){
		this.atomValue = atomValue;
	}
	
	public TokenKind getTokenKind(){
		return TokenKind.NUMERIC_TOKEN;
	}
	
	@Override
	public int hashCode(){
		return (int) getTokenKind().ordinal();
	}
	
	@Override
	public String toString(){
		return String.valueOf(atomValue);
	}
}
