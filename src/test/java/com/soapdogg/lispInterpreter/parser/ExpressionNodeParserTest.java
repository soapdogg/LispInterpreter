package com.soapdogg.lispInterpreter.parser;

import com.soapdogg.lispInterpreter.asserter.TokenKindAsserter;
import com.soapdogg.lispInterpreter.constants.ReservedValuesConstants;
import com.soapdogg.lispInterpreter.datamodels.AtomNode;
import com.soapdogg.lispInterpreter.datamodels.ExpressionNode;
import com.soapdogg.lispInterpreter.datamodels.Node;
import com.soapdogg.lispInterpreter.datamodels.ParserResult;
import com.soapdogg.lispInterpreter.datamodels.Token;
import com.soapdogg.lispInterpreter.datamodels.TokenKind;
import com.soapdogg.lispInterpreter.generator.NodeGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

class ExpressionNodeParserTest {

    private TokenKindAsserter tokenKindAsserter;
    private NodeGenerator nodeGenerator;
    private ExpressionNodeFinisher expressionNodeFinisher;
    private AtomNodeParser atomNodeParser;
    private ParserResultBuilder parserResultBuilder;

    private Token headToken;
    private List<Token> tokens;

    private ExpressionNodeParser expressionNodeParser;

    @BeforeEach
    void setup() {
        tokenKindAsserter = Mockito.mock(TokenKindAsserter.class);
        nodeGenerator = Mockito.mock(NodeGenerator.class);
        expressionNodeFinisher = Mockito.mock(ExpressionNodeFinisher.class);
        atomNodeParser = Mockito.mock(AtomNodeParser.class);
        parserResultBuilder = Mockito.mock(ParserResultBuilder.class);

        headToken = Mockito.mock(Token.class);
        tokens = new LinkedList<>();
        tokens.add(headToken);

        Mockito.when(
            tokenKindAsserter.assertTokenIsNotNull(Optional.of(headToken))
        ).thenReturn(headToken);

        expressionNodeParser = new ExpressionNodeParser(
            tokenKindAsserter,
            nodeGenerator,
            expressionNodeFinisher,
            atomNodeParser,
            parserResultBuilder
        );
    }

    @Test
    void headTokenIsCloseTest() {
        Mockito.when(headToken.getTokenKind()).thenReturn(TokenKind.CLOSE_TOKEN);

        final AtomNode result = Mockito.mock(AtomNode.class);
        Mockito.when(nodeGenerator.generateAtomNode(ReservedValuesConstants.NIL)).thenReturn(result);

        final ParserResult expected = Mockito.mock(ParserResult.class);
        Mockito.when(
            parserResultBuilder.buildParserResult(
                result,
                tokens
            )
        ).thenReturn(expected);

        final ParserResult actual = expressionNodeParser.parseExpressionNode(
            tokens
        );

        Assertions.assertEquals(expected, actual);

        Mockito.verifyNoInteractions(atomNodeParser);
    }

