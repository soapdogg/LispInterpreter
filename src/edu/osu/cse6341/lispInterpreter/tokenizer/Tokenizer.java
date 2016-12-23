package edu.osu.cse6341.lispInterpreter.tokenizer;

import java.util.Queue;
import java.util.LinkedList;
import java.util.Scanner;

import edu.osu.cse6341.lispInterpreter.tokenizer.tokens.IToken;
import edu.osu.cse6341.lispInterpreter.tokenizer.tokens.EndOfFileToken;
import edu.osu.cse6341.lispInterpreter.tokenizer.states.IState;
import edu.osu.cse6341.lispInterpreter.tokenizer.states.StartingState;

public class Tokenizer implements ITokenizer{

	private static Tokenizer singletonTokenizer;
	private Queue<IToken> tokens;	

	private Tokenizer(){
		tokens = new LinkedList<>();
	}
	
	public static Tokenizer getTokenizer(){
		if(singletonTokenizer == null) singletonTokenizer = new Tokenizer();
		return singletonTokenizer;
	}

	@Override
	public void tokenize(Scanner in){
		tokens = new LinkedList<>();
        boolean continueParsing = true;
        IState state = new StartingState();
        String line;
        while(in.hasNextLine() && continueParsing)
        {
            line = in.nextLine().trim();
            continueParsing = state.processState(line, 0, 0);
        }
		if(continueParsing) tokens.add(new EndOfFileToken());
	}

	@Override
	public IToken getNextToken(){
		return tokens.remove();	
	}

	@Override
	public void addToTokens(IToken token){
		tokens.add(token);
	}

	@Override
	public boolean hasNext(){
		return !tokens.isEmpty();
	}

	@Override
	public IToken getCurrent(){
		return tokens.peek();
	}

}
