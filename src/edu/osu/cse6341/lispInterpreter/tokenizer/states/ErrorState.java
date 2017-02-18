package edu.osu.cse6341.lispInterpreter.tokenizer.states;

import edu.osu.cse6341.lispInterpreter.tokenizer.tokens.ErrorToken;
import edu.osu.cse6341.lispInterpreter.tokenizer.tokens.IToken;


public class ErrorState implements IState{

    private IToken token;

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
		token = new ErrorToken(fragment);
	    return false;
	}

	@Override
    public int getStartingPos(){
	    return -1;
    }

    @Override
    public IToken getToken(){
        return token;
    }
}
