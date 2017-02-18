package edu.osu.cse6341.lispInterpreter.tokenizer.states;

import edu.osu.cse6341.lispInterpreter.tokenizer.tokens.IToken;

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
    public IToken getToken(){
	    return null;
    }
}
