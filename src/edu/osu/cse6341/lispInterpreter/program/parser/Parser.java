package edu.osu.cse6341.lispInterpreter.program.parser;

import edu.osu.cse6341.lispInterpreter.asserter.TokenKindAsserter;
import edu.osu.cse6341.lispInterpreter.program.nodes.AtomNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.ExpressionNode;
import edu.osu.cse6341.lispInterpreter.program.nodes.LispNode;
import edu.osu.cse6341.lispInterpreter.singleton.AsserterSingleton;
import edu.osu.cse6341.lispInterpreter.tokenizer.Tokenizer;
import edu.osu.cse6341.lispInterpreter.tokenizer.tokens.IToken;
import edu.osu.cse6341.lispInterpreter.tokenizer.tokens.TokenKind;

public class Parser {

    private final TokenKindAsserter tokenKindAsserter;

    public Parser() {
        tokenKindAsserter = AsserterSingleton.INSTANCE.getTokenKindAsserter();
    }

    public LispNode parseIntoNode(
        Tokenizer tokenizer
    ) throws Exception {
        IToken token = tokenizer.getCurrent();
        tokenKindAsserter.assertTokenIsAtomOrOpen(token);
        TokenKind currentTokenKind = token.getTokenKind();
        boolean isOpen = currentTokenKind == TokenKind.OPEN_TOKEN;
        if (isOpen) {
            tokenizer.getNextToken();
            LispNode result = parseExpressionNode(tokenizer);
            tokenizer.getNextToken();
            return result;
        } else {
            token = tokenizer.getNextToken();
            String value = token.toString();
            return new AtomNode(value);
        }
    }

    private LispNode parseExpressionNode(
        Tokenizer tokenizer
    ) throws Exception {
        boolean isClose = tokenizer.getCurrent().getTokenKind() == TokenKind.CLOSE_TOKEN;
        if (isClose) return new ExpressionNode();
        LispNode address = parseIntoNode(tokenizer);
        LispNode data = parseExpressionNode(tokenizer);
        return new ExpressionNode(
            address,
            data
        );
    }

}
