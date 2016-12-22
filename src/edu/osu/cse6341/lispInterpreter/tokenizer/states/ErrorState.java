package edu.osu.cse6341.lispInterpreter.tokenizer.states;

import edu.osu.cse6341.lispInterpreter.tokenizer.ITokenizer;
import edu.osu.cse6341.lispInterpreter.tokenizer.Tokenizer;
import edu.osu.cse6341.lispInterpreter.tokenizer.tokens.ErrorToken;


public class ErrorState implements IState{

	@Override
	public boolean processState(String line, int pos, int startingPos){
		Tokenizer tokenizer = Tokenizer.getTokenizer();
		tokenizer.addToTokens(new ErrorToken("SHIT THE BED"));
		return false;
	}
}
