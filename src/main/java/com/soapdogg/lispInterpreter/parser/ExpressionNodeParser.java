package com.soapdogg.lispInterpreter.parser;

import com.soapdogg.lispInterpreter.asserter.TokenKindAsserter;
import com.soapdogg.lispInterpreter.constants.ReservedValuesConstants;
import com.soapdogg.lispInterpreter.datamodels.ExpressionNode;
import com.soapdogg.lispInterpreter.datamodels.Node;
import com.soapdogg.lispInterpreter.datamodels.ParserResult;
import com.soapdogg.lispInterpreter.datamodels.Token;
import com.soapdogg.lispInterpreter.datamodels.TokenKind;
import com.soapdogg.lispInterpreter.generator.NodeGenerator;
import lombok.AllArgsConstructor;

import java.util.Optional;
import java.util.Queue;

@AllArgsConstructor(staticName = "newInstance")
public class ExpressionNodeParser {

    private final TokenKindAsserter tokenKindAsserter;
    private final NodeGenerator nodeGenerator;
    private final ExpressionNodeFinisher expressionNodeFinisher;
    private final AtomNodeParser atomNodeParser;
    private final ParserResultBuilder parserResultBuilder;

    public ParserResult parseExpressionNode(
        final Queue<Token> tokens
    ) throws Exception {
        final Optional<Token> tokenOptional = Optional.ofNullable(tokens.peek());
        final Token token = tokenKindAsserter.assertTokenIsNotNull(tokenOptional);
        final boolean isClose = token.getTokenKind() == TokenKind.CLOSE_TOKEN;
        if (isClose) {
            final Node result = nodeGenerator.generateAtomNode(ReservedValuesConstants.NIL);
            return parserResultBuilder.buildParserResult(
                result,
                tokens
            );
        }
        else {
            ParserResult addressParserResult;
            final TokenKind currentTokenKind = token.getTokenKind();
            final boolean isOpen = currentTokenKind == TokenKind.OPEN_TOKEN;
            if (isOpen) {
                tokens.remove();
                final ParserResult t = parseExpressionNode(tokens);
                addressParserResult = expressionNodeFinisher.finishParsingExpressionNode(t);
            } else {
                addressParserResult = atomNodeParser.parseAtomNode(tokens);
            }
            final ParserResult data = parseExpressionNode(addressParserResult.getRemainingTokens());
            final var addressResultingNode = addressParserResult.getResultingNode();
            final var dataResultingNode = data.getResultingNode();

            final ExpressionNode result = nodeGenerator.generateExpressionNode(
                addressResultingNode,
                dataResultingNode
            );

            return parserResultBuilder.buildParserResult(
                result,
                data.getRemainingTokens()
            );
        }
    }
}
