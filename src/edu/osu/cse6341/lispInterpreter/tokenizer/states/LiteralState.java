package edu.osu.cse6341.lispInterpreter.tokenizer.states;

import edu.osu.cse6341.lispInterpreter.tokenizer.Tokenizer;
import edu.osu.cse6341.lispInterpreter.tokenizer.tokens.LiteralToken;
import edu.osu.cse6341.lispInterpreter.tokenizer.tokens.IToken;

public class LiteralState implements IState{

	private static final IState [] nextStateArray;

	static {
		nextStateArray = new IState [256];
		for(char i = 'A'; i <= 'Z'; ++i) nextStateArray[i] = new LiteralState();
		for(char i = '0'; i <= '9'; ++i) nextStateArray[i] = new LiteralState();
	}

	@Override
	public boolean processState(Tokenizer tokenizer, String line, int pos, int startingPos){
		if(++pos >= line.length()) return finishState(tokenizer, line, pos, startingPos);
		char currentChar = line.charAt(pos);
		if(nextStateArray[currentChar] == null) return finishState(tokenizer, line, pos, startingPos);
		return nextStateArray[currentChar].processState(tokenizer, line, pos, startingPos);
	}

	private static boolean finishState(Tokenizer tokenizer, String line, int pos, int startingPos)
	{
        String fragment = line.substring(startingPos, pos);
		IToken token = new LiteralToken(fragment);
        tokenizer.addToTokens(token);
        IState nextState = new StartingState();
        startingPos = pos;
        return nextState.processState(tokenizer, line, pos, startingPos);
	}
}
