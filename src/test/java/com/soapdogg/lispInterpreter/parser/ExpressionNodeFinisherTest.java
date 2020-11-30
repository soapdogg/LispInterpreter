package com.soapdogg.lispInterpreter.parser;

import com.soapdogg.lispInterpreter.asserter.TokenKindAsserter;
import com.soapdogg.lispInterpreter.datamodels.Node;
import com.soapdogg.lispInterpreter.datamodels.ParserResult;
import com.soapdogg.lispInterpreter.datamodels.Token;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

class ExpressionNodeFinisherTest {

    private ParserResult result;

    private ParserResult expected;

    private ExpressionNodeFinisher expressionNodeFinisher;

    @BeforeEach
    void setup() {
        TokenKindAsserter tokenKindAsserter = Mockito.mock(TokenKindAsserter.class);
        ParserResultBuilder parserResultBuilder = Mockito.mock(ParserResultBuilder.class);

        result = Mockito.mock(ParserResult.class);

        Node resultingNode = Mockito.mock(Node.class);
        Mockito.when(result.getResultingNode()).thenReturn(resultingNode);

        Token headToken = Mockito.mock(Token.class);
        List<Token> remainingTokens = List.of(headToken);
        Mockito.when(result.getRemainingTokens()).thenReturn(remainingTokens);

        Mockito.when(tokenKindAsserter.assertTokenIsNotNull(Optional.of(headToken))).thenReturn(headToken);

        Mockito.doNothing().when(tokenKindAsserter).assertTokenIsClose(headToken);

        expected = Mockito.mock(ParserResult.class);
        Mockito.when(
            parserResultBuilder.buildParserResult(
                resultingNode,
                List.of()
            )
        ).thenReturn(expected);

        expressionNodeFinisher = new ExpressionNodeFinisher(
            tokenKindAsserter,
            parserResultBuilder
        );
    }

    @Test
    void finishParsingExpressionNodeTest() {
        final var actual = expressionNodeFinisher.finishParsingExpressionNode(result);

        Assertions.assertEquals(expected, actual);
    }
}
