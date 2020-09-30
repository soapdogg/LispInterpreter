package com.soapdogg.lispInterpreter.determiner;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LiteralTokenValueEndIndexDeterminerTest {

    private LiteralTokenValueEndIndexDeterminer literalTokenValueEndIndexDeterminer;

    @BeforeEach
    void setup() {
        literalTokenValueEndIndexDeterminer = LiteralTokenValueEndIndexDeterminer.newInstance();
    }

    @Test
    void wholeWordIsLiteralTest() {
        String literal = "ERIC1323";
        int result = literalTokenValueEndIndexDeterminer.determineEndIndexOfLiteralTokenValue(
            literal,
            0
        );

        Assertions.assertEquals(result, literal.length());
    }

    @Test
    void nonLiteralValueHappensBeforeStartingPosTest() {
        String literal = ")ERIC1323";
        int result = literalTokenValueEndIndexDeterminer.determineEndIndexOfLiteralTokenValue(
            literal,
            1
        );

        Assertions.assertEquals(result, literal.length());
    }

    @Test
    void wordContainsNonLiteralValueAfterStartingPos() {
        String literal = "E)RIC1323";
        int result = literalTokenValueEndIndexDeterminer.determineEndIndexOfLiteralTokenValue(
            literal,
            0
        );

        Assertions.assertEquals(result, 1);
    }
}
