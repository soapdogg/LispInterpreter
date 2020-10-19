package com.soapdogg.lispInterpreter.parser;

import com.soapdogg.lispInterpreter.asserter.TokenKindAsserter;
import com.soapdogg.lispInterpreter.datamodels.ParserResult;
import com.soapdogg.lispInterpreter.datamodels.Token;
import com.soapdogg.lispInterpreter.datamodels.TokenKind;
import lombok.AllArgsConstructor;

import java.util.Optional;
import java.util.Queue;

@AllArgsConstructor(staticName = "newInstance")
public class NodeParser {

    private final TokenKindAsserter tokenKindAsserter;
    private final ExpressionNodeParser expressionNodeParser;
    private final AtomNodeParser atomNodeParser;

    public ParserResult parseIntoNode(
        final Queue<Token> tokens
    ) throws Exception {
        final Optional<Token> optionalToken = Optional.ofNullable(tokens.peek());
        final Token token = tokenKindAsserter.assertTokenIsNotNull(optionalToken);
        tokenKindAsserter.assertTokenIsAtomOrOpen(token);
        final TokenKind currentTokenKind = token.getTokenKind();
        boolean isOpen = currentTokenKind == TokenKind.OPEN_TOKEN;
        if (isOpen) {
            tokens.remove();
            final ParserResult result = expressionNodeParser.parseExpressionNode(tokens);
            final Queue<Token> remainingTokens = result.getRemainingTokens();
            final Optional<Token> closeTokenOptional = Optional.ofNullable(remainingTokens.poll());
            final Token closeToken = tokenKindAsserter.assertTokenIsNotNull(closeTokenOptional);
            tokenKindAsserter.assertTokenIsClose(closeToken);
            return ParserResult.newInstance(
                result.getResultingNode(),
                remainingTokens
            );
        } else {
            return atomNodeParser.parseAtomNode(
                tokens
            );
        }
    }
}
