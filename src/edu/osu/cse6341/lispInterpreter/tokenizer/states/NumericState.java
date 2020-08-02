package edu.osu.cse6341.lispInterpreter.tokenizer.states;

import edu.osu.cse6341.lispInterpreter.tokens.Token;
import edu.osu.cse6341.lispInterpreter.tokens.TokenKind;

public class NumericState implements IState{

	private static final boolean [] nextStateArray;
	private static final boolean [] errorArray;

	private int startingPos;
    private Token token;
    private ErrorState errorState;

	static {
		nextStateArray = new boolean [256];
		for(char i = '0'; i <= '9'; ++i) nextStateArray[i] = true;
		errorArray = new boolean[256];
		for(char i = 'A'; i <= 'Z'; ++i) errorArray[i] = true;
	}

	@Override
	public boolean processState(String line, int startingPos){
	    int pos = startingPos;

        while(pos < line.length() && (nextStateArray[line.charAt(pos)] || errorArray[line.charAt(pos)])) {
            char currentChar = line.charAt(pos);
            if(errorArray[currentChar]) {
                errorState = new ErrorState();
                return errorState.processState(line, startingPos);
            }
            ++pos;
        }
        String fragment = line.substring(startingPos, pos);
        token = Token.newInstance(
            TokenKind.NUMERIC_TOKEN,
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
        if(errorState != null) return errorState.getToken();
        return token;
    }
}
