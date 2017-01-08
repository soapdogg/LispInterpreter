package edu.osu.cse6341.lispInterpreter.tokenizer.tokens;

import edu.osu.cse6341.lispInterpreter.Interpreter;

public class NumericToken implements IToken{

	private int atomValue;

	public NumericToken(){}
	
	public NumericToken(int atomValue){
		this.atomValue = atomValue;
	}

    @Override
    public void process(Interpreter interpreter) {
        interpreter.incrementNumericAtomCount();
        interpreter.addToNumericAtomSum(atomValue);
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
