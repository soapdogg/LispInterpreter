package edu.osu.cse6341.lispInterpreter.tokenizer;

import edu.osu.cse6341.lispInterpreter.constants.TokenValueConstants;
import edu.osu.cse6341.lispInterpreter.datamodels.Token;
import edu.osu.cse6341.lispInterpreter.datamodels.TokenKind;
import lombok.AllArgsConstructor;

import java.util.LinkedList;
import java.util.Queue;

@AllArgsConstructor(staticName = "newInstance")
public class WordTokenizer {

    public Queue<Token> tokenizeWord(String word) {

        Queue<Token> tokens = new LinkedList<>();
        int startingPos = 0;

        while (startingPos < word.length()) {
            char currentChar = word.charAt(startingPos);
            Token token;
            if (currentChar == TokenValueConstants.CLOSE_PARENTHESES) {
                token = Token.newInstance(
                    TokenKind.CLOSE_TOKEN,
                    String.valueOf(TokenValueConstants.CLOSE_PARENTHESES)
                );
            } else if (currentChar == TokenValueConstants.OPEN_PARENTHESES){
                token = Token.newInstance(
                    TokenKind.OPEN_TOKEN,
                    String.valueOf(TokenValueConstants.OPEN_PARENTHESES)
                );
            } else if (Character.isDigit(currentChar)) {
                int pos = startingPos;
                while(pos < word.length() && Character.isDigit(word.charAt(pos))) {
                    ++pos;
                }
                String fragment = word.substring(startingPos, pos);
                token = Token.newInstance(
                    TokenKind.NUMERIC_TOKEN,
                    fragment
                );
            } else {
                int pos = startingPos;
                while(pos < word.length() && (Character.isDigit(word.charAt(pos)) || Character.isAlphabetic(word.charAt(pos))) ) {
                    ++pos;
                }
                String fragment = word.substring(startingPos, pos);
                token = Token.newInstance(
                    TokenKind.LITERAL_TOKEN,
                    fragment
                );
            }
            startingPos += token.getValue().length();
            tokens.add(token);

        }
        return tokens;
    }
}
