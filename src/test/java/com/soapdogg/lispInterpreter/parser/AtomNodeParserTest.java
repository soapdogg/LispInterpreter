package com.soapdogg.lispInterpreter.parser;

import com.soapdogg.lispInterpreter.datamodels.AtomNode;
import com.soapdogg.lispInterpreter.datamodels.ParserResult;
import com.soapdogg.lispInterpreter.datamodels.Token;
import com.soapdogg.lispInterpreter.generator.NodeGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.LinkedList;
import java.util.Queue;

public class AtomNodeParserTest {

    private NodeGenerator nodeGenerator;

    private String value;
    private Token headToken;
    private Queue<Token> tokens;

    private AtomNode atomNode;

    private AtomNodeParser atomNodeParser;

    @BeforeEach
    void setup() {
        nodeGenerator = Mockito.mock(NodeGenerator.class);

        value = "value";
        headToken = Mockito.mock(Token.class);
        Mockito.when(headToken.getValue()).thenReturn(value);

        atomNode = Mockito.mock(AtomNode.class);
        Mockito.when(nodeGenerator.generateAtomNode(value)).thenReturn(atomNode);

        tokens = new LinkedList<>();
        tokens.add(headToken);

        atomNodeParser = AtomNodeParser.newInstance(nodeGenerator);
    }

    @Test
    void atomNodeParserTest() {
        ParserResult actual = atomNodeParser.parseAtomNode(tokens);

        Assertions.assertEquals(atomNode, actual.getResultingNode());
        Assertions.assertTrue(actual.getRemainingTokens().isEmpty());
    }
}
