package edu.osu.cse6341.lispInterpreter.tokenizer;

import edu.osu.cse6341.lispInterpreter.constants.TokenValueConstants;
import edu.osu.cse6341.lispInterpreter.datamodels.Token;
import lombok.AllArgsConstructor;

import java.util.LinkedList;
import java.util.Queue;

@AllArgsConstructor(staticName = "newInstance")
public class LineTokenizer {

    private final WordTokenizer wordTokenizer;

    public Queue<Token> tokenizeLine(
        String line
    ) {
        String trimmedLine = line.trim();
        String [] words = trimmedLine.split(TokenValueConstants.WHITE_SPACE_PATTERN);
        Queue<Token> tokens = new LinkedList<>();
        for (String word : words) {
            Queue<Token> wordTokens = wordTokenizer.tokenizeWord(word);
            tokens.addAll(wordTokens);
        }
        return tokens;
    }
}
