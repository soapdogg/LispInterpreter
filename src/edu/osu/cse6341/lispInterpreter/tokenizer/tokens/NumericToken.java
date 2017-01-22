package edu.osu.cse6341.lispInterpreter.tokenizer.tokens;

import edu.osu.cse6341.lispInterpreter.Interpreter;

public class NumericToken implements IToken{

	private final int atomValue;

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
	public String toString(){
		return String.valueOf(atomValue);
	}
}
