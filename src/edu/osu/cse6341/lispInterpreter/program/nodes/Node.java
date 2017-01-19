package edu.osu.cse6341.lispInterpreter.program.nodes;

import edu.osu.cse6341.lispInterpreter.program.IEvaluatable;
import edu.osu.cse6341.lispInterpreter.program.IParsable;
import edu.osu.cse6341.lispInterpreter.program.IPrettyPrintable;
import edu.osu.cse6341.lispInterpreter.program.Program;
import edu.osu.cse6341.lispInterpreter.tokenizer.Tokenizer;
import edu.osu.cse6341.lispInterpreter.tokenizer.tokens.IToken;
import edu.osu.cse6341.lispInterpreter.tokenizer.tokens.TokenKind;

import java.util.HashMap;
import java.util.Map;

public abstract class Node implements IParsable, IEvaluatable, IPrettyPrintable{

    private static final Map<TokenKind, Node> tokenToNodeMap;

    static{
        tokenToNodeMap = new HashMap<>();
        tokenToNodeMap.put(TokenKind.OPEN_TOKEN, new ListNode());
        tokenToNodeMap.put(TokenKind.NUMERIC_TOKEN, new AtomNode());
        tokenToNodeMap.put(TokenKind.LITERAL_TOKEN, new AtomNode());
    }

    protected String errorMessage;
    protected boolean hasError;

    public abstract Node newInstance();
    public abstract boolean isList();
    public abstract boolean isNumeric();
    public abstract boolean isLiteral();
    public abstract int getLength();

    public static Node parseIntoNode(Tokenizer tokenizer, Program program){
        IToken token = tokenizer.getCurrent();
        assertTokenIsAtomOrOpen(token, program);
        if(program.hasError()) return null;
        Node expressionChild = tokenToNodeMap.get(token.getTokenKind());
        expressionChild = expressionChild.newInstance();
        boolean isList = expressionChild instanceof ListNode;
        if(isList) tokenizer.getNextToken();
        expressionChild.parse(tokenizer, program);
        if(program.hasError()) return null;
        if(isList) assertTokenIsClose(tokenizer.getNextToken(), program);
        if(program.hasError()) return null;
        return expressionChild;
    }

    public void setErrorMessage(String errorMessage){
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage(){
        return errorMessage;
    }

    public void markErrorPresent(){
        hasError = true;
    }

    public boolean hasError(){
        return hasError;
    }


    private static void assertTokenIsAtomOrOpen(IToken token, Program program){
        boolean result = tokenToNodeMap.containsKey(token.getTokenKind());
        if (result) return;
        program.markErrorPresent();
        String errorMessage = "Expected either an ATOM or OPEN token.\n" +
                "Actual: " + token.getTokenKind().toString() + "    Value: " + token.toString() + "\n";
        program.setErrorMessage(errorMessage);
    }

    private static void assertTokenIsClose(IToken token, Program program){
        boolean result = token.getTokenKind() == TokenKind.CLOSE_TOKEN;
        if (result) return;
        program.markErrorPresent();
        String errorMessage = "Expected CLOSE token.\n" +
                "Actual: " + token.getTokenKind().toString() + "    Value: " + token.toString() + "\n";
        program.setErrorMessage(errorMessage);
    }
}
