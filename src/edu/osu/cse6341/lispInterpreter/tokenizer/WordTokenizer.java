package edu.osu.cse6341.lispInterpreter.tokenizer;

import edu.osu.cse6341.lispInterpreter.constants.TokenValueConstants;
import edu.osu.cse6341.lispInterpreter.datamodels.Token;
import edu.osu.cse6341.lispInterpreter.determiner.LiteralTokenValueEndIndexDeterminer;
import edu.osu.cse6341.lispInterpreter.determiner.NumericTokenValueEndIndexDeterminer;
import edu.osu.cse6341.lispInterpreter.generator.TokenGenerator;
import lombok.AllArgsConstructor;

import java.util.LinkedList;
import java.util.Queue;

@AllArgsConstructor(staticName = "newInstance")
public class WordTokenizer {

    private final TokenGenerator tokenGenerator;
    private final NumericTokenValueEndIndexDeterminer numericTokenValueEndIndexDeterminer;
    private final LiteralTokenValueEndIndexDeterminer literalTokenValueEndIndexDeterminer;

    public Queue<Token> tokenizeWord(String word) {

        Queue<Token> tokens = new LinkedList<>();
        int startingPos = 0;

        while (startingPos < word.length()) {
            char currentChar = word.charAt(startingPos);
            Token token;
            if (currentChar == TokenValueConstants.CLOSE_PARENTHESES) {
                token = tokenGenerator.generateCloseToken();
            } else if (currentChar == TokenValueConstants.OPEN_PARENTHESES){
                token = tokenGenerator.generateOpenToken();
            } else if (Character.isDigit(currentChar)) {
                int pos = numericTokenValueEndIndexDeterminer.determineEndIndexOfNumericTokenValue(
                    word,
                    startingPos
                );
                String fragment = word.substring(startingPos, pos);
                token = tokenGenerator.generateNumericToken(
                    fragment
                );
            } else {
                int pos = literalTokenValueEndIndexDeterminer.determineEndIndexOfLiteralTokenValue(
                    word,
                    startingPos
                );
                String fragment = word.substring(startingPos, pos);
                token = tokenGenerator.generateLiteralToken(
                    fragment
                );
            }
            startingPos += token.getValue().length();
            tokens.add(token);

        }
        return tokens;
    }
}
