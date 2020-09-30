package com.soapdogg.lispInterpreter.tokenizer;

import com.soapdogg.lispInterpreter.datamodels.Token;
import com.soapdogg.lispInterpreter.determiner.LiteralTokenValueEndIndexDeterminer;
import com.soapdogg.lispInterpreter.determiner.NumericTokenValueEndIndexDeterminer;
import com.soapdogg.lispInterpreter.generator.TokenGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Queue;

class WordTokenizerTest {

    private TokenGenerator tokenGenerator;
    private NumericTokenValueEndIndexDeterminer numericTokenValueEndIndexDeterminer;
    private LiteralTokenValueEndIndexDeterminer literalTokenValueEndIndexDeterminer;

    private WordTokenizer wordTokenizer;

    @BeforeEach
    void setup() {
        tokenGenerator = Mockito.mock(TokenGenerator.class);
        numericTokenValueEndIndexDeterminer = Mockito.mock(NumericTokenValueEndIndexDeterminer.class);
        literalTokenValueEndIndexDeterminer = Mockito.mock(LiteralTokenValueEndIndexDeterminer.class);

        wordTokenizer = WordTokenizer.newInstance(
            tokenGenerator,
            numericTokenValueEndIndexDeterminer,
            literalTokenValueEndIndexDeterminer
        );
    }

    @Test
    void closeParenthesesTest() {
        String word = ")";

        Token closeToken = Mockito.mock(Token.class);
        Mockito.when(closeToken.getValue()).thenReturn(word);

        Mockito.when(tokenGenerator.generateCloseToken()).thenReturn(closeToken);
        Queue<Token> actual = wordTokenizer.tokenizeWord(word);

        Assertions.assertEquals(1, actual.size());
        Assertions.assertEquals(closeToken, actual.peek());

        Mockito.verifyNoInteractions(numericTokenValueEndIndexDeterminer);
        Mockito.verifyNoInteractions(literalTokenValueEndIndexDeterminer);
    }

    @Test
    void openParenthesesTest() {
        String word = "(";

        Token openToken = Mockito.mock(Token.class);
        Mockito.when(openToken.getValue()).thenReturn(word);

        Mockito.when(tokenGenerator.generateOpenToken()).thenReturn(openToken);
        Queue<Token> actual = wordTokenizer.tokenizeWord(word);

        Assertions.assertEquals(1, actual.size());
        Assertions.assertEquals(openToken, actual.peek());

        Mockito.verifyNoInteractions(numericTokenValueEndIndexDeterminer);
        Mockito.verifyNoInteractions(literalTokenValueEndIndexDeterminer);
    }

    @Test
    void numericTokenValueTest() {
        String word = "123";

        int pos = word.length();
        Mockito.when(
            numericTokenValueEndIndexDeterminer.determineEndIndexOfNumericTokenValue(
                word,
                0
            )
        ).thenReturn(pos);

        Token numericToken = Mockito.mock(Token.class);
        Mockito.when(numericToken.getValue()).thenReturn(word);

        Mockito.when(tokenGenerator.generateNumericToken(word)).thenReturn(numericToken);
        Queue<Token> actual = wordTokenizer.tokenizeWord(word);

        Assertions.assertEquals(1, actual.size());
        Assertions.assertEquals(numericToken, actual.peek());

        Mockito.verifyNoInteractions(literalTokenValueEndIndexDeterminer);
    }

    @Test
    void literalTokenValueTest() {
        String word = "ABC";

        int pos = word.length();
        Mockito.when(
            literalTokenValueEndIndexDeterminer.determineEndIndexOfLiteralTokenValue(
                word,
                0
            )
        ).thenReturn(pos);

        Token literalToken = Mockito.mock(Token.class);
        Mockito.when(literalToken.getValue()).thenReturn(word);

        Mockito.when(tokenGenerator.generateLiteralToken(word)).thenReturn(literalToken);
        Queue<Token> actual = wordTokenizer.tokenizeWord(word);

        Assertions.assertEquals(1, actual.size());
        Assertions.assertEquals(literalToken, actual.peek());

        Mockito.verifyNoInteractions(numericTokenValueEndIndexDeterminer);
    }
}
