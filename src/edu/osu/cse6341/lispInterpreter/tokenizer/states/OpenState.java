package edu.osu.cse6341.lispInterpreter.tokenizer.states;

import edu.osu.cse6341.lispInterpreter.tokens.Token;
import edu.osu.cse6341.lispInterpreter.tokens.TokenKind;

public class OpenState implements IState{

    private int startingPos;
    private Token token;

	@Override
	public boolean processState(String line, int startingPos){
		this.startingPos = ++startingPos;
		token = Token.newInstance(
		    TokenKind.OPEN_TOKEN,
            "("
        );
		return true;
	}

	@Override
    public int getStartingPos(){
	    return this.startingPos;
    }

    @Override
    public Token getToken(){
        return token;
    }

}
