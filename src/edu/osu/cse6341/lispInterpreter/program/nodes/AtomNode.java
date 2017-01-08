package edu.osu.cse6341.lispInterpreter.program.nodes;

import java.util.Set;
import java.util.HashSet;

import edu.osu.cse6341.lispInterpreter.program.ExpressionKind;
import edu.osu.cse6341.lispInterpreter.tokenizer.Tokenizer;
import edu.osu.cse6341.lispInterpreter.tokenizer.tokens.IToken;
import edu.osu.cse6341.lispInterpreter.tokenizer.tokens.TokenKind;

public class AtomNode implements IExpressionChild{
	
	private String value;
	private ExpressionKind expressionKind;
	private static Set<String> builtinKeywords;

	static{
		builtinKeywords = new HashSet<>();
		builtinKeywords.add("CAR");
		builtinKeywords.add("CDR");
		builtinKeywords.add("CONS");
		builtinKeywords.add("ATOM");
		builtinKeywords.add("INT");
		builtinKeywords.add("NULL");
		builtinKeywords.add("EQ");
		builtinKeywords.add("PLUS");
		builtinKeywords.add("MINUS");
		builtinKeywords.add("TIMES");
		builtinKeywords.add("LESS");
		builtinKeywords.add("GREATER");
		builtinKeywords.add("QUOTE");
		builtinKeywords.add("COND");
		builtinKeywords.add("T");
		builtinKeywords.add("NIL");
	}

	@Override
	public void parse(Tokenizer tokenizer){
		IToken token = tokenizer.getNextToken();
		assertTokenIsAtom(token);
		if(token.getTokenKind() == TokenKind.NUMERIC_TOKEN) expressionKind = ExpressionKind.NUMERIC_EXPRESSION;
		else if (builtinKeywords.contains(token.toString())) expressionKind = ExpressionKind.LITERAL_EXPRESSION;
		else expressionKind = ExpressionKind.UNDEFINED_EXPRESSION;
		value = token.toString();
	}

	@Override
	public void evaluate(){
		if(expressionKind == ExpressionKind.UNDEFINED_EXPRESSION) value = "undefined";
	}

	public String getValue(){
		return value;
	}

	@Override
	public ExpressionKind getExpressionKind(){
		return expressionKind;
	}

	@Override
	public IExpressionChild newInstance(){
		return new AtomNode();
	}
	
	private void assertTokenIsAtom(IToken token){
		TokenKind tokenKind = token.getTokenKind();
		if(tokenKind == TokenKind.NUMERIC_TOKEN ||
			tokenKind == TokenKind.LITERAL_TOKEN) return;
		System.out.println("Expected NUMERIC or LITERAL token, Actual Token:" + 
			tokenKind.toString() +"\tValue: " + token.toString());
	}
}
