package edu.osu.cse6341.lispInterpreter.tokenizer.states;

import edu.osu.cse6341.lispInterpreter.tokens.Token;

public interface IState{

	boolean processState(String line, int startingPos);

	int getStartingPos();

	Token getToken();
}
