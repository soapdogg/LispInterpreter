package edu.osu.cse6341.lispInterpreter.determiner;

import lombok.AllArgsConstructor;

import java.util.stream.IntStream;

@AllArgsConstructor(staticName = "newInstance")
public class LiteralTokenValueEndIndexDeterminer {

    public int determineEndIndexOfLiteralTokenValue(
        final String word,
        final int startingPos
    ) {
        int wordLength = word.length();
        int firstNonAlphaNumericIndex = IntStream
            .range(startingPos, wordLength)
            .filter(
               i -> !Character.isAlphabetic(word.charAt(i)) && !Character.isDigit(word.charAt(i))
            ).findFirst().orElse(Integer.MAX_VALUE);

        return Math.min(wordLength, firstNonAlphaNumericIndex);
    }
}
