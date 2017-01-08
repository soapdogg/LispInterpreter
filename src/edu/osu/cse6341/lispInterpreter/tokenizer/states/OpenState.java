package edu.osu.cse6341.lispInterpreter.tokenizer.states;

import edu.osu.cse6341.lispInterpreter.tokenizer.Tokenizer;
import edu.osu.cse6341.lispInterpreter.tokenizer.tokens.OpenToken;


public class OpenState implements IState{

	@Override
	public boolean processState(Tokenizer tokenizer, String line, int pos, int startingPos){
		tokenizer.addToTokens(new OpenToken());
		IState nextState = new StartingState();
		return nextState.processState(tokenizer, line, ++pos, ++startingPos);
	}

}
