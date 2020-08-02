package edu.osu.cse6341.lispInterpreter.tokenizer.states;

import edu.osu.cse6341.lispInterpreter.tokens.Token;
import edu.osu.cse6341.lispInterpreter.tokens.TokenKind;


public class ErrorState implements IState{

    private Token token;

	private static final boolean [] endOfToken;

	static{
	    endOfToken = new boolean[256];
	    endOfToken[' '] = true;
	    endOfToken['\t'] = true;
	    endOfToken['\r'] = true;
	    endOfToken['('] = true;
	    endOfToken[')'] =true;
	}

	@Override
	public boolean processState(String line, int startingPos){
		int pos = startingPos;
	    while(++pos < line.length() && !endOfToken[line.charAt(pos)]);
		String fragment = line.substring(startingPos, pos);
		token = Token.newInstance(
			TokenKind.ERROR_TOKEN,
			fragment
		);
	    return false;
	}

	@Override
    public int getStartingPos(){
	    return -1;
    }

    @Override
    public Token getToken(){
        return token;
    }
}
