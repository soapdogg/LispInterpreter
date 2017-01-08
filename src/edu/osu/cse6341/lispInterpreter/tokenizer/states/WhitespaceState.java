package edu.osu.cse6341.lispInterpreter.tokenizer.states;


import edu.osu.cse6341.lispInterpreter.tokenizer.Tokenizer;

public class WhitespaceState implements IState{

	@Override
	public boolean processState(Tokenizer tokenizer, String line, int pos, int startingPos){
		IState nextState = new StartingState();
		return nextState.processState(tokenizer, line, ++pos, ++startingPos);
	}

}
