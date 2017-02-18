package edu.osu.cse6341.lispInterpreter.tokenizer.states;

import edu.osu.cse6341.lispInterpreter.tokenizer.tokens.CloseToken;
import edu.osu.cse6341.lispInterpreter.tokenizer.tokens.IToken;

public class CloseState implements IState{

    private int startingPos;
    private IToken token;

	@Override
	public boolean processState(String line, int startingPos){
		this.startingPos = ++startingPos;
		this.token = new CloseToken();
        return true;
	}

    @Override
    public int getStartingPos() {
        return startingPos;
    }

    @Override
    public IToken getToken(){
	    return this.token;
    }

}
