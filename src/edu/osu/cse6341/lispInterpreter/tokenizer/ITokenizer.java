package edu.osu.cse6341.lispInterpreter.tokenizer;

import java.util.Scanner;

import edu.osu.cse6341.lispInterpreter.tokenizer.tokens.IToken;

public interface ITokenizer{
	public void tokenize(Scanner in);

	public IToken getNextToken();	

	public void addToTokens(IToken token);

	public boolean hasNext();

	public IToken getCurrent();
}


