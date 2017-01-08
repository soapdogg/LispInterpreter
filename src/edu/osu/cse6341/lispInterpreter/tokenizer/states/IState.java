package edu.osu.cse6341.lispInterpreter.tokenizer.states;

import edu.osu.cse6341.lispInterpreter.tokenizer.Tokenizer;

public interface IState{

	boolean processState(Tokenizer tokenizer, String line, int pos, int startingPos);
}
