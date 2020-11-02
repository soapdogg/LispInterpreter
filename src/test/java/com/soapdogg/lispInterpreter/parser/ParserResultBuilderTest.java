package com.soapdogg.lispInterpreter.parser;

import com.soapdogg.lispInterpreter.datamodels.Node;
import com.soapdogg.lispInterpreter.datamodels.Token;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.PriorityQueue;
import java.util.Queue;

public class ParserResultBuilderTest {

    private Node resultingNode;
    private Queue<Token> remainingTokens;

    private ParserResultBuilder parserResultBuilder;

    @BeforeEach
    void setup() {
        resultingNode = Mockito.mock(Node.class);
        remainingTokens = new PriorityQueue<>();

        parserResultBuilder = ParserResultBuilder.newInstance();
    }

    @Test
    void buildParserResultTest() {
        final var actual = parserResultBuilder.buildParserResult(
            resultingNode,
            remainingTokens
        );

        Assertions.assertEquals(resultingNode, actual.getResultingNode());
        Assertions.assertEquals(remainingTokens, actual.getRemainingTokens());
    }
}
