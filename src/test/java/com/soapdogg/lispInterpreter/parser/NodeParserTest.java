package com.soapdogg.lispInterpreter.parser;

import com.soapdogg.lispInterpreter.asserter.TokenKindAsserter;
import com.soapdogg.lispInterpreter.datamodels.ParserResult;
import com.soapdogg.lispInterpreter.datamodels.Token;
import com.soapdogg.lispInterpreter.datamodels.TokenKind;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class NodeParserTest {

    private ExpressionNodeParser expressionNodeParser;
    private ExpressionNodeFinisher expressionNodeFinisher;
    private AtomNodeParser atomNodeParser;

    private Token headToken;
    private List<Token> tokens;

    private NodeParser nodeParser;

    @BeforeEach
    void setup() {
        TokenKindAsserter tokenKindAsserter = Mockito.mock(TokenKindAsserter.class);
        expressionNodeParser = Mockito.mock(ExpressionNodeParser.class);
        expressionNodeFinisher = Mockito.mock(ExpressionNodeFinisher.class);
        atomNodeParser = Mockito.mock(AtomNodeParser.class);

        headToken = Mockito.mock(Token.class);
        tokens = new LinkedList<>();
        tokens.add(headToken);

        Mockito.when(
            tokenKindAsserter.assertTokenIsNotNull(Optional.of(headToken))
        ).thenReturn(headToken);

        Mockito.doNothing().when(tokenKindAsserter).assertTokenIsAtomOrOpen(headToken);

        nodeParser = new NodeParser(
            tokenKindAsserter,
            expressionNodeParser,
            expressionNodeFinisher,
            atomNodeParser
        );
    }

    @Test
    void parseIntoExpressionNodeTest() {
        Mockito.when(headToken.getTokenKind()).thenReturn(TokenKind.OPEN_TOKEN);

        final ParserResult result = Mockito.mock(ParserResult.class);
        Mockito.when(expressionNodeParser.parseExpressionNode(new LinkedList<>())).thenReturn(result);

        final ParserResult expected = Mockito.mock(ParserResult.class);
        Mockito.when(expressionNodeFinisher.finishParsingExpressionNode(result)).thenReturn(expected);

        final ParserResult actual = nodeParser.parseIntoNode(tokens);

        Assertions.assertEquals(expected, actual);

        Mockito.verifyNoInteractions(atomNodeParser);
    }

    @Test
    void parseIntoAtomNodeTest() {
        Mockito.when(headToken.getTokenKind()).thenReturn(TokenKind.NUMERIC_TOKEN);

        final ParserResult expected = Mockito.mock(ParserResult.class);
        Mockito.when(
            atomNodeParser.parseAtomNode(tokens)
        ).thenReturn(expected);

        final ParserResult actual = nodeParser.parseIntoNode(tokens);

        Assertions.assertEquals(expected, actual);

        Mockito.verifyNoInteractions(expressionNodeParser);
    }
}
