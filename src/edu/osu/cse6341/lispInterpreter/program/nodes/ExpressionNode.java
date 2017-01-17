package edu.osu.cse6341.lispInterpreter.program.nodes;

import java.util.Map;
import java.util.HashMap;

import edu.osu.cse6341.lispInterpreter.program.*;
import edu.osu.cse6341.lispInterpreter.tokenizer.Tokenizer;
import edu.osu.cse6341.lispInterpreter.tokenizer.tokens.*;

public class ExpressionNode extends Node{
	
	private static final Map<TokenKind, Node> tokenExpressionChildMap;
	private Node expressionChild;

    static{
        tokenExpressionChildMap = new HashMap<>();
        tokenExpressionChildMap.put(TokenKind.NUMERIC_TOKEN, new AtomNode());
        tokenExpressionChildMap.put(TokenKind.LITERAL_TOKEN, new AtomNode());
        tokenExpressionChildMap.put(TokenKind.OPEN_TOKEN, new ListNode());
    }

	@Override 
	public void parse(Tokenizer tokenizer, Program program){
		IToken token = tokenizer.getCurrent();
		if(!assertTokenIsAtomOrOpen(token, program)) return;
		expressionChild = tokenExpressionChildMap.get(token.getTokenKind());
		expressionChild = expressionChild.newInstance();
        boolean isList = expressionChild instanceof ListNode;
		if(isList) tokenizer.getNextToken();
		expressionChild.parse(tokenizer, program);
		if(program.hasError()) return;
		if(isList) assertTokenIsClose(tokenizer.getNextToken(), program);
	}

	public ExpressionNode(){

    }

	public ExpressionNode(Node node){
	    this.expressionChild = node;
    }

	@Override
	public Node evaluate(){
		return expressionChild.evaluate();
	}

	@Override
	public String getValueToString(){
		return expressionChild.getValueToString();
	}

    @Override
    public String getDotNotationToString(){
        if(expressionChild == null) return "NIL";
	    return expressionChild.getDotNotationToString();
    }

    public Node getExpressionChild(){
        return expressionChild;
    }

    @Override
    public Node newInstance(){
        return new ExpressionNode();
    }

    @Override
    public boolean isList() {
        return expressionChild.isList();
    }

    @Override
    public boolean isNumeric() {
        return expressionChild.isNumeric();
    }

    @Override
    public boolean isLiteral(){
        return expressionChild.isLiteral();
    }

    private static boolean assertTokenIsAtomOrOpen(IToken token, Program program){
		boolean result = tokenExpressionChildMap.containsKey(token.getTokenKind());
		if(!result) {
		    program.markErrorPresent();
		    String errorMessage = "Expected either an ATOM or OPEN token.\n" +
                    "Actual: " + token.getTokenKind().toString() + "    Value: " + token.toString() + "\n";
		    program.setErrorMessage(errorMessage);
        }
        return result;
	}
	
	private static void assertTokenIsClose(IToken token, Program program){
        boolean result = token.getTokenKind() == TokenKind.CLOSE_TOKEN;
        if(!result){
            program.markErrorPresent();
            String errorMessage = "Expected CLOSE token.\n" +
                    "Actual: " + token.getTokenKind().toString() + "    Value: " + token.toString() + "\n";
            program.setErrorMessage(errorMessage);
        }
	}
}
