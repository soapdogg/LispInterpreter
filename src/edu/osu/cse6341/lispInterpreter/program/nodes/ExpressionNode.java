package edu.osu.cse6341.lispInterpreter.program.nodes;

import java.util.Map;
import java.util.HashMap;

import edu.osu.cse6341.lispInterpreter.tokenizer.Tokenizer;
import edu.osu.cse6341.lispInterpreter.tokenizer.tokens.*;

public class ExpressionNode implements IExpressionNode{
	
	private static final Map<TokenKind, IExpressionChild> tokenExpressionChildMap;
	private IExpressionChild expressionChild;
	private boolean isList;
    /**
     * static constructor
     */
    static
    {
        tokenExpressionChildMap = new HashMap<>();
        tokenExpressionChildMap.put(TokenKind.NUMERIC_TOKEN, new AtomNode());
        tokenExpressionChildMap.put(TokenKind.LITERAL_TOKEN, new AtomNode());
        tokenExpressionChildMap.put(TokenKind.OPEN_TOKEN, new ListNode());
    }

	@Override 
	public void parse(){
		//TODO Valid token type
		expressionChild = tokenExpressionChildMap.get(Tokenizer.getTokenizer().getCurrent().getTokenKind());
		expressionChild = expressionChild.newInstance();
		isList = expressionChild instanceof ListNode;
		if(isList) Tokenizer.getTokenizer().getNextToken();
		expressionChild.parse();
		if(isList) Tokenizer.getTokenizer().getNextToken();
	}

	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append(expressionChild.toString());
		return sb.toString();
	}
}
