package com.soapdogg.lispInterpreter.parser;

import com.soapdogg.lispInterpreter.datamodels.Node;
import com.soapdogg.lispInterpreter.datamodels.Token;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class ParserResultBuilderTest {

    private Node resultingNode;
    private List<Token> remainingTokens;

    private ParserResultBuilder parserResultBuilder;

    @BeforeEach
    void setup() {
        resultingNode = Mockito.mock(Node.class);
        remainingTokens = new ArrayList<>();

        parserResultBuilder = new ParserResultBuilder();
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
