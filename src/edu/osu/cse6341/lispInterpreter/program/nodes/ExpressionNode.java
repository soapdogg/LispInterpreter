package edu.osu.cse6341.lispInterpreter.program.nodes;

import java.util.Map;
import java.util.HashMap;

import edu.osu.cse6341.lispInterpreter.program.*;
import edu.osu.cse6341.lispInterpreter.tokenizer.Tokenizer;
import edu.osu.cse6341.lispInterpreter.tokenizer.tokens.*;

public class ExpressionNode implements IParsable, IEvaluatable, IPrettyPrintable{
	
	private static final Map<TokenKind, IExpressionChild> tokenExpressionChildMap;
	private IExpressionChild expressionChild;

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

	@Override
	public void evaluate(){
		expressionChild.evaluate();
	}

	@Override
	public String getValue(){
		return expressionChild.getValue();
	}

    @Override
    public String getDotNotation(){
        if(expressionChild == null) return "NIL";
	    return expressionChild.getDotNotation();
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
	
	private static boolean assertTokenIsClose(IToken token, Program program){
        boolean result = token.getTokenKind() == TokenKind.CLOSE_TOKEN;
        if(!result){
            program.markErrorPresent();
            String errorMessage = "Expected CLOSE token.\n" +
                    "Actual: " + token.getTokenKind().toString() + "    Value: " + token.toString() + "\n";
            program.setErrorMessage(errorMessage);
        }
        return result;
	}
}
