package edu.osu.cse6341.lispInterpreter.program.nodes;

import java.util.Set;
import java.util.HashSet;

import edu.osu.cse6341.lispInterpreter.program.IParsable;
import edu.osu.cse6341.lispInterpreter.tokenizer.Tokenizer;
import edu.osu.cse6341.lispInterpreter.tokenizer.tokens.IToken;
import edu.osu.cse6341.lispInterpreter.tokenizer.tokens.TokenKind;

public class AtomNode implements IExpressionChild{
	
	private String value;
	private boolean isNumeric;
	private boolean isUndefined;
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
	}

	@Override
	public void parse(){
		IToken token =  Tokenizer.getTokenizer().getNextToken();
		assertTokenIsAtom(token);
		value = isNumeric || builtinKeywords.contains(token.toString()) 
			? token.toString()
			: "Undefined";
	}

	@Override
	public void evaluate(){
		if(isUndefined){
			value = "undefined";
			return;
		}
	}

	@Override
	public String toString(){
		return value;
	}

	@Override
	public IExpressionChild newInstance(){
		return new AtomNode();
	}
	
	public boolean isNumeric(){
		return isNumeric;
	}

	public boolean isUndefined(){
		return isUndefined;
	}

	private void assertTokenIsAtom(IToken token){
		TokenKind tokenKind = token.getTokenKind();
		isNumeric = tokenKind == tokenKind.NUMERIC_TOKEN;
		if(tokenKind == TokenKind.NUMERIC_TOKEN ||
			tokenKind == TokenKind.LITERAL_TOKEN) return;
		System.out.println("Expected NUMERIC or LITERAL token, Actual Token:" + 
			tokenKind.toString() +"\tValue: " + token.toString());
	}
}
