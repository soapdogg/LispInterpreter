package com.soapdogg.lispInterpreter.parser;

import com.soapdogg.lispInterpreter.asserter.TokenKindAsserter;
import com.soapdogg.lispInterpreter.datamodels.ParserResult;
import com.soapdogg.lispInterpreter.datamodels.Token;
import com.soapdogg.lispInterpreter.datamodels.TokenKind;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor(staticName = "newInstance")
public class NodeParser {

    private final TokenKindAsserter tokenKindAsserter;
    private final ExpressionNodeParser expressionNodeParser;
    private final ExpressionNodeFinisher expressionNodeFinisher;
    private final AtomNodeParser atomNodeParser;

    public ParserResult parseIntoNode(
        final List<Token> tokens
    ) throws Exception {
        final Optional<Token> optionalToken = Optional.ofNullable(tokens.get(0));
        final Token token = tokenKindAsserter.assertTokenIsNotNull(optionalToken);
        tokenKindAsserter.assertTokenIsAtomOrOpen(token);
        final TokenKind currentTokenKind = token.getTokenKind();
        boolean isOpen = currentTokenKind == TokenKind.OPEN_TOKEN;
        if (isOpen) {
            tokens.remove(0);
            final ParserResult result = expressionNodeParser.parseExpressionNode(tokens);
            return expressionNodeFinisher.finishParsingExpressionNode(result);
        } else {
            return atomNodeParser.parseAtomNode(
                tokens
            );
        }
    }
}
