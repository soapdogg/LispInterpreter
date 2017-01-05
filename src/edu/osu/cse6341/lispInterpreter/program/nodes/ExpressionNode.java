package edu.osu.cse6341.lispInterpreter.program.nodes;

import java.util.Map;
import java.util.HashMap;

import edu.osu.cse6341.lispInterpreter.program.IParsable;
import edu.osu.cse6341.lispInterpreter.program.IEvaluatable;
import edu.osu.cse6341.lispInterpreter.tokenizer.Tokenizer;
import edu.osu.cse6341.lispInterpreter.tokenizer.tokens.*;
import edu.osu.cse6341.lispInterpreter.program.ExpressionKind;

public class ExpressionNode implements IParsable, IEvaluatable{
	
	private static final Map<TokenKind, IExpressionChild> tokenExpressionChildMap;
	private IExpressionChild expressionChild;
	private boolean isList;

    static{
        tokenExpressionChildMap = new HashMap<>();
        tokenExpressionChildMap.put(TokenKind.NUMERIC_TOKEN, new AtomNode());
        tokenExpressionChildMap.put(TokenKind.LITERAL_TOKEN, new AtomNode());
        tokenExpressionChildMap.put(TokenKind.OPEN_TOKEN, new ListNode());
    }

	@Override 
	public void parse(){
		IToken token = Tokenizer.getTokenizer().getCurrent();
		assertTokenIsAtomOrOpen(token);
		expressionChild = tokenExpressionChildMap.get(token.getTokenKind());
		expressionChild = expressionChild.newInstance();
		isList = expressionChild instanceof ListNode;
		if(isList) Tokenizer.getTokenizer().getNextToken();
		expressionChild.parse();
		if(isList) assertTokenIsClose(Tokenizer.getTokenizer().getNextToken());
	}

	@Override
	public void evaluate(){
		expressionChild.evaluate();
	}

	@Override
	public String toString(){
		return expressionChild.toString();
	}

	public boolean isList(){
		return isList;
	}

	public ExpressionKind getExpressionKind(){
		return expressionChild.getExpressionKind();
	}

	private static void assertTokenIsAtomOrOpen(IToken token){
		if(tokenExpressionChildMap.containsKey(token.getTokenKind())) return;
		System.out.println("Expected either an ATOM or OPEN token. Actual: " 
			+ token.getTokenKind().toString() + "\tValue: " + token.toString());
		System.exit(-5);
	}
	
	private static void assertTokenIsClose(IToken token){
		if(token.getTokenKind() == TokenKind.CLOSE_TOKEN) return;
		System.out.println("Expected CLOSE token. Actual: " 
			+ token.getTokenKind().toString() + "\tValue: " + token.toString());
		System.exit(-6);
	}
}
