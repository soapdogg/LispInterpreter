package edu.osu.cse6341.lispInterpreter.tokenizer.states;

public interface IState{

	public boolean processState(String line, int pos, int startingPos);
}
