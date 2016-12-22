package edu.osu.cse6341.lispInterpreter.tokenizer.states;


public class ErrorState implements IState{

	@Override
	public boolean processState(String line, int pos, int startingPos){
		return false;
	}
}
