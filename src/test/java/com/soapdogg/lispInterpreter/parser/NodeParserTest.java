package com.soapdogg.lispInterpreter.parser;

import com.soapdogg.lispInterpreter.asserter.TokenKindAsserter;
import com.soapdogg.lispInterpreter.datamodels.Node;
import com.soapdogg.lispInterpreter.datamodels.ParserResult;
import com.soapdogg.lispInterpreter.datamodels.Token;
import com.soapdogg.lispInterpreter.datamodels.TokenKind;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;

public class NodeParserTest {

    private TokenKindAsserter tokenKindAsserter;
    private ExpressionNodeParser expressionNodeParser;
    private AtomNodeParser atomNodeParser;

    private Token headToken;
    private Queue<Token> tokens;

    private NodeParser nodeParser;

    @BeforeEach
    void setup() throws Exception {
        tokenKindAsserter = Mockito.mock(TokenKindAsserter.class);
        expressionNodeParser = Mockito.mock(ExpressionNodeParser.class);
        atomNodeParser = Mockito.mock(AtomNodeParser.class);

        headToken = Mockito.mock(Token.class);
        tokens = new LinkedList<>();
        tokens.add(headToken);

        Mockito.when(
            tokenKindAsserter.assertTokenIsNotNull(Optional.of(headToken))
        ).thenReturn(headToken);

        Mockito.doNothing().when(tokenKindAsserter).assertTokenIsAtomOrOpen(headToken);

        nodeParser = NodeParser.newInstance(
            tokenKindAsserter,
            expressionNodeParser,
            atomNodeParser
        );
    }

    @Test
    void parseIntoExpressionNodeTest() throws Exception {
        Mockito.when(headToken.getTokenKind()).thenReturn(TokenKind.OPEN_TOKEN);

        final ParserResult result = Mockito.mock(ParserResult.class);
        Mockito.when(expressionNodeParser.parseExpressionNode(new LinkedList<>())).thenReturn(result);

        final Token closeToken = Mockito.mock(Token.class);
        final Queue<Token> remainingTokens = new LinkedList<>();

        remainingTokens.add(closeToken);
        Mockito.when(tokenKindAsserter.assertTokenIsNotNull(Optional.of(closeToken))).thenReturn(closeToken);
        Mockito.doNothing().when(tokenKindAsserter).assertTokenIsClose(closeToken);

        Node resultingNode = Mockito.mock(Node.class);
        Mockito.when(result.getResultingNode()).thenReturn(resultingNode);
        Mockito.when(result.getRemainingTokens()).thenReturn(remainingTokens);

        final ParserResult actual = nodeParser.parseIntoNode(tokens);

        Assertions.assertEquals(resultingNode, actual.getResultingNode());
        Assertions.assertTrue(actual.getRemainingTokens().isEmpty());

        Mockito.verifyNoInteractions(atomNodeParser);
    }

    @Test
    void parseIntoAtomNodeTest() throws Exception {
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
