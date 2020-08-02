package edu.osu.cse6341.lispInterpreter.tokenizer.states;

import edu.osu.cse6341.lispInterpreter.tokens.Token;

public class WhitespaceState implements IState{

    private int startingPos;

	@Override
	public boolean processState(String line, int startingPos){
		this.startingPos = ++startingPos;
	    return true;
	}

	public int getStartingPos(){
	    return this.startingPos;
    }

    @Override
    public Token getToken(){
	    return null;
    }
}
