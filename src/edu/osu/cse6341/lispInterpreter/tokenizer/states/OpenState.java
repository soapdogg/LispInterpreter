package edu.osu.cse6341.lispInterpreter.tokenizer.states;

import edu.osu.cse6341.lispInterpreter.tokenizer.tokens.IToken;
import edu.osu.cse6341.lispInterpreter.tokenizer.tokens.OpenToken;

public class OpenState implements IState{

    private int startingPos;
    private IToken token;

	@Override
	public boolean processState(String line, int startingPos){
		this.startingPos = ++startingPos;
		token = new OpenToken();
		return true;
	}

	@Override
    public int getStartingPos(){
	    return this.startingPos;
    }

    @Override
    public IToken getToken(){
        return token;
    }

}
