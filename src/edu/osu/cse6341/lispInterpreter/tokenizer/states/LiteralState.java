package edu.osu.cse6341.lispInterpreter.tokenizer.states;

import edu.osu.cse6341.lispInterpreter.tokens.Token;
import edu.osu.cse6341.lispInterpreter.tokens.TokenKind;

public class LiteralState implements IState{

	private static final boolean [] nextStateArray;

	private int startingPos;
	private Token token;

	static {
		nextStateArray = new boolean [256];
		for(char i = 'A'; i <= 'Z'; ++i) nextStateArray[i] = true;
		for(char i = '0'; i <= '9'; ++i) nextStateArray[i] = true;
	}

	@Override
	public boolean processState(String line, int startingPos){
		int pos = startingPos;
	    while(pos < line.length() && nextStateArray[line.charAt(pos)]){
	        ++pos;
        }
        String fragment = line.substring(startingPos, pos);
        token = Token.newInstance(
        	TokenKind.LITERAL_TOKEN,
			fragment
		);
        this.startingPos = pos;
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
