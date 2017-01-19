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

    static{
        tokenToNodeMap = new HashMap<>();
        tokenToNodeMap.put(TokenKind.OPEN_TOKEN, new ExpressionNode());
        tokenToNodeMap.put(TokenKind.NUMERIC_TOKEN, new AtomNode());
        tokenToNodeMap.put(TokenKind.LITERAL_TOKEN, new AtomNode());
    }

    protected abstract Node newInstance();
    public abstract boolean isList();
    public abstract boolean isNumeric();
    public abstract boolean isLiteral();
    public abstract int getLength();

    static Node parseIntoNode(Tokenizer tokenizer) throws Exception{
        IToken token = tokenizer.getCurrent();
        assertTokenIsAtomOrOpen(token);
        Node expressionChild = tokenToNodeMap.get(token.getTokenKind());
        expressionChild = expressionChild.newInstance();
        boolean isList = token.getTokenKind() == TokenKind.OPEN_TOKEN;
        if(isList) tokenizer.getNextToken();
        expressionChild.parse(tokenizer);
        if(isList) assertTokenIsClose(tokenizer.getNextToken());
        return expressionChild;
    }

    private static void assertTokenIsAtomOrOpen(IToken token) throws Exception{
        boolean result = tokenToNodeMap.containsKey(token.getTokenKind());
        if (result) return;
        String errorMessage = "Expected either an ATOM or OPEN token.\n" +
                "Actual: " + token.getTokenKind().toString() + "    Value: " + token.toString() + "\n";
        throw new Exception(errorMessage);
    }

    private static void assertTokenIsClose(IToken token) throws Exception{
        boolean result = token.getTokenKind() == TokenKind.CLOSE_TOKEN;
        if (result) return;
        String errorMessage = "Expected CLOSE token.\n" +
                "Actual: " + token.getTokenKind().toString() + "    Value: " + token.toString() + "\n";
        throw new Exception(errorMessage);
    }
}
