package edu.osu.cse6341.lispInterpreter.tokenizer.tokens;

import edu.osu.cse6341.lispInterpreter.IInterpreter;

import edu.osu.cse6341.lispInterpreter.tokenizer.tokens.IToken;

public class ErrorToken implements IToken{

	private String atomValue;

	public ErrorToken(String atomValue){
		this.atomValue = atomValue;
	}

}
