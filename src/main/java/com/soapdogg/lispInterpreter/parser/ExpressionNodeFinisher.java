package com.soapdogg.lispInterpreter.parser;

import com.soapdogg.lispInterpreter.asserter.TokenKindAsserter;
import com.soapdogg.lispInterpreter.datamodels.ParserResult;
import com.soapdogg.lispInterpreter.datamodels.Token;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor(staticName = "newInstance")
public class ExpressionNodeFinisher {

    private final TokenKindAsserter tokenKindAsserter;
    private final ParserResultBuilder parserResultBuilder;

    public ParserResult finishParsingExpressionNode(
        final ParserResult result
    ) throws Exception {
        final List<Token> remainingTokens = result.getRemainingTokens();
        final Optional<Token> closeTokenOptional = Optional.ofNullable(remainingTokens.remove(0));
        final Token closeToken = tokenKindAsserter.assertTokenIsNotNull(closeTokenOptional);
        tokenKindAsserter.assertTokenIsClose(closeToken);
        return parserResultBuilder.buildParserResult(
            result.getResultingNode(),
            remainingTokens
        );
    }
}
