package edu.osu.cse6341.lispInterpreter.determiner;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class NumericTokenValueEndIndexDeterminerTest {

    private NumericTokenValueEndIndexDeterminer numericTokenValueEndIndexDeterminer;

    @BeforeEach
    void setup() {
        numericTokenValueEndIndexDeterminer = NumericTokenValueEndIndexDeterminer.newInstance();
    }

    @Test
    void wholeWordIsLiteralTest() {
        String literal = "1323";
        int result = numericTokenValueEndIndexDeterminer.determineEndIndexOfNumericTokenValue(
            literal,
            0
        );

        Assertions.assertEquals(result, literal.length());
    }

    @Test
    void nonLiteralValueHappensBeforeStartingPosTest() {
        String literal = ")1323";
        int result = numericTokenValueEndIndexDeterminer.determineEndIndexOfNumericTokenValue(
            literal,
            1
        );

        Assertions.assertEquals(result, literal.length());
    }

    @Test
    void wordContainsNonLiteralValueAfterStartingPos() {
        String literal = "1)1323";
        int result = numericTokenValueEndIndexDeterminer.determineEndIndexOfNumericTokenValue(
            literal,
            0
        );

        Assertions.assertEquals(result, 1);
    }
}
