package edu.osu.cse6341.lispInterpreter.parser;

import edu.osu.cse6341.lispInterpreter.asserter.TokenKindAsserter;
import edu.osu.cse6341.lispInterpreter.constants.ReservedValuesConstants;
import edu.osu.cse6341.lispInterpreter.generator.NodeGenerator;
import edu.osu.cse6341.lispInterpreter.datamodels.Node;
import edu.osu.cse6341.lispInterpreter.datamodels.Token;
import edu.osu.cse6341.lispInterpreter.datamodels.TokenKind;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

@AllArgsConstructor(staticName = "newInstance")
public class Parser {

    private final TokenKindAsserter tokenKindAsserter;
    private final NodeGenerator nodeGenerator;

    public List<Node> parse(Queue<Token> tokens) throws Exception {

        List<Node> rootNodes = new ArrayList<>();
        while (!tokens.isEmpty()) {
            Node root = parseIntoNode(tokens);
            rootNodes.add(root);
        }
        return rootNodes;
    }

    public Node parseIntoNode(
        Queue<Token> tokens
    ) throws Exception {
        Token token = tokens.peek();
        tokenKindAsserter.assertTokenIsNotNull(token);
        tokenKindAsserter.assertTokenIsAtomOrOpen(token);
        TokenKind currentTokenKind = token.getTokenKind();
        boolean isOpen = currentTokenKind == TokenKind.OPEN_TOKEN;
        if (isOpen) {
            tokens.remove();
            Node result = parseExpressionNode(tokens);
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

    private Node parseExpressionNode(
        Queue<Token> tokens
    ) throws Exception {
        tokenKindAsserter.assertTokenIsNotNull(tokens.peek());
        boolean isClose = tokens.peek().getTokenKind() == TokenKind.CLOSE_TOKEN;
        Node result;
        if (isClose) result = nodeGenerator.generateAtomNode(ReservedValuesConstants.NIL);
        else {
            Node address = parseIntoNode(tokens);
            Node data = parseExpressionNode(tokens);
            result = nodeGenerator.generateExpressionNode(
                address,
                data
            );
        }
        return result;
    }

}
