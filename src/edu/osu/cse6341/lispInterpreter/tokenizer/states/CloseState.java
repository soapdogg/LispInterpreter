package edu.osu.cse6341.lispInterpreter.tokenizer.states;

import edu.osu.cse6341.lispInterpreter.tokenizer.ITokenizer;
import edu.osu.cse6341.lispInterpreter.tokenizer.Tokenizer;
import edu.osu.cse6341.lispInterpreter.tokenizer.tokens.CloseToken;

public class CloseState implements IState{

	@Override
	public boolean processState(String line, int pos, int startingPos){
		ITokenizer tokenizer = Tokenizer.getTokenizer();
		tokenizer.addToTokens(new CloseToken());
		IState nextState = new StartingState();
		return nextState.processState(line, ++pos, ++startingPos);
	}

}
