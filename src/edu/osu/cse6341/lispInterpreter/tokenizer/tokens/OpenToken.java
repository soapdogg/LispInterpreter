package edu.osu.cse6341.lispInterpreter.tokenizer.tokens;

import edu.osu.cse6341.lispInterpreter.IInterpreter;


public class OpenToken implements IToken{

	public void process(IInterpreter interpreter){
		interpreter.incrementOpenCount();
	}

}
