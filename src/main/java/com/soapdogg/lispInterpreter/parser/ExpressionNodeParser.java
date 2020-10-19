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
public class ExpressionNodeParser {

    private final TokenKindAsserter tokenKindAsserter;
    private final NodeGenerator nodeGenerator;
    private final AtomNodeParser atomNodeParser;

    public ParserResult parseExpressionNode(
        Queue<Token> tokens
    ) throws Exception {
        Optional<Token> tokenOptional = Optional.ofNullable(tokens.peek());
        Token token = tokenKindAsserter.assertTokenIsNotNull(tokenOptional);
        boolean isClose = token.getTokenKind() == TokenKind.CLOSE_TOKEN;
        Node result;
        if (isClose) {
            result = nodeGenerator.generateAtomNode(ReservedValuesConstants.NIL);
            return ParserResult.newInstance(
                result,
                tokens
            );
        }
        else {
            Node address;
            Queue<Token> remainingTokens;
            TokenKind currentTokenKind = token.getTokenKind();
            boolean isOpen = currentTokenKind == TokenKind.OPEN_TOKEN;
            if (isOpen) {
                tokens.remove();
                ParserResult t = parseExpressionNode(tokens);
                remainingTokens = t.getRemainingTokens();
                Optional<Token> closeTokenOptional = Optional.ofNullable(remainingTokens.poll());
                Token closeToken = tokenKindAsserter.assertTokenIsNotNull(closeTokenOptional);
                tokenKindAsserter.assertTokenIsClose(closeToken);
                address = t.getResultingNode();
            } else {
                ParserResult t = atomNodeParser.parseAtomNode(tokens);
                address = t.getResultingNode();
                remainingTokens = t.getRemainingTokens();
            }
            ParserResult data = parseExpressionNode(remainingTokens);
            result = nodeGenerator.generateExpressionNode(
                address,
                data.getResultingNode()
            );
            return ParserResult.newInstance(
                result,
                data.getRemainingTokens()
            );
        }
    }
}
