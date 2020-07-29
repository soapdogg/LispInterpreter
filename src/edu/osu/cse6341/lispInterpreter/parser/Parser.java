package edu.osu.cse6341.lispInterpreter.parser;

import edu.osu.cse6341.lispInterpreter.asserter.TokenKindAsserter;
import edu.osu.cse6341.lispInterpreter.generator.NodeGenerator;
import edu.osu.cse6341.lispInterpreter.program.nodes.LispNode;
import edu.osu.cse6341.lispInterpreter.tokenizer.Tokenizer;
import edu.osu.cse6341.lispInterpreter.tokenizer.tokens.IToken;
import edu.osu.cse6341.lispInterpreter.tokenizer.tokens.TokenKind;
import lombok.AllArgsConstructor;

@AllArgsConstructor(staticName = "newInstance")
public class Parser {

    private final TokenKindAsserter tokenKindAsserter;
    private final NodeGenerator nodeGenerator;

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
            return nodeGenerator.generateAtomNode(
                value
            );
        }
    }

    private LispNode parseExpressionNode(
        Tokenizer tokenizer
    ) throws Exception {
        boolean isClose = tokenizer.getCurrent().getTokenKind() == TokenKind.CLOSE_TOKEN;
        LispNode result;
        if (isClose) result = nodeGenerator.generateExpressionNode();
        else {
            LispNode address = parseIntoNode(tokenizer);
            LispNode data = parseExpressionNode(tokenizer);
            result = nodeGenerator.generateExpressionNode(
                address,
                data
            );
        }
        return result;
    }

}
