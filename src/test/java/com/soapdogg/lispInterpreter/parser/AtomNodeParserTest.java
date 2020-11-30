package com.soapdogg.lispInterpreter.parser;

import com.soapdogg.lispInterpreter.datamodels.AtomNode;
import com.soapdogg.lispInterpreter.datamodels.ParserResult;
import com.soapdogg.lispInterpreter.datamodels.Token;
import com.soapdogg.lispInterpreter.generator.NodeGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;

public class AtomNodeParserTest {

    private List<Token> tokens;

    private ParserResult expected;

    private AtomNodeParser atomNodeParser;

    @BeforeEach
    void setup() {
        NodeGenerator nodeGenerator = Mockito.mock(NodeGenerator.class);
        ParserResultBuilder parserResultBuilder = Mockito.mock(ParserResultBuilder.class);

        String value = "value";
        Token headToken = Mockito.mock(Token.class);
        Mockito.when(headToken.getValue()).thenReturn(value);

        AtomNode atomNode = Mockito.mock(AtomNode.class);
        Mockito.when(nodeGenerator.generateAtomNode(value)).thenReturn(atomNode);

        tokens = Collections.singletonList(headToken);

        expected = Mockito.mock(ParserResult.class);
        Mockito.when(
            parserResultBuilder.buildParserResult(
                atomNode,
                Collections.emptyList()
            )
        ).thenReturn(expected);

        atomNodeParser = new AtomNodeParser(
            nodeGenerator,
            parserResultBuilder
        );
    }

    @Test
    void atomNodeParserTest() {
        ParserResult actual = atomNodeParser.parseAtomNode(tokens);

        Assertions.assertEquals(expected, actual);
    }
}
