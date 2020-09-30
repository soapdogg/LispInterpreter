package com.soapdogg.lispInterpreter.parser;

import com.soapdogg.lispInterpreter.asserter.TokenKindAsserter;
import com.soapdogg.lispInterpreter.constants.ReservedValuesConstants;
import com.soapdogg.lispInterpreter.datamodels.Node;
import com.soapdogg.lispInterpreter.datamodels.Token;
import com.soapdogg.lispInterpreter.datamodels.TokenKind;
import com.soapdogg.lispInterpreter.generator.NodeGenerator;
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

    private Node parseIntoNode(
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
            Node address;
            Token token = tokens.peek();
            tokenKindAsserter.assertTokenIsNotNull(token);
            tokenKindAsserter.assertTokenIsAtomOrOpen(token);
            TokenKind currentTokenKind = token.getTokenKind();
            boolean isOpen = currentTokenKind == TokenKind.OPEN_TOKEN;
            if (isOpen) {
                tokens.remove();
                Node t = parseExpressionNode(tokens);
                Token closeToken = tokens.peek();
                tokenKindAsserter.assertTokenIsNotNull(closeToken);
                tokenKindAsserter.assertTokenIsClose(closeToken);
                tokens.remove();
                address = t;
            } else {
                token = tokens.remove();
                String value = token.getValue();
                address = nodeGenerator.generateAtomNode(
                    value
                );
            }
            Node data = parseExpressionNode(tokens);
            result = nodeGenerator.generateExpressionNode(
                address,
                data
            );
        }
        return result;
    }

}
