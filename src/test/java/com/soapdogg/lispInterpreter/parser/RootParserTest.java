package com.soapdogg.lispInterpreter.parser;

import com.soapdogg.lispInterpreter.datamodels.Node;
import com.soapdogg.lispInterpreter.datamodels.ParserResult;
import com.soapdogg.lispInterpreter.datamodels.Token;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class RootParserTest {

    private Queue<Token> tokens;

    private Node resultingNode;

    private RootParser rootParser;

    @BeforeEach
    void setup() throws Exception {
        NodeParser nodeParser = Mockito.mock(NodeParser.class);

        Token headToken = Mockito.mock(Token.class);
        tokens = new LinkedList<>();
        tokens.add(headToken);

        resultingNode = Mockito.mock(Node.class);
        Queue<Token> remainingTokens = new LinkedList<>();

        ParserResult parserResult = Mockito.mock(ParserResult.class);
        Mockito.when(parserResult.getResultingNode()).thenReturn(resultingNode);
        Mockito.when(parserResult.getRemainingTokens()).thenReturn(remainingTokens);

        Mockito.when(nodeParser.parseIntoNode(tokens)).thenReturn(parserResult);

        rootParser = RootParser.newInstance(nodeParser);
    }

    @Test
    void rootParserTest() throws Exception {
        List<Node> actual = rootParser.parse(tokens);

        Assertions.assertEquals(1, actual.size());
        Assertions.assertEquals(resultingNode, actual.get(0));
    }
}
