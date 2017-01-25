package edu.osu.cse6341.lispInterpreter.program.nodes;

import edu.osu.cse6341.lispInterpreter.program.IEvaluatable;
import edu.osu.cse6341.lispInterpreter.program.IParsable;
import edu.osu.cse6341.lispInterpreter.program.IPrettyPrintable;
import edu.osu.cse6341.lispInterpreter.tokenizer.Tokenizer;
import edu.osu.cse6341.lispInterpreter.tokenizer.tokens.IToken;
import edu.osu.cse6341.lispInterpreter.tokenizer.tokens.TokenKind;

import java.util.HashMap;
import java.util.Map;

public abstract class Node implements IParsable, IEvaluatable, IPrettyPrintable{

    private static final Map<TokenKind, Node> tokenToNodeMap;
    static final String NIL;
    static final String T;

    static{
        tokenToNodeMap = new HashMap<>();
        tokenToNodeMap.put(TokenKind.OPEN_TOKEN, new ExpressionNode());
        tokenToNodeMap.put(TokenKind.NUMERIC_TOKEN, new AtomNode());
        tokenToNodeMap.put(TokenKind.LITERAL_TOKEN, new AtomNode());
        NIL = "NIL";
        T = "T";
    }

    protected abstract Node newInstance();
    public abstract boolean isList();
    public abstract boolean isNumeric();
    public abstract int getLength();

    static Node parseIntoNode(Tokenizer tokenizer) throws Exception{
        IToken token = tokenizer.getCurrent();
        assertTokenIsAtomOrOpen(token);
        Node expressionChild = tokenToNodeMap.get(token.getTokenKind());
        expressionChild = expressionChild.newInstance();
        boolean isList = token.getTokenKind() == TokenKind.OPEN_TOKEN;
        if(isList) tokenizer.getNextToken();
        expressionChild.parse(tokenizer);
        if(isList) tokenizer.getNextToken();
        return expressionChild;
    }

    private static void assertTokenIsAtomOrOpen(IToken token) throws Exception{
        boolean result = tokenToNodeMap.containsKey(token.getTokenKind());
        if (result) return;
        StringBuilder errorMessage = new StringBuilder("Expected either an ATOM or OPEN token.\nActual: ");
        errorMessage.append(token.getTokenKind().toString());
        errorMessage.append("    Value: ");
        errorMessage.append(token.toString());
        errorMessage.append('\n');
        throw new Exception(errorMessage.toString());
    }

    public static boolean equalsNil(String value){
        return NIL.equals(value);
    }

    static boolean equalsT(String value){
        return T.equals(value);
    }
}