    @Test
    void headTokenIsOpenTest()  {
        Mockito.when(headToken.getTokenKind()).thenReturn(TokenKind.OPEN_TOKEN);

        Token closeToken = Mockito.mock(Token.class);
        Mockito.when(closeToken.getTokenKind()).thenReturn(TokenKind.CLOSE_TOKEN);
        tokens.add(closeToken);

        Mockito.when(
            tokenKindAsserter.assertTokenIsNotNull(Optional.of(closeToken))
        ).thenReturn(closeToken);

        final AtomNode closeTokenResult = Mockito.mock(AtomNode.class);
        Mockito.when(nodeGenerator.generateAtomNode(ReservedValuesConstants.NIL)).thenReturn(closeTokenResult);

        final ParserResult closeTokenParserResult = Mockito.mock(ParserResult.class);
        Mockito.when(
            parserResultBuilder.buildParserResult(
                closeTokenResult,
                tokens
            )
        ).thenReturn(closeTokenParserResult);


        final ParserResult addressParserResult = Mockito.mock(ParserResult.class);
        Mockito.when(
            expressionNodeFinisher.finishParsingExpressionNode(ArgumentMatchers.any(ParserResult.class))
        ).thenReturn(addressParserResult);

        final Node addressResultingNode = Mockito.mock(Node.class);
        Mockito.when(
            addressParserResult.getResultingNode()
        ).thenReturn(addressResultingNode);

        final List<Token> remainingTokens = new ArrayList<>();
        remainingTokens.add(closeToken);
        Mockito.when(
            addressParserResult.getRemainingTokens()
        ).thenReturn(remainingTokens);

        final ParserResult dataParserResult = Mockito.mock(ParserResult.class);
        Mockito.when(
            parserResultBuilder.buildParserResult(
                closeTokenResult,
                remainingTokens
            )
        ).thenReturn(dataParserResult);

        final Node dataResultingNode = Mockito.mock(Node.class);
        Mockito.when(dataParserResult.getResultingNode()).thenReturn(dataResultingNode);

        final ExpressionNode result = Mockito.mock(ExpressionNode.class);
        Mockito.when(
            nodeGenerator.generateExpressionNode(
                addressResultingNode,
                dataResultingNode
            )
        ).thenReturn(result);

        final List<Token> dataRemainingTokens = new LinkedList<>();
        Mockito.when(dataParserResult.getRemainingTokens()).thenReturn(dataRemainingTokens);

        ParserResult expected = Mockito.mock(ParserResult.class);
        Mockito.when(
            parserResultBuilder.buildParserResult(
                result,
                dataRemainingTokens
            )
        ).thenReturn(expected);


        final var actual = expressionNodeParser.parseExpressionNode(tokens);

        Assertions.assertEquals(expected, actual);

    }

    @Test
    void headTokenIsNumericTest() {
        Mockito.when(headToken.getTokenKind()).thenReturn(TokenKind.NUMERIC_TOKEN);

        Token closeToken = Mockito.mock(Token.class);
        Mockito.when(closeToken.getTokenKind()).thenReturn(TokenKind.CLOSE_TOKEN);
        tokens.add(closeToken);

        Mockito.when(
            tokenKindAsserter.assertTokenIsNotNull(Optional.of(closeToken))
        ).thenReturn(closeToken);

        final AtomNode closeTokenResult = Mockito.mock(AtomNode.class);
        Mockito.when(nodeGenerator.generateAtomNode(ReservedValuesConstants.NIL)).thenReturn(closeTokenResult);

        final ParserResult closeTokenParserResult = Mockito.mock(ParserResult.class);
        Mockito.when(
            parserResultBuilder.buildParserResult(
                closeTokenResult,
                tokens
            )
        ).thenReturn(closeTokenParserResult);

        final ParserResult addressParserResult = Mockito.mock(ParserResult.class);
        Mockito.when(
            atomNodeParser.parseAtomNode(tokens)
        ).thenReturn(addressParserResult);

        final Node addressResultingNode = Mockito.mock(Node.class);
        Mockito.when(
            addressParserResult.getResultingNode()
        ).thenReturn(addressResultingNode);

        final List<Token> remainingTokens = new ArrayList<>();
        remainingTokens.add(closeToken);
        Mockito.when(
            addressParserResult.getRemainingTokens()
        ).thenReturn(remainingTokens);

        final ParserResult dataParserResult = Mockito.mock(ParserResult.class);
        Mockito.when(
            parserResultBuilder.buildParserResult(
                closeTokenResult,
                remainingTokens
            )
        ).thenReturn(dataParserResult);

        final Node dataResultingNode = Mockito.mock(Node.class);
        Mockito.when(dataParserResult.getResultingNode()).thenReturn(dataResultingNode);

        final ExpressionNode result = Mockito.mock(ExpressionNode.class);
        Mockito.when(
            nodeGenerator.generateExpressionNode(
                addressResultingNode,
                dataResultingNode
            )
        ).thenReturn(result);

        final List<Token> dataRemainingTokens = new LinkedList<>();
        Mockito.when(dataParserResult.getRemainingTokens()).thenReturn(dataRemainingTokens);

        ParserResult expected = Mockito.mock(ParserResult.class);
        Mockito.when(
            parserResultBuilder.buildParserResult(
                result,
                dataRemainingTokens
            )
        ).thenReturn(expected);


        final var actual = expressionNodeParser.parseExpressionNode(tokens);

        Assertions.assertEquals(expected, actual);

    }

}
