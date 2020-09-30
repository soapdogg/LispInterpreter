package com.soapdogg.lispInterpreter.determiner;

import lombok.AllArgsConstructor;

import java.util.stream.IntStream;

@AllArgsConstructor(staticName = "newInstance")
public class NumericTokenValueEndIndexDeterminer {

    public int determineEndIndexOfNumericTokenValue(
        final String word,
        final int startingPos
    ) {
        int wordLength = word.length();
        int firstNonAlphaNumericIndex = IntStream
            .range(startingPos, wordLength)
            .filter(
                i -> !Character.isDigit(word.charAt(i))
            ).findFirst().orElse(Integer.MAX_VALUE);

        return Math.min(wordLength, firstNonAlphaNumericIndex);
    }
}
