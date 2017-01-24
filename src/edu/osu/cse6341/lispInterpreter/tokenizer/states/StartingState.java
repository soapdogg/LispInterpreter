package edu.osu.cse6341.lispInterpreter.tokenizer.states;

import edu.osu.cse6341.lispInterpreter.tokenizer.Tokenizer;

public class StartingState implements IState{
	private static final IState [] nextStateArray;

	static{
		nextStateArray = new IState[256];
		for(int i = 0; i < 256; ++i) nextStateArray[i] = new ErrorState();
		for(char i = 'A'; i <= 'Z'; ++i) nextStateArray[i] = new LiteralState();
		for(char i = '0'; i <= '9'; ++i) nextStateArray[i] = new NumericState();
		nextStateArray['\t'] = new WhitespaceState();
		nextStateArray[' '] = new WhitespaceState();
		nextStateArray['\r'] = new WhitespaceState();
		nextStateArray['('] = new OpenState();
		nextStateArray[')'] = new CloseState();
	}

	@Override
	public boolean processState(Tokenizer tokenizer, String line, int pos, int startingPos){
		if(line.length() <= pos) return true;
		char currentChar = line.charAt(pos);
		IState nextState = nextStateArray[currentChar];
		return nextState.processState(tokenizer, line, pos, startingPos);
	}
}
