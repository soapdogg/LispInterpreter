package edu.osu.cse6341.lispInterpreter.tokenizer.states;

import edu.osu.cse6341.lispInterpreter.tokenizer.Tokenizer;
import edu.osu.cse6341.lispInterpreter.tokenizer.tokens.CloseToken;

public class CloseState implements IState{

	@Override
	public boolean processState(Tokenizer tokenizer, String line, int pos, int startingPos){
		tokenizer.addToTokens(new CloseToken());
		IState nextState = new StartingState();
		return nextState.processState(tokenizer, line, ++pos, ++startingPos);
	}

}
