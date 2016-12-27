package edu.osu.cse6341.lispInterpreter.program.nodes;

import java.util.Map;
import java.util.HashMap;

import edu.osu.cse6341.lispInterpreter.program.IParsable;
import edu.osu.cse6341.lispInterpreter.tokenizer.Tokenizer;
import edu.osu.cse6341.lispInterpreter.tokenizer.tokens.*;

public class ExpressionNode implements IParsable{
	
	private static final Map<TokenKind, IExpressionChild> tokenExpressionChildMap;
	private IExpressionChild expressionChild;
	private boolean isList;

    static
    {
        tokenExpressionChildMap = new HashMap<>();
        tokenExpressionChildMap.put(TokenKind.NUMERIC_TOKEN, new AtomNode());
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
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append(expressionChild.toString());
		return sb.toString();
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
