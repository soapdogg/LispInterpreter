package edu.osu.cse6341.lispInterpreter.tokenizer.states;

import java.util.Set;
import java.util.HashSet;

import edu.osu.cse6341.lispInterpreter.tokenizer.ITokenizer;
import edu.osu.cse6341.lispInterpreter.tokenizer.Tokenizer;
import edu.osu.cse6341.lispInterpreter.tokenizer.tokens.ErrorToken;


public class ErrorState implements IState{

	private static final Set<Character> endOfTokenCharacters;

	static{
		endOfTokenCharacters = new HashSet<>();
		endOfTokenCharacters.add(' ');
		endOfTokenCharacters.add('\t');
		endOfTokenCharacters.add('(');
		endOfTokenCharacters.add(')');
	}

	@Override
	public boolean processState(String line, int pos, int startingPos){
		while(++pos < line.length() && !endOfTokenCharacters.contains(line.charAt(pos))); 
		String fragment = line.substring(startingPos, pos);
		Tokenizer tokenizer = Tokenizer.getTokenizer();
		tokenizer.addToTokens(new ErrorToken(fragment));
		return false;
	}
}
