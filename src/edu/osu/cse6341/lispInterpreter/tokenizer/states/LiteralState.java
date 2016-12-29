package edu.osu.cse6341.lispInterpreter.tokenizer.states;

import edu.osu.cse6341.lispInterpreter.tokenizer.Tokenizer;
import edu.osu.cse6341.lispInterpreter.tokenizer.tokens.LiteralToken;
import edu.osu.cse6341.lispInterpreter.tokenizer.tokens.IToken;
import edu.osu.cse6341.lispInterpreter.tokenizer.tokens.TokenKind;

import java.util.Map;
import java.util.HashMap;

public class LiteralState implements IState{

	private static final Map<String, TokenKind> reservedWordTokenMap;

	static
	{
		reservedWordTokenMap = new HashMap<>();
		reservedWordTokenMap.put("CAR", TokenKind.CAR_TOKEN);
		reservedWordTokenMap.put("CDR", TokenKind.CDR_TOKEN);
		reservedWordTokenMap.put("CONS", TokenKind.CONS_TOKEN);
		reservedWordTokenMap.put("ATOM", TokenKind.ATOM_TOKEN);
		reservedWordTokenMap.put("INT", TokenKind.INT_TOKEN);
		reservedWordTokenMap.put("NULL", TokenKind.NULL_TOKEN);
		reservedWordTokenMap.put("EQ", TokenKind.EQ_TOKEN);
		reservedWordTokenMap.put("PLUS", TokenKind.PLUS_TOKEN);
		reservedWordTokenMap.put("MINUS", TokenKind.MINUS_TOKEN);
		reservedWordTokenMap.put("TIMES", TokenKind.TIMES_TOKEN);
		reservedWordTokenMap.put("LESS", TokenKind.LESS_TOKEN);
		reservedWordTokenMap.put("GREATER", TokenKind.GREATER_TOKEN);
	}

	private static int determineEndCharacterPosition(String line, int pos)
	{
		int endCharPos = pos;
		while(endCharPos < line.length() && Character.isUpperCase(line.charAt(endCharPos))) ++endCharPos;
		return endCharPos;
	}

	private static IState processReservedWord(String reservedWord)
	{
		boolean containsWord = reservedWordTokenMap.containsKey(reservedWord);
		Tokenizer tokenizer = Tokenizer.getTokenizer();
		TokenKind tokenKind = containsWord ? reservedWordTokenMap.get(reservedWord) : TokenKind.LITERAL_TOKEN;
		IToken token = new LiteralToken(reservedWord, tokenKind);
		tokenizer.addToTokens(token);
		return new StartingState(); 
	}

	@Override
	public boolean processState(String line, int pos, int startPos)
	{
		int endCharPos = determineEndCharacterPosition(line, pos);
		String reservedWord = line.substring(pos, endCharPos);
		IState nextState = processReservedWord(reservedWord);
		return nextState.processState(line, pos + reservedWord.length(), startPos + reservedWord.length());
	}
}
