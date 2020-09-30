package com.soapdogg.lispInterpreter.tokenizer;

import com.soapdogg.lispInterpreter.datamodels.Token;
import com.soapdogg.lispInterpreter.determiner.LiteralTokenValueEndIndexDeterminer;
import com.soapdogg.lispInterpreter.determiner.NumericTokenValueEndIndexDeterminer;
import com.soapdogg.lispInterpreter.constants.TokenValueConstants;
import com.soapdogg.lispInterpreter.generator.TokenGenerator;
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
