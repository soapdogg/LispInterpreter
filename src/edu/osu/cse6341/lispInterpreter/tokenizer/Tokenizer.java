package edu.osu.cse6341.lispInterpreter.tokenizer;

import java.util.Queue;
import java.util.LinkedList;
import java.util.Scanner;

import edu.osu.cse6341.lispInterpreter.tokenizer.states.*;
import edu.osu.cse6341.lispInterpreter.tokenizer.tokens.IToken;
import edu.osu.cse6341.lispInterpreter.tokenizer.tokens.EndOfFileToken;

public class Tokenizer {

    private static final IState [] nextStateArray;
	private Queue<IToken> tokens;

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

	public Tokenizer(){
		tokens = new LinkedList<>();
	}

	public void tokenize(Scanner in){
		tokens = new LinkedList<>();
        boolean continueParsing = true;
        String line;
        IState state;
        while(in.hasNextLine() && continueParsing) {
            line = in.nextLine().trim();
            int startingPos = 0;
            while(startingPos < line.length() && continueParsing) {
                state = nextStateArray[line.charAt(startingPos)];
                continueParsing = state.processState(line, startingPos);
                startingPos = state.getStartingPos();
                if(state.getToken() != null) addToTokens(state.getToken());
            }
        }
		if(continueParsing) tokens.add(new EndOfFileToken());
	}

	public IToken getNextToken(){
		return tokens.remove();	
	}

	public void addToTokens(IToken token){
		tokens.add(token);
	}

	public boolean hasNext(){
		return !tokens.isEmpty();
	}

	public IToken getCurrent(){
		return tokens.peek();
	}

}
