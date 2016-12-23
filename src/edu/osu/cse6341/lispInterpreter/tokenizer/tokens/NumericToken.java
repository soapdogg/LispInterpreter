package edu.osu.cse6341.lispInterpreter.tokenizer.tokens;

import edu.osu.cse6341.lispInterpreter.IInterpreter;


public class NumericToken implements IToken{

	private int atomValue;

	public NumericToken(){}
	
	public NumericToken(int atomValue){
		this.atomValue = atomValue;
	}
}
