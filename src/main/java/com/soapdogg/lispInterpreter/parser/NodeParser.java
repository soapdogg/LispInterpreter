package com.soapdogg.lispInterpreter.parser;

import com.soapdogg.lispInterpreter.asserter.TokenKindAsserter;
import com.soapdogg.lispInterpreter.constants.ReservedValuesConstants;
import com.soapdogg.lispInterpreter.datamodels.Node;
import com.soapdogg.lispInterpreter.datamodels.ParserResult;
import com.soapdogg.lispInterpreter.datamodels.Token;
import com.soapdogg.lispInterpreter.datamodels.TokenKind;
import com.soapdogg.lispInterpreter.generator.NodeGenerator;
import lombok.AllArgsConstructor;

import java.util.Optional;
import java.util.Queue;

@AllArgsConstructor(staticName = "newInstance")
public class NodeParser {

    private final TokenKindAsserter tokenKindAsserter;
    private final NodeGenerator nodeGenerator;

    public ParserResult parseIntoNode(
        Queue<Token> tokens
    ) throws Exception {
        Optional<Token> optionalToken = Optional.ofNullable(tokens.peek());
        Token token = tokenKindAsserter.assertTokenIsNotNull(optionalToken);
        tokenKindAsserter.assertTokenIsAtomOrOpen(token);
        TokenKind currentTokenKind = token.getTokenKind();
        boolean isOpen = currentTokenKind == TokenKind.OPEN_TOKEN;
        if (isOpen) {
            tokens.remove();
            Node result = parseExpressionNode(tokens);
            Optional<Token> closeTokenOptional = Optional.ofNullable(tokens.peek());
            Token closeToken = tokenKindAsserter.assertTokenIsNotNull(closeTokenOptional);
            tokenKindAsserter.assertTokenIsClose(closeToken);
            tokens.remove();
            return ParserResult.newInstance(
                result,
                tokens
            );
        } else {
            token = tokens.remove();
            String value = token.getValue();
            Node atomNode = nodeGenerator.generateAtomNode(value);
            return ParserResult.newInstance(
                atomNode,
                tokens
            );
        }
    }

    private Node parseExpressionNode(
        Queue<Token> tokens
    ) throws Exception {
        Optional<Token> tokenOptional = Optional.ofNullable(tokens.peek());
        Token token = tokenKindAsserter.assertTokenIsNotNull(tokenOptional);
        boolean isClose = token.getTokenKind() == TokenKind.CLOSE_TOKEN;
        Node result;
        if (isClose) result = nodeGenerator.generateAtomNode(ReservedValuesConstants.NIL);
        else {
            Node address;
            TokenKind currentTokenKind = token.getTokenKind();
            boolean isOpen = currentTokenKind == TokenKind.OPEN_TOKEN;
            if (isOpen) {
                tokens.remove();
                Node t = parseExpressionNode(tokens);
                Optional<Token> closeTokenOptional = Optional.ofNullable(tokens.peek());
                Token closeToken = tokenKindAsserter.assertTokenIsNotNull(closeTokenOptional);
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
