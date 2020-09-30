package com.soapdogg.lispInterpreter.tokenizer;

import com.soapdogg.lispInterpreter.asserter.LineFormatAsserter;
import com.soapdogg.lispInterpreter.datamodels.Token;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.*;

class TokenizerTest {

    private Scanner in;

    private ScannerToLineTransformer scannerToLineTransformer;
    private LineFormatAsserter lineFormatAsserter;
    private LineTokenizer lineTokenizer;

    private Tokenizer tokenizer;

    @BeforeEach
    void setup() {
        in = new Scanner(System.in);

        scannerToLineTransformer = Mockito.mock(ScannerToLineTransformer.class);
        lineFormatAsserter = Mockito.mock(LineFormatAsserter.class);
        lineTokenizer = Mockito.mock(LineTokenizer.class);

        tokenizer = Tokenizer.newInstance(
            scannerToLineTransformer,
            lineFormatAsserter,
            lineTokenizer
        );
    }

    @Test
    void tokenizerTest() throws Exception {
        String line = "line";
        List<String> lines = Collections.singletonList(line);

        Mockito.when(
            scannerToLineTransformer.transformScannerInputToLines(in)
        ).thenReturn(lines);

        Token token = Mockito.mock(Token.class);
        Queue<Token> tokens = new LinkedList<>();
        tokens.add(token);
        Mockito.when(
           lineTokenizer.tokenizeLine(line)
        ).thenReturn(tokens);

        Queue<Token> actual = tokenizer.tokenize(in);

        Assertions.assertEquals(1, actual.size());
        Assertions.assertEquals(token, actual.peek());

        Mockito.verify(lineFormatAsserter).assertLineFormat(line);
    }
}
