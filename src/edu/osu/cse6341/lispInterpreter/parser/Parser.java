package edu.osu.cse6341.lispInterpreter.parser;

import edu.osu.cse6341.lispInterpreter.asserter.TokenKindAsserter;
import edu.osu.cse6341.lispInterpreter.constants.ReservedValuesConstants;
import edu.osu.cse6341.lispInterpreter.generator.NodeGenerator;
import edu.osu.cse6341.lispInterpreter.nodes.LispNode;
import edu.osu.cse6341.lispInterpreter.tokenizer.Tokenizer;
import edu.osu.cse6341.lispInterpreter.tokens.Token;
import edu.osu.cse6341.lispInterpreter.tokens.TokenKind;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

@AllArgsConstructor(staticName = "newInstance")
public class Parser {

    private final TokenKindAsserter tokenKindAsserter;
    private final NodeGenerator nodeGenerator;

    public List<LispNode> parse(Queue<Token> tokens) throws Exception {

        List<LispNode> rootNodes = new ArrayList<>();
        while (!tokens.isEmpty()) {
            LispNode root = parseIntoNode(tokens);
            rootNodes.add(root);
        }
        return rootNodes;
    }

    public LispNode parseIntoNode(
        Queue<Token> tokens
    ) throws Exception {
        Token token = tokens.peek();
        tokenKindAsserter.assertTokenIsNotNull(token);
        tokenKindAsserter.assertTokenIsAtomOrOpen(token);
        TokenKind currentTokenKind = token.getTokenKind();
        boolean isOpen = currentTokenKind == TokenKind.OPEN_TOKEN;
        if (isOpen) {
            tokens.remove();
            LispNode result = parseExpressionNode(tokens);
            Token closeToken = tokens.peek();
            tokenKindAsserter.assertTokenIsNotNull(closeToken);
            tokenKindAsserter.assertTokenIsClose(closeToken);
            tokens.remove();
            return result;
        } else {
            token = tokens.remove();
            String value = token.getValue();
            return nodeGenerator.generateAtomNode(
                value
            );
        }
    }

    private LispNode parseExpressionNode(
        Queue<Token> tokens
    ) throws Exception {
        tokenKindAsserter.assertTokenIsNotNull(tokens.peek());
        boolean isClose = tokens.peek().getTokenKind() == TokenKind.CLOSE_TOKEN;
        LispNode result;
        if (isClose) result = nodeGenerator.generateAtomNode(ReservedValuesConstants.NIL);
        else {
            LispNode address = parseIntoNode(tokens);
            LispNode data = parseExpressionNode(tokens);
            result = nodeGenerator.generateExpressionNode(
                address,
                data
            );
        }
        return result;
    }

}
