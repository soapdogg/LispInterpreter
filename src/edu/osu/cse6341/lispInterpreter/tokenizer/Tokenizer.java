package edu.osu.cse6341.lispInterpreter.tokenizer;

import java.util.Queue;
import java.util.LinkedList;
import java.util.Scanner;

import edu.osu.cse6341.lispInterpreter.tokenizer.tokens.IToken;
import edu.osu.cse6341.lispInterpreter.tokenizer.tokens.EndOfFileToken;
import edu.osu.cse6341.lispInterpreter.tokenizer.states.IState;
import edu.osu.cse6341.lispInterpreter.tokenizer.states.StartingState;

public class Tokenizer {

	private Queue<IToken> tokens;

	public Tokenizer(){
		tokens = new LinkedList<>();
	}

	public void tokenize(Scanner in){
		tokens = new LinkedList<>();
        boolean continueParsing = true;
        IState state = new StartingState();
        String line;
        while(in.hasNextLine() && continueParsing)
        {
            line = in.nextLine().trim();
            continueParsing = state.processState(this, line, 0, 0);
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
