package edu.osu.cse6341.lispInterpreter.tokenizer.states;

import edu.osu.cse6341.lispInterpreter.tokenizer.Tokenizer;
import edu.osu.cse6341.lispInterpreter.tokenizer.tokens.OpenToken;


public class OpenState implements IState{

	@Override
	public boolean processState(String line, int pos, int startingPos){
		Tokenizer tokenizer = Tokenizer.getTokenizer();
		tokenizer.addToTokens(new OpenToken());
		IState nextState = new StartingState();
		return nextState.processState(line, ++pos, ++startingPos);
	}

}
