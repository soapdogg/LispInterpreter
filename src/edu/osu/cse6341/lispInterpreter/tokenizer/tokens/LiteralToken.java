package edu.osu.cse6341.lispInterpreter.tokenizer.tokens;

import edu.osu.cse6341.lispInterpreter.IInterpreter;

public class LiteralToken implements IToken{

	private String atomValue;

	public LiteralToken(String atomValue){
		this.atomValue = atomValue;
	}
}
