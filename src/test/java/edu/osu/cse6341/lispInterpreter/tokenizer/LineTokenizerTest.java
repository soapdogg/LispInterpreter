package edu.osu.cse6341.lispInterpreter.tokenizer;

import edu.osu.cse6341.lispInterpreter.datamodels.Token;
import edu.osu.cse6341.lispInterpreter.tokenizer.LineTokenizer;
import edu.osu.cse6341.lispInterpreter.tokenizer.WordTokenizer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.LinkedList;
import java.util.Queue;

class LineTokenizerTest {

    private String word1;
    private String word2;
    private String line;

    private WordTokenizer wordTokenizer;

    private LineTokenizer lineTokenizer;

    @BeforeEach
    void setup() {
        word1 = "word1";
        word2 = "word2";
        line = "\t" + word1 + "    " + word2 + "\n";

        wordTokenizer = Mockito.mock(WordTokenizer.class);
        lineTokenizer = LineTokenizer.newInstance(
            wordTokenizer
        );
    }

    @Test
    void tokenizeLineTest() {
        Token word1Token = Mockito.mock(Token.class);
        Queue<Token> word1Tokens = new LinkedList<>();
        word1Tokens.add(word1Token);
        Mockito.when(wordTokenizer.tokenizeWord(word1)).thenReturn(word1Tokens);

        Token word2Token = Mockito.mock(Token.class);
        Queue<Token> word2Tokens = new LinkedList<>();
        word2Tokens.add(word2Token);
        Mockito.when(wordTokenizer.tokenizeWord(word2)).thenReturn(word2Tokens);

        Queue<Token> actual = lineTokenizer.tokenizeLine(line);

        Assertions.assertEquals(2, actual.size());
        Assertions.assertEquals(word1Token, actual.remove());
        Assertions.assertEquals(word2Token, actual.remove());
    }
}
